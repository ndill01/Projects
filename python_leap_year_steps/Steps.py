# Nicholas Dill
# CSCI 236

import sys

# Named constants - create one for each month to store number of days in that month; assume this is NOT a leap year
JANUARY = 31
FEBRUARYNOLEAP = 28
FEBRUARYLEAP = 29
MARCH = 31
APRIL = 30
MAY = 31
JUNE = 30
JULY = 31
AUGUST = 31
SEPTEMBER = 30
OCTOBER = 31
NOVEMBER = 30
DECEMBER = 31

def main(): 

    # Open the steps file using the first command line argument to get the input file name. 
    openFile = open(sys.argv[1], 'r')
    stepsFile = open(sys.argv[1], 'r')

    isLeapYear = False
    count = 0
    for line in openFile: 
        count += 1

    if count == 366: 
        isLeapYear = True

    # Display the average steps for each month using a function to calculate and display. 
    constants = [JANUARY, FEBRUARYNOLEAP, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER]

    if isLeapYear: 
        constants[1] = FEBRUARYLEAP

    months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"]

    for constant, month in zip(constants, months):
        averageSteps(stepsFile, month, constant)
    
    # Close the file. 
    openFile.close()
    stepsFile.close()

def averageSteps(stepsFile, monthName, days): 
    
    # Compute the average number of steps for the given month.
    sum = 0
    for day in range(1, days + 1):
        steps = int(stepsFile.readline())
        sum += steps

    # Output the results. 
    print("The average steps taken in", monthName, "was", round(sum / days, 1))

main()
