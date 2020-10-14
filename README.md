# SPECIFY TO DWC
[![Build Status](https://travis-ci.com/Naturhistoriska/specify-to-dwc.svg?branch=master)](https://travis-ci.com/Naturhistoriska/specify-to-dwc)
[![codecov](https://codecov.io/gh/Naturhistoriska/specify-to-dwc/branch/master/graph/badge.svg)](https://codecov.io/gh/Naturhistoriska/specify-to-dwc)
[![License: AGPL v3](https://img.shields.io/badge/License-AGPL%20v3-blue.svg)](http://www.gnu.org/licenses/agpl-3.0)


Build solr index for data from Swedish Museum of Natural History Specify database.


## Prerequisites

* docker
* java 8
* maven 3
* mysql
* solr8


## Project setup

* Clone this repo
* Setup specify database
* Setup mapping.json file
  * cd mapping_files, config mapping.json for collections want to build index in solr
* Setup project-initdata.yml
  * In root directory, create project-initdata.yml.
  * Config project-initdata.yml file for solr instance path, mapping_files path and mysql database.


## Build image


In root directory, run:
```
mvn clean package
cd specify-data-service
make build
```

# Start image


After build image, run:
```
docker-compose up -d
```

# Processing specify data to solr, run:

curl -X GET 'http://localhost:8180/run?inst={institutionCode}&collCode={collectionNumber}&from={fromDate}&to={toDate}'

Example: curl -X GET 'http://localhost:8180/run?inst=nrm&collCode=163840&from=2018-02-14&to=2018-02-15'
