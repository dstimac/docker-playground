CONTAINER_NAME="consul-server"
BRIDGE_IP="172.17.0.1"
HOST="node"
MY_IP=$(shell ifconfig eth0 | grep -Eo 'inet (addr:)?([0-9]*\.){3}[0-9]*' | grep -Eo '([0-9]*\.){3}[0-9]*' | grep -v '127.0.0.1' | awk '{print $1}')
CONTAINER_ID=$$(docker ps -a | grep $(CONTAINER_NAME) | awk '{ print $$1 }')

run:
	docker run -d \
		--hostname=$(HOST) \
		-p $(MY_IP):8300:8300 \
		-p $(MY_IP):8301:8301 \
		-p $(MY_IP):8301:8301/udp \
		-p $(MY_IP):8302:8302 \
		-p $(MY_IP):8302:8302/udp \
		-p $(MY_IP):8400:8400 \
		-p $(MY_IP):8500:8500 \
 		-p $(BRIDGE_IP):53:53/udp \
		--name=$(CONTAINER_NAME) \
		gliderlabs/consul-server -advertise $(MY_IP) -bootstrap $(ARGS)

stop:
	docker stop $(CONTAINER_ID)
	
rm: stop
	docker rm $(CONTAINER_ID)