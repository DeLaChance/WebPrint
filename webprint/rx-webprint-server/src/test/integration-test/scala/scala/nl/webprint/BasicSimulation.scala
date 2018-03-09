package nl.webprint

import nl.webprint.Config
import com.fasterxml.jackson.databind.ObjectMapper
import com.jcabi.ssh.{Shell, SshByPassword}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.apache.commons.lang3.StringUtils

import scala.collection.JavaConversions._
import scala.concurrent.duration._

/**
  * A simulation to do CRUD-operations on the REST endpoint.
  *
  **/
class BasicSimulation extends Simulation {


    val httpConf = http
        .baseURL(Config.BASE_URL)
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("en-US,en;q=0.5")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")
        .disableWarmUp

    // A scenario is a chain of requests and pauses
    val scn = scenario("Machine CRUD simulation scenario")
        // Necessary to prevent Akka-exception and wait till application is up
        .pause(SimulationConfig.INITIAL_WAIT_IN_SECONDS seconds)
        // Part 1: add new printing jobs
        .exec(AddPrintingJob.addPrintingJob);
        // Part 2: find printing job by id that was returned
        // Part 3: start printing job
        // Part 4: complete printing job
        // Part 5: delete printing job


    setUp(
        scn.inject(atOnceUsers(SimulationConfig.MAX_USERS))
            .protocols(httpConf)
    )
    .maxDuration((SimulationConfig.MAX_SIMULATION_TIME_IN_SECONDS) seconds)
    .assertions(global.successfulRequests.percent.gt(98.0))
    .assertions(global.responseTime.mean.lt(600));

    before {
    	// Archive all existing printing jobs
    }

    after {
    	// Move data back from archive
    }

}

object SimulationConfig {
    val MAX_USERS = 1; //100;
    val INITIAL_WAIT_IN_SECONDS = 1;
    val INTERMEDIATE_WAIT_TIME_IN_MILLIS = 600;
    val MAX_SIMULATION_TIME_IN_SECONDS = 60; // 1 minute at most
}


/*
Host: localhost:8181

Referer: http://localhost:8181/static/
Cache-Control: no-cache
X-Requested-With: XMLHttpRequest
Content-Length: 209815
Content-Type: multipart/form-data; boundary=---------------------------169761768719568529011904878927
Connection: keep-alive
*/
object AddPrintingJob {
    
  	val headers = Map(
	  "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
	  "Content-Type" -> "multipart/form-data; boundary=---------------------------169761768719568529011904878927"
	);

    val addPrintingJob = exec(
        http("Add a mock machine")
        	.headers(headers)
            .post(Config.POST_JOB)
            .bodyPart(RawFileBodyPart("file", "dummyfile.txt")
                .fileName("dummyfile.txt")
                .transferEncoding("UTF-8")).asMultipartForm
            )
            .check(status.is(200))
            .check(jsonPath("$..identifier.identifier").ofType[String].saveAs("identifier"))
    ).exec(session => {
        if( session.contains("identifier") )
            session
        else
            session.markAsFailed
    }).exitHereIfFailed;
}

object MapToJSON {

    val objectMapper = new ObjectMapper();

    def convert(map: Map[String, String]): String = {
        val output = objectMapper.writeValueAsString(mapAsJavaMap(map));
        return output;
    }

}
