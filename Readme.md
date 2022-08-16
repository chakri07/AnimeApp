#StackFinal

This is the master repo for full stack final project
This repo contains multiple submodules

1. androidretro - android client
2. awsContact - aws SES service which enables developer to receive emails from users
3. quarkusRest - the brain of our application based on graalvm and quarkus architecture.
   

AndroidRetro:
- Nothing special needed to build this
- Open in android studio and build it 

AwsContact: (instructions to deploy a new cloud formation stack)
- cd in ./awscontact/emailer
- open a terminal 
- run sam build
- run sam deploy ( --guided is not needed as I stored the config in a .toml file)

quarkusRest - (instruction to run locally)
- cd into ./quarkusRest
- make sure docker is running in background
- start a mongo instance using "docker run -ti --rm -p 27017:27017 mongo:4.4"
- You can run the java backend in two ways both need mongo db running in background
- Using Docker:
  - build the docker image using "docker build -f src/main/docker/Dockerfile.jvm -t <docker_hub_username>/quarkus-jvm ."
  - run the docker image using "docker run -i --rm -p 8080:8080 <docker_hub_username>/quarkus-jvm"
- Using intellij:
  - Open the project in intellij and launch from intellij for developing purposes.

quarkus in prod is deployed in aws light sail


URLS: 

API = "https://animechan.vercel.app/"

AwsLightsailURL = "https://fullstack-final.9s4t2ca60nlac.us-east-1.cs.amazonlightsail.com/"

AwsSESUrl = "https://lkdzggekod.execute-api.us-east-1.amazonaws.com/Prod/"
