# Nicholas Dill
# CSCI 236
# 10/23/2021
# Book Recommender Program

import sys

"""
readFromFile Function
This function, given a file, will return a list with each element being three lines from the file. 
"""
def readFromFile(file): 
    linesTemp = file.readlines()
    lines = []
    for lineCount in range(0, len(linesTemp) - 1, 3): 
        lines.append([linesTemp[lineCount].strip(), linesTemp[lineCount + 1].strip(), linesTemp[lineCount + 2].strip()])
    return lines

"""
getBooks Function
This function, given a list of the lines in a file, will return a list of all the unique book names.
"""
def getBooks(lines): 
    books = set()
    for data in lines: 
        books.add(data[1])
    return list(books)

"""
getRatings Function
This function, given a list of lines and list of books, will return a dictionary with the users in the file being the key and the values being a list of their 
ratings for the different books. 
"""
def getRatings(lines, books): 
    users = set()
    # Iterating through list of three lines and adding each unique user to a set.
    for data in lines: 
        users.add(data[0])
    ratings = {}
    # Iterating through the unique users and creating a book list for each with all elements set to 0.
    for user in users: 
        ratings[user] = [0] * len(books)
    # Iterating through the list of three lines and getting the users rating for each book and placing it in their respective book list. 
    for data in lines: 
        list = ratings[data[0]]
        list[books.index(data[1])] = int(data[2])
    return ratings

"""
getAverages Function
This function, given a list of lines and list of books, will return a dictionary with the books being the key and the values being the books average rating according 
to the calculated average of all user ratings. 
"""
def getAverages(lines, books): 
    averages = {}
    # Iterating through the list of unique books.
    for book in books: 
        sum = 0
        count = 0
        # Iterating through the list of three lines.
        for data in lines: 
            # If the book title in the current element is equal to the current book in the book list, add to sum.
            if data[1] == book: 
                sum += int(data[2])
                # If rating is not zero, add to the count. 
                if int(data[2]) != 0: 
                    count += 1
        # Adding average to dictionary with book title as key. 
        averages[book] = sum / count
    return averages

"""
getRecommendations Function
This function, given an inputed user, a dictionary of books and their ratings, and a list of books, will perform a series of calculations in order to determine and 
recommend books that the given user might be interested in. 
"""
def getRecommendations(user, ratings, books): 
    dotProducts = getDotProduct(user, ratings)
    mostSimilar = getSimilarUsers(user, dotProducts)
    return getAverageBooks(mostSimilar, books, ratings)

"""
getDotProduct Function
This function, given a user and a dictionary of books and their ratings, will calculate the dot product by multiplying each element in the given user's list at the 
same index in the other user's list and then sum the results and repeat for every user in the dictionary. This function returns a dictionary of the users and their 
dot products. 
"""
def getDotProduct(user, ratings): 
    dotProducts = {}
    # Iterating through the users and their book ratings.
    for userInfo in ratings: 
        sum = 0
        # Simultaneously iterating through inputed user and current user in ratings dictionary.
        for userRating, otherRating in zip(ratings[user], ratings[userInfo]): 
            # Each time through the loop we multiply the inputed user's rating with current user in loop and add it to the overall sum. 
            sum += (userRating * otherRating)
        # Adding sum to dictionary with the user as key. 
        dotProducts[userInfo] = sum
    return dotProducts

"""
getSimilarUsers Function
This function, given a user and a dictionary of users and their dot products, will calculate the similarity of all other users to the given user with their dot 
products and then find the top three most similar to the given users. This function returns a list of the top three most similar users, containing the user and then 
their dot product. 
"""
def getSimilarUsers(user, dotProducts): 
    similarUsers = {}
    # Iterating through the users and their dot products. 
    for userInfo in dotProducts:
        # Each time through the loop subtract the inputed user's dot product with current user in the loop to get similarity. 
        similarUsers[userInfo] = dotProducts[user] - dotProducts[userInfo]
    # Remove inputed user from dictionary.
    similarUsers.pop(user)
    # Return the top three most similar users to the inputed user. 
    return sorted(zip(similarUsers.values(), similarUsers.keys()))[:3]

"""
getAverageBooks Function
This function, given the top three similar users, a list of books, and a dictionary of users and their ratings, will find the average book ratings from the three most 
similar users to the inputed user and return a dictionary of the book and rating as a recommendation to the user. 
"""
def getAverageBooks(similar, books, ratings): 
    averageBooks = {}
    # Iterating through the books list. 
    for index in range(0, len(books)): 
        sum = 0
        count = 0
        average = 0
        # Iterating through the most similar users. 
        for similarUser in similar: 
            # Get the ratings of the similar user.
            bookList = ratings[similarUser[1]]
            # Add the current book rating to the sum. 
            sum += bookList[index]
            # If the rating is not zero, increment count. 
            if bookList[index] != 0: 
                count += 1
        # If the sum is zero, the average is just the sum. Otherwise it is the sum / count of non-zero items.
        if sum == 0: 
            average = sum
        else: 
            average = sum / count
        # Adding average to dictionary with book title as key. 
        averageBooks[books[index]] = average
    return averageBooks

"""
main Function
"""
def main(): 
    # Check to ensure user enters a file name. 
    if(len(sys.argv) != 2): 
        print("USAGE: Recommender.py filename")
        quit()
    # Open the file. 
    file = open(sys.argv[1], "r")
    # Get a list containing every three lines in the file.
    lines = readFromFile(file)
    # Getting list of unique book titles.
    books = getBooks(lines)
    # Getting dictionary of users and their book ratings.
    ratings = getRatings(lines, books)
    # Getting the average rating for each book and sorting via descending. 
    averages = sorted(getAverages(lines, books).items(), key = lambda kv: kv[1], reverse = True)
    # Output welcome message. 
    print("Welcome to the CSCI 236 Book Recommender. Type the word in the left column to do the action on the right.")
    print("recommend : recommend books for a particular user")
    print("averages  : output the average ratings of all books in the system")
    print("quit      : exit the program")
    task = ""
    # Continuously run the program until the user enters quit. 
    while task != "quit": 
        task = input("next task? ")
        if task == "averages": 
            # Iterating through averages and printing each book rating average. 
            for average in averages: 
                print(average[0], average[1])
        elif task == "recommend": 
            user = input("user? ")
            # Check if the user is in the dictionary of ratings. 
            if user in ratings.keys(): 
                # Getting the recommended books for the inputed user and sorting via descending. 
                recommendedBooks = sorted(getRecommendations(user, ratings, books).items(), key = lambda kv: kv[1], reverse = True)
                # Iterating through recommended books and printing them and their average if the average is > 0.
                for book in recommendedBooks: 
                    if book[1] > 0: 
                        print(book[0], book[1])
            # If user is not in the dictionary, print the averages. 
            else: 
                # Iterating through averages and printing each book rating average if they are > 0.
                for average in averages: 
                    if average[1] > 0: 
                        print(average[0], average[1])
        print()

main()
