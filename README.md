# sd-csof
[![Build Status](https://travis-ci.org/oharsta/funactic-workshop.svg)](https://travis-ci.org/oharsta/funactic-workshop)
[![codecov.io](https://codecov.io/github/oharsta/funactic-workshop/coverage.svg)](https://codecov.io/github/oharsta/funactic-workshop)

Funactic Workshop

## [Getting started](#getting-started)

`git checkout step-1`

### [System Requirements](#system-requirements)

- Java 8
- Maven 3
- MongoDB 3.4.x
- yarn 1.17
- NodeJS v8.16.0 (best managed with `nvm`, current version in [.nvmrc](client/.nvmrc) or do `brew switch node 8.16.0`)
- ansible

## [Building and running](#building-and-running)

### Run server and client

### [The server](#server)

This project uses Spring Boot and Maven. To run locally, type:

`cd server`

`mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=dev"`

When developing, it's convenient to just execute the applications main-method, which is in [Application](server/src/main/java/csof/CsofApplication.java). Don't forget
to set the active profile to dev.
```
-Dspring.profiles.active=dev
```

### [The client](#client)

The client is build with react and to get initially started:

```
cd client
yarn install
yarn start
```

Browse to the [application homepage](http://localhost:3000/).

To add new dependencies:

`yarn add package --dev`

When new yarn dependencies are added:

`yarn install`

To run all JavaScript tests:
```
cd client
yarn test
```

Or to run all the tests and do not watch - like CI:
```
cd client
CI=true yarn test
```

## [Ansible playbook](#ansible_playbook)

### Testing against local docker container

```
docker run -it --name ubuntu_container ubuntu:bionic bash
unminimize
<ctr-d>

docker commit ubuntu_container ubuntu:unminimize
docker stop ubuntu_container 
docker rm ubuntu_container 
docker run -p 80:80 --name ubuntu_container  -td ubuntu:unminimize
ansible-playbook -i inventory/local -K playbook.yml -e @group_vars/local.yml
```
Add the following line to your `etc/hosts` file:
```
127.0.0.1       csof.local
```
Connect to the docker image:
```
docker exec -it ubuntu_container bash
```
