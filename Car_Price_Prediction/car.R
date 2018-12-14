library(readr)
library(caret)
library(rpart)
library(rpart.plot)
library(Metrics)

# Importing dataset
true_car_listings <- read_csv("/home/lucas/Documents/true_car_listings.csv")

newSet <- true_car_listings[sample(nrow(true_car_listings), 10000), ]
newSet$City <- NULL
newSet$State <- NULL
newSet$Vin <- NULL
newSet$Make <- NULL
newSet$Model <- NULL

# Splitting the dataset in training set and testing set
indexTrain <- createDataPartition(newSet$Price, p = 0.8, list = FALSE)
car.train <- newSet[indexTrain,]
car.test <- newSet[-indexTrain,]


ctrl <- trainControl(method = "repeatedcv", number = 5, repeats = 10)

# Training and predict a knn model
k <- expand.grid(k = seq(20, 100, length=20))
model.knn <- train(Price ~., data = car.train, method = "knn", trControl = ctrl,tuneGrid = k, na.action = na.omit)
model.knn
ggplot(model.knn)

knn.predict <- predict(model.knn, car.test)

knn.results <- data.frame(car.test$Price, knn.predict)
ggplot(knn.results)

# Linear Regression
model.lm <- train(Price ~., data = car.train, method = "lm", trControl = ctrl, na.action = na.omit)
model.lm

lm.predict <- predict(model.lm, car.test)

lm.results <- data.frame(car.test$Price, lm.predict)
lm.results

ggplot(lm.results)

# Decision Tree
decision_tree_model <- train(Price ~.,
                  data = car.train,
                  method = "rpart",
                  trControl = ctrl)
tree.predict <- predict(decision_tree_model, car.test)

ggplot(decision_tree_model)

# Training Analising
mods <- resamples(list(knn = model.knn, lm = model.lm, rpart = decision_tree_model))
summary(mods)

# Test Analising
postResample(pred = knn.predict, obs = newSet$Price)
postResample(pred = lm.predict, obs = newSet$Price)
postResample(pred = tree.predict, obs = newSet$Price)
