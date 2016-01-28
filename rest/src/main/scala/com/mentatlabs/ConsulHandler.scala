package com.mentatlabs


import java.net.{Inet4Address, InetAddress}
import java.util.concurrent.Executors

import scala.concurrent.ExecutionContext

/**
  * Created by davor on 1/28/16.
  */
object ConsulHandler {

  implicit val executor = ExecutionContext.fromExecutor(Executors.newCachedThreadPool())
  val addr = InetAddress.getByName("localhost").asInstanceOf[Inet4Address]
  val myConsul = new consul.Consul(addr)(executor)
  import myConsul.v1._


  def register(): Unit = {
    val myServiceCheck = agent.service.httpCheck(s"http://localhost:8080/health", "15s")
    val myService = agent.service.LocalService(ServiceId("PingerID1"), ServiceType("pinger"), Set(ServiceTag("pinger")), Some(8080), Some(myServiceCheck))
    agent.service.register(myService)
  }

}
