package com.mentatlabs

import java.time.LocalDate
import java.util.concurrent.Executors

import akka.actor.ActorSystem
import org.slf4j.LoggerFactory
import spray.routing.SimpleRoutingApp

import scala.concurrent.ExecutionContext

/**
  * Created by davor on 1/28/16.
  */
object RestService extends App with SimpleRoutingApp {
  val logger = LoggerFactory.getLogger(RestService.getClass)

  private implicit val system = ActorSystem("docker-playground-rest")

  private implicit val executionContext =
    ExecutionContext.fromExecutor(Executors.newCachedThreadPool)

  val routes = {
    pathPrefix("ping") {
      pathEnd {
        get {
          parameter("id") { id =>
            complete {
              logger.info("Handling ping request [{}]...", id)
              "pong " + LocalDate.now()
            }
          }
        }
      }
    } ~
    pathPrefix("health") {
      pathEnd {
        get {
          complete {
            logger.debug("Handling health request...")
            ""
          }
        }
      }
    }
  }

  startServer(interface = "0.0.0.0", port = 8080) {
    detach()(routes)
  }
}