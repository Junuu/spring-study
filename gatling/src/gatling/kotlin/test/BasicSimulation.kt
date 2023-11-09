package test

import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http

class BasicSimulation : Simulation() { // 3

  val httpProtocol = http // 4
    .baseUrl("http://localhost:8080") // 5
    .doNotTrackHeader("1")

  val scn = scenario("BasicSimulation") // 7
    .exec(
      http("request_1") // 8
        .get("/stress-test")
    ) // 9
    .pause(5) // 10

  init {
    setUp( // 11
      scn.injectOpen(
//        atOnceUsers(60)
        constantUsersPerSec(60.0).during(60)
      )
    ).protocols(httpProtocol) // 13
  }
}
