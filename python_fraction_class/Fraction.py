# Nicholas Dill
# CSCI U236
# 12/4/2021
# Fractions Program

# Greatest Common Denominator Function
def gcd(num1, num2):
    while num1 % num2 != 0:
        old_num1 = num1
        old_num2 = num2

        num1 = old_num2
        num2 = old_num1 % old_num2

    return num2

class Fraction:

    # Constructor Function
    def __init__(self, num = 0, den = 1):
        self.sign = 1
        # Initializing the numerator and denominator
        if den == 0:
            raise ZeroDivisionError("Denominator cannot be zero.")
        if num == 0:
            den = 1
        elif (num < 0 and den >= 0 or num >= 0 and den < 0):
            self.sign = -1
            num = abs(num)
            den = abs(den)
        if type(num) is float:
            num *= 100
            den *= 100
        self.numerator = (int)(num)
        self.denominator = (int)(den)
        self.simplify()

    # Simplify Function
    def simplify(self):
        common = gcd(self.numerator, self.denominator)

        self.numerator = self.numerator // common
        self.denominator = self.denominator // common

    # Equivalent Function
    def equivalent(self, otherFraction): 
        commonDen = gcd(self.denominator, otherFraction.denominator)
        selfMultiplier = commonDen / self.denominator
        otherFracMultiplier = commonDen / otherFraction.denominator

        # Gets new fractions after multiplying each by their given multipliers
        newSelfNum = self.numerator * selfMultiplier
        newOtherFracNum = otherFraction.numerator * otherFracMultiplier

        return Fraction(newSelfNum, commonDen), Fraction(newOtherFracNum, commonDen)

    # Get Numerator Function
    def getNum(self): 
        return self.numerator

    # Get Denominator Function
    def getDen(self): 
        return self.denominator

    # Get Sign Function
    def getSign(self): 
        return self.sign

    # String Function
    def __str__(self):
        if self.sign == -1:
            front = "-"
        else:
            front = ""
        if self.denominator > 1:
            back = "/" + str(self.denominator)
        else:
            back = ""
        return front + str(self.numerator) + back

    # Add Function
    def __add__(self, otherFraction):
      num = (self.numerator * otherFraction.denominator + self.denominator * otherFraction.numerator)
      den = self.denominator * otherFraction.denominator
      return Fraction(num, den)

    # Subtract Function
    def __sub__(self, otherFraction): 
        num = (self.numerator * otherFraction.denominator - self.denominator * otherFraction.numerator)
        den = self.denominator * otherFraction.denominator
        return Fraction(num, den)

    # Multiply Function
    def __mul__(self, otherFraction): 
        num = self.numerator * otherFraction.numerator
        den = self.denominator * otherFraction.denominator
        return Fraction(num, den)

    # Division Function
    def __truediv__(self, otherFraction): 
        num = self.numerator * otherFraction.denominator
        den = self.denominator * otherFraction.numerator
        return Fraction(num, den)

    # Equality Function
    def __eq__(self, otherFraction): 
        return (self.numerator * otherFraction.denominator) == (self.denominator * otherFraction.numerator)

    # Less Than Function
    def __lt__(self, otherFraction): 
        if self.denominator == otherFraction.denominator: 
            return self.numerator < otherFraction.numerator 
        else: 
            newSelfFrac, newOtherFrac = self.equivalent(otherFraction)
            return newSelfFrac.numerator < newOtherFrac.numerator

    # Greater Than Function
    def __gt__(self, otherFraction): 
        if self.denominator == otherFraction.denominator: 
            return self.numerator > otherFraction.numerator 
        else: 
            newSelfFrac, newOtherFrac = self.equivalent(otherFraction)
            return newSelfFrac.numerator > newOtherFrac.numerator

    # Negation Function
    def __neg__(self): 
        return Fraction((self.numerator * self.sign) * -1, self.denominator)
