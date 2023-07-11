#!/bin/bash

echo "Hello World"

export SPRING_DATA_MONGODB_URI="mongodb://localhost:27017/shows"

mvn spring-boot:run
