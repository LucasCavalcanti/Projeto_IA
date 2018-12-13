library(readr)
library(caret)

true_car_listings <- read_csv("/home/lucas/Documents/true_car_listings.csv")
newSet <- true_car_listings[sample(nrow(true_car_listings), 10000), ]
set.seed(10)

indexTrain <- createDataPartition(newSet$Price, p = 0.8, list = FALSE)

carTrain <- newSet[indexTrain,]
carTest <- newSet[-indexTrain,]


set.seed(20)
fitControl <- trainControl(method = "repeatedcv", number = 10, repeats = 4)

set.seed(30)
fit <- train(Price ~. -Vin, data = carTrain, method = "knn", trControl = fitControl, na.action = na.omit)

