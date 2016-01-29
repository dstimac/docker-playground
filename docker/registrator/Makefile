CONTAINER_NAME="registrator"
HOST="node"
MY_IP=$(shell ifconfig eth0 | grep -Eo 'inet (addr:)?([0-9]*\.){3}[0-9]*' | grep -Eo '([0-9]*\.){3}[0-9]*' | grep -v '127.0.0.1' | awk '{print $1}')
CONTAINER_ID=$$(docker ps -a | grep $(CONTAINER_NAME) | awk '{ print $$1 }')

run:
	docker run -d \
		--hostname=$(HOST) \
		--name=$(CONTAINER_NAME) \
		--volume=/var/run/docker.sock:/tmp/docker.sock \
		gliderlabs/registrator:latest \
		consul://$(MY_IP):8500

stop:
	docker stop $(CONTAINER_ID)
	
rm: stop
	docker rm $(CONTAINER_ID)
