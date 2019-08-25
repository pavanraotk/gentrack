# Gentrack interview

This project has the following components:
1. Spring boot application
2. Cloudformation for s3 bucket, RDS and to deploy the spring-boot application on the AWS.

## Pre requisites:

1. Maven is installed.
2. AWS cli is installed.

## Build:

Run command ```mvn clean install```

## Deploy application in AWS:

Run the command to create s3 bucket: ```aws cloudformation create-stack --stack-name=meter-s3 --template-body file:///cloudformation/cloudformation-s3-bucket.json```

Run the command to create the RDS: ```aws cloudformation create-stack --stack-name=meter-rds --template-body file://cloudformation.json```

Change the application.properties to point to the RDS URL: `spring.datasource.url` 

Run the command to upload the application jar to the S3 bucket: ```aws s3 cp target/meter-reader-0.0.1-SNAPSHOT.jar s3://meter-s3-bucket/meter-reader-0.0.1-SNAPSHOT.jar```

Run the command to create the application in EC2 instance: ```aws cloudformation create-stack --stack-name=meter-app --template-body file://cloudformation/cloudformation-ec2.json --capabilities CAPABILITY_IAM```


## Postman collection attached:

* Add a new meter
* Add a new meter reading
* Get meter

## Changes if given longer time:

* Make the add new meter, add meter reading asynchronous by adding an AWS SQS queue, a listener, currently it's synchronous.
* Add more tests to the application.
* CD using code-deploy/own pipeline using concourse or jenkins.

