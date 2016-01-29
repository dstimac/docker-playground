.PHONY: build
IMAGE_NAME="dstimac/docker-playground-rest-client"
HOST="node"
BRIDGE_IP="172.17.0.1"
MY_IP=$(shell ifconfig eth0 | grep -Eo 'inet (addr:)?([0-9]*\.){3}[0-9]*' | grep -Eo '([0-9]*\.){3}[0-9]*' | grep -v '127.0.0.1' | awk '{print $1}')

## Build image from Dockerfile
build:
	pack.sh && docker build --rm -t $(IMAGE_NAME) .

## Push image to hub
push:
	docker push $(IMAGE_NAME)

## Run container with default command
run: 
	docker run -d -it \
		--hostname=$(HOST) \
		--log-driver=syslog \
		$(IMAGE_NAME)

## Run container and expose provided port
run-%: 
	docker run -d -it \
		--hostname=$(HOST)-$* \
		--log-driver=syslog \
		$(IMAGE_NAME)
