.PHONY: build
IMAGE_NAME="dstimac/docker-playground-rest"

## Build image from Dockerfile
build:
	docker build --rm -t $(IMAGE_NAME) .

## Push image to hub
push:
	docker push $(IMAGE_NAME)

## Run container with default command
run: 
	docker run -d -it \
		--net=host \
		-p 127.0.0.1:8080:8080 \
		--log-driver=syslog \
		$(IMAGE_NAME)

## Run container and expose provided port
run-%: 
	docker run -d -it \
		--net=host \
		-p 127.0.0.1:$*:8080 \
		--log-driver=syslog \
		$(IMAGE_NAME)
