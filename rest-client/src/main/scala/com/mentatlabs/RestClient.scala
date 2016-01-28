package com.mentatlabs

import java.net.NetworkInterface

import com.ning.http.client.AsyncHandler.STATE
import com.ning.http.client._
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

/**
  * Created by davor on 1/28/16.
  */
object RestClient extends App {
  val logger = LoggerFactory.getLogger(RestClient.getClass)
  val nodes = new ConsulHandler().getNodes

  val client = new AsyncHttpClient()
  val builder = new RequestBuilder("PUT")

  val req = nodes.headOption match {
    case Some(node) => builder.setUrl(s"http://${node.address}:${node.port}/ping")
      .setQueryParams(List(new Param("id", "")).asJava).build()
    case _ => throw new RuntimeException()
  }

  nodes foreach println

  while(true) {
    client.executeRequest(req, new MyAssyncHandler(getEth0Addrs.head))
    Thread.sleep(3000)
  }

  def getEth0Addrs: List[String] = {
    val ni = NetworkInterface.getByName("eth0")
    val inetAddrs = ni.getInetAddresses
    val lb = new ListBuffer[String]

    while(inetAddrs.hasMoreElements) {
      val addr = inetAddrs.nextElement()
      if(!addr.isLinkLocalAddress) lb += addr.getHostAddress
    }
    lb.result
  }

  class MyAssyncHandler(id: String) extends AsyncHandler[Unit] {
    val logger = LoggerFactory.getLogger(classOf[MyAssyncHandler])

    override def onCompleted(): Unit = {
      logger.info("Request for di [{}] completed..", id)
    }

    override def onThrowable(t: Throwable): Unit = {
      logger.error("Error: ", t)
    }

    override def onBodyPartReceived(bodyPart: HttpResponseBodyPart): STATE = STATE.CONTINUE

    override def onStatusReceived(responseStatus: HttpResponseStatus): STATE = STATE.CONTINUE

    override def onHeadersReceived(headers: HttpResponseHeaders): STATE = STATE.CONTINUE
  }
}
