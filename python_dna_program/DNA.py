# Nicholas Dill
# CSCI 236

import sys

# Constants
MINCODONS = 5
PERCMASS = 30
UNIQUENUC = 4
NUCPERCODON = 3

def readFile(file): 
    # Read input file of nucleotides sequences. 
        # Consists of pairs of strings - name, nucleotide sequence.
        # Dashes are ignored except for mass.
    linesTemp = []
    lines = []
    for line in file: 
        linesTemp.append(line.strip())
    for count in range(0, len(linesTemp) - 1, 2): 
        lines.append([linesTemp[count], linesTemp[count + 1].upper()])
    return lines
    

def countNucleotides(data): 
    # Count each nucleotide.
    aCount = 0
    cCount = 0
    gCount = 0
    tCount = 0
    for seq in data: 
        if seq == 'A': 
            aCount += 1
        elif seq == 'C': 
            cCount += 1
        elif seq == 'G': 
            gCount += 1
        elif seq == 'T': 
            tCount += 1
    return [aCount, cCount, gCount, tCount]

def getNucleotideWeight(data): 
    # Compute weight of each nucleotide. 
    aWeight = 0
    cWeight = 0
    gWeight = 0
    tWeight = 0
    dashWeight = 0
    totalWeight = 0
    for seq in data: 
        if seq == 'A': 
            aWeight += 135.128
        elif seq == 'C': 
            cWeight += 111.103
        elif seq == 'G': 
            gWeight += 151.128
        elif seq == 'T': 
            tWeight += 125.107
        elif seq == '-': 
            dashWeight += 100.000
    totalWeight += aWeight + cWeight + gWeight + tWeight + dashWeight
    return [[round((aWeight / totalWeight) * 100, 1), round((cWeight / totalWeight) * 100, 1), round((gWeight / totalWeight) * 100, 1), round((tWeight / totalWeight) * 100, 1)], round(totalWeight, 1)]

def getCodons(data): 
    # Show the codons (list of 3 nucleotides).
    codons = []
    codon = ""
    count = 0
    for seq in data: 
        if seq == '-': 
            continue
        else: 
            if count == NUCPERCODON: 
                count = 1
                codons.append(codon)
                codon = ""
                codon += seq
            else: 
                count += 1
                codon += seq
    codons.append(codon)
    return codons

def isProtein(codons, nucWeight): 
    # Determine if sequence is a protein. Protein has 4 characteristics: 
        # 1. Begins with valid start codon (ATG).
        # 2. Ends with valid stop codon (TAA, TAG, TGA).
        # 3. Contains at least 5 total codons.
        # 4. Cytosine (C) and Guanine (G) combined account for at least 30% of total mass.
        validStart = codons[0] == 'ATG'
        validStop = codons[len(codons) - 1] == 'TAA' or codons[len(codons) - 1] == 'TAG' or codons[len(codons) - 1] == 'TGA'
        totalCodons = len(codons) >= MINCODONS
        totalMass = float(nucWeight[0][1]) + float(nucWeight[0][2]) >= PERCMASS
        if validStart and validStop and totalCodons and totalMass: 
            return "YES"
        else: 
            return "NO"

def main(): 
    # Display program information. 
    print("This program reports information about DNA \nnucleotide sequences that may encode proteins.")
    # Get input file abd output file names. 
    inFile = open(input("Input file name? "), 'r')
    outFile = open(input("Output file name? "), 'x')
    lines = readFile(inFile)
    # Report information about given nucleotide sequences. 
    for line in lines: 
        nucCounts = countNucleotides(line[1])
        nucWeight = getNucleotideWeight(line[1])
        codons = getCodons(line[1])
        protein = isProtein(codons, nucWeight)
        outFile.write(f"Region Name: {line[0]} \n")
        outFile.write(f"Nucleotides: {line[1]} \n")
        outFile.write(f"Nuc. Counts: {nucCounts} \n")
        outFile.write(f"Total Mass%: {nucWeight[0]} of {nucWeight[1]} \n")
        outFile.write(f"Codons List: {codons} \n")
        outFile.write(f"Is Protein?: {protein} \n\n")

main()
