# Importing data from csv files into dataframes.
aalData <- read.table("c:/AAL.csv", header = TRUE, sep = ",")
dalData <- read.table("c:/DAL.csv", header = TRUE, sep = ",")

# Converting the original date columns containing chars into the date type.
datesAAL <- as.Date(aalData$date)
aalData$datesAAL <- datesAAL
datesDAL <- as.Date(dalData$date)
dalData$datesDAL <- datesDAL

# Finding the mean of the high, low, opening, and closing prices for the AAL and DAL stocks.
sapply(aalData, mean, na.rm = TRUE)
sapply(dalData, mean, na.rm = TRUE)

# Finding the mean of the high and low prices for AAL and DAL stocks during the Great Recession.
aalRecessionData <- subset(aalData, subset = date >= "2008-09-15" & date <= "2010-12-31")
sapply(aalRecessionData, mean, na.rm = TRUE)
dalRecessionData <- subset(dalData, subset = date >= "2008-09-15" & date <= "2010-12-31")
sapply(dalRecessionData, mean, na.rm = TRUE)

# Plotting the High Stock Prices of AAL and DAL.
library(ggplot2)
aalHighGraph <- ggplot(aalData, aes(x = datesAAL, y = high)) + geom_point() + labs(x = "Year (2007-2018)", y = "High Stock Price", title = "AAL High Stock Prices From 2007-2018")
aalHighGraph
dalHighGraph <- ggplot(dalData, aes(x = datesDAL, y = high)) + geom_point() + labs(x = "Year (2007-2018)", y = "High Stock Price", title = "DAL High Stock Prices From 2007-2018")
dalHighGraph

# Plotting the Low Stock Prices of AAL and DAL.
aalLowGraph <- ggplot(aalData, aes(x = datesAAL, y = low)) + geom_point() + labs(x = "Year (2007-2018)", y = "Low Stock Price", title = "AAL Low Stock Prices From 2007-2018")
aalLowGraph
dalLowGraph <- ggplot(dalData, aes(x = datesDAL, y = low)) + geom_point() + labs(x = "Year (2007-2018)", y = "Low Stock Price", title = "DAL Low Stock Prices From 2007-2018")
dalLowGraph

# Ploting the DAL high stock during the company's acquisition of Northwest Airlines.
acquisitionData <- subset(dalData, subset = date >= "2008-04-14" & date <= "2010-12-31")
acquisitionGraph <- ggplot(acquisitionData, aes(x = datesDAL, y = high)) + geom_point() + labs(x = "Year (2008-2010)", y = "High Stock Price", title = "DAL Stock During Northwestern Airlines Acquisition")
acquisitionGraph

# Plotting the AAL vs DAL high stock prices to view their relationship.
mergedData <- merge(aalData, dalData, by = "date")
mergedGraph <- ggplot(mergedData, aes(x = high.x, y = high.y)) + geom_point() + stat_smooth(method = "lm", col = "#C42126", se = FALSE, size = 1) + labs(x = "AAL High Stock Prices", y = "DAL High Stock Prices", title = "Relation between AAL and DAL High Stock Prices")
mergedGraph
