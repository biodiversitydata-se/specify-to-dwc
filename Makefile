#!make
PWD=$(shell pwd)

all: up

.PHONY: all

init:
	@echo "working directory is ${PWD}"


build:
	docker build -t nrm/solr:v0.1 .

up:
	docker-compose -f docker-compose.yml up
# 	docker-compose -f docker-compose.yml up -d

down:
	docker-compose -f docker-compose.yml down


# docker login
# release:
# 	docker push dina/solr:v0.3
