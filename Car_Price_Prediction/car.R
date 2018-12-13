library(readr)
library(caret)

true_car_listings <- read_csv("/home/lucas/Documents/true_car_listings.csv")
newSet <- true_car_listings[sample(nrow(true_car_listings), 10000), ]
newSet$City <- NULL
newSet$State <- NULL
newSet$Vin <- NULL
newSet$Model <- NULL
newSet$Make <- NULL
set.seed(10)

indexTrain <- createDataPartition(newSet$Price, p = 0.8, list = FALSE)

car.train <- newSet[indexTrain,]
car.test <- newSet[-indexTrain,]


set.seed(20)
ctrl <- trainControl(method = "cv", number = 10)

set.seed(30)
model.knn <- train(Price ~., data = car.train, method = "knn", trControl = ctrl, na.action = na.omit)
model.knn$results
plot(model.knn)

model.lm <- train(Price ~., data = car.train, method = "lm", trControl = ctrl, na.action = na.omit)
model.lm$results

predict(model.knn, car.test)
predict(model.lm, car.test)

mods <- resamples(list(knn = model.knn, lm = model.lm))
summary(mods)

