# Nicholas Dill
# CSCI U580 - Intro to AI
# Knapsack Problem Assignment

from numpy.random import randint
from numpy.random import rand

import sys

# Fitness Function
def knapsack(pop, items, weightAmt):
    val = 0
    weight = 0
    # Traverse population to determine fitness.
    for x in range(len(pop)): 
        if pop[x] == 1:     
            # If item is in knapsack, add its weight and value to total. 
            weight += items[x][0]
            val += items[x][1]
    # If weight exceedes limit, halve the value.
    if weight > weightAmt: 
        return val / 2
    return val

# Generation Selection
def selectGeneration(pop, scores): 
    # First random selection in the population.
    firstSelection = randint(int(len(pop) / 2))
    # Traverse through random values. 
    for x in randint(0, len(pop), 1): 
        # Check if selected is better than random. 
        if scores[x] < scores[firstSelection]: 
            firstSelection = x
    return pop[firstSelection]

# Crossover Operation
def crossover(parent1, parent2): 
    # Select half-way point of the parent as the crossover point.
    crossPoint = int(len(parent1) / 2)
    # Perform crossover and set to the offspring. 
    child1 = parent1[:crossPoint] + parent2[crossPoint:]
    child2 = parent2[:crossPoint] + parent1[crossPoint:]
    return [child1, child2]

# Mutation Operation
def mutation(individual, mutRate): 
    # Traverse the individual. 
    for x in range(len(individual)): 
        # Generate random number and check if it is below the mutation rate. 
        if rand() < mutRate: 
            # Flip the bit to perform mutation. 
            individual[x] = 1 - individual[x]

# Genetic Algorithm
def geneticAlgorithm(items, weightAmt, popSize, iterations, mutRate): 
    # Initial population of random solutions. 
    population = [randint(0, 2, popSize).tolist() for _ in range(popSize)]
    best = knapsack(population[0], items, weightAmt)
    # Simulate natural selection across generations. 
    for gen in range(iterations): 
        # Give all individuals in the population a fitness value. 
        scores = [knapsack(x, items, weightAmt) for x in population]
        # Check for the best solution in the population. 
        for x in range(popSize): 
            if scores[x] >= best: 
                best = scores[x]
                print(f"> New Best Fitness on Generation {gen}: {best}")
        # Select parents from the population. 
        parents = [selectGeneration(population, scores) for _ in range(popSize)]
        # Create the next generation. 
        children = []
        for x in range(0, popSize, 2): 
            # Get new parents in pairs. 
            parent1 = parents[x]
            parent2 = parents[x + 1]
            # Perform crossover and mutation operations. 
            for y in crossover(parent1, parent2): 
                # Perform mutation on the individual.
                mutation(y, mutRate)
                # Store in the list. 
                children.append(y)
        # Replace the old generation with the new generation. 
        population = children
    return best

def main(): 
    # Open input file from command line. 
    openFile = open(sys.argv[1], 'r')
    # Define population size from first line in text file.
    popSize = int(openFile.readline())
    # Define weight constaint from second line in text file.
    weightAmt = int(openFile.readline())
    items = []
    for line in openFile: 
        item = line.strip().split(" ")
        items.append([int(item[0]), int(item[1])])
    # Define total number of iterations. 
    iterations = 100
    # Rate of mutation. 
    mutRate = 0.5
    # Perform genetic algorithm search. 
    score = geneticAlgorithm(items, weightAmt, popSize, iterations, mutRate)
    print(f"Solution Found!: {score}")

main()
