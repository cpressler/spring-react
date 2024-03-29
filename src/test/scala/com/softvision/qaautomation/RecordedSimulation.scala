package com.softvision.qaautomation

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	// update to any passed in base url via jvm args
	val targetUrl = sys.props.getOrElse("targetUrl", "http://localhost:9000")

	val httpProtocol = http
		.baseUrl(targetUrl)
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.9")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36")

	val headers_2 = Map(
		"Accept" -> "application/json",
		"Content-Type" -> "application/json",
		"Origin" -> "http://localhost:9000")



	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("/api/groups"))
		.pause(2)
		.exec(http("request_1")
			.get("/api/groups/1"))
		.pause(4)
		.exec(http("request_2")
			.put("/api/groups/1")
			.headers(headers_2)
			.body(RawFileBody("gatling/RecordedSimulation_0002_request.txt"))
			.resources(http("request_3")
			.get("/api/groups")))

//	setUp(scn.inject(atOnceUsers(50))).protocols(httpProtocol)

	setUp(
		scn.inject(rampUsers(30) during (10 seconds))).protocols(httpProtocol)

}