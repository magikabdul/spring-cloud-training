#!/bin/sh
cd configuration
mvn clean
mvn package
cd ../departments
mvn clean
mvn package
cd ../discovery
mvn clean
mvn package
cd ../gateway
mvn clean
mvn package
cd ../users
mvn clean
mvn package
cd ../zipkin
mvn clean
mvn package
cd ../client
rm -rf target
ng build --prod