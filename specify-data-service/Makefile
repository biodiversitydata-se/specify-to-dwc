#!make

all: build

build:
	docker build -t dina/specify-service:v0.3.1 .

# docker login
release:
	docker push dina/specify-service:v0.3.1
