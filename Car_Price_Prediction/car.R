library(readr)
library(caret)

true_car_listings <- read_csv("/home/lucas/Documents/true_car_listings.csv")

set.seed(10)
inTrain <- createDataPartition(true_car_listings$Price, p = 0.8, list = FALSE)

carTrain <- true_car_listings[inTrain,]
carTest <- true_car_listings[-inTrain,]

set.seed(825)
fitControl <- trainControl(method = "repeatedcv", number = 10, repeats = 4)

set.seed(12345)
knnFit <- train(Price ~ . - Vin, data = carTrain, method = "knn", trControl = fitControl,
                 verbose = FALSE)


