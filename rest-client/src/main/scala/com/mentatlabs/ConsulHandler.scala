package com.mentatlabs

import com.google.common.net.HostAndPort
import com.orbitz.consul.Consul

import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._

/**
  * Created by davor on 1/28/16.
  */
class ConsulHandler {
  val logger = LoggerFactory.getLogger(classOf[ConsulHandler])

  val service = "docker-playground-rest"  // TODO FROM CONFIG
  val consul = Consul.builder()
    .withHostAndPort(HostAndPort.fromParts("192.168.251.67", 8500)) // TODO FROM CONFIG
    .build()

  def getNodes: Seq[Node] = {
    val hc = consul.healthClient()
    hc.getHealthyServiceInstances(service).getResponse.asScala map { e =>
      Node(e.getService.getAddress, e.getService.getPort)
    }
  }
}
case class Node(address: String, port: Int)