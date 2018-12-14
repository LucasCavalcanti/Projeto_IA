library(readr)
library(caret)
library(rpart)
library(rpart.plot)
library(Metrics)

true_car_listings <- read_csv("/home/lucas/Documents/true_car_listings.csv")
newSet <- true_car_listings[sample(nrow(true_car_listings), 10000), ]
newSet$City <- NULL
newSet$State <- NULL
newSet$Vin <- NULL
newSet$Make <- NULL
newSet$Model <- NULL

indexTrain <- createDataPartition(newSet$Price, p = 0.8, list = FALSE)

car.train <- newSet[indexTrain,]
car.test <- newSet[-indexTrain,]


ctrl <- trainControl(method = "cv", number = 10)


model.knn <- train(Price ~., data = car.train, method = "knn", trControl = ctrl, na.action = na.omit)
model.knn
ggplot(model.knn)

knn.predict <- predict(model.knn, car.test)

knn.results <- data.frame(car.test$Price, knn.predict)
knn.results

model.lm <- train(Price ~., data = car.train, method = "lm", trControl = ctrl, na.action = na.omit)
model.lm

lm.predict <- predict(model.lm, car.test)

lm.results <- data.frame(car.test$Price, lm.predict)
lm.results

decision_tree_model <- rpart(Price ~., data = car.train);
rpart.plot(decision_tree_model)
text(decision_tree_model, pretty = 0, cex = 0.6)
test_tree_predict = predict(decision_tree_model, newdata = car.test)
rmse.tree <- rmse(car.test$Price, test_tree_predict)
rmse.tree

mods <- resamples(list(knn = model.knn, lm = model.lm))
summary(mods)

