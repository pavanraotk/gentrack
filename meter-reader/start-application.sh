#!/usr/bin/env bash
aws s3 cp s3://{bucket with code}/meter-reader-0.0.1-SNAPSHOT.jar /home/ec2-user/meter-reader-0.0.1-SNAPSHOT.jar
sudo yum -y install java-1.8.0
sudo yum -y remove java-1.7.0-openjdk
cd /home/ec2-user/
sudo nohup java -jar meter-reader-0.0.1-SNAPSHOT.jar > ec2dep.log