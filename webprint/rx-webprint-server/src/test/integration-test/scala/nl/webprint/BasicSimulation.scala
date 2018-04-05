package nl.webprint

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

/**
  * A Gatling simulation to measure the performance and correctness of CRUD-operations on the REST-endpoint.
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

        /*
    // A scenario is a chain of requests and pauses
    val scn = scenario("Machine CRUD simulation scenario")
        // Necessary to prevent Akka-exception and wait till application is up
        .pause(SimulationConfig.INITIAL_WAIT_IN_SECONDS seconds)
        // Part 1: add new printing jobs
        .exec(new AddPrintingJob("/dummyfile.txt").addPrintingJob)
        // Part 2: find printing job by id that was returned
        // Part 3: start printing job
        // Part 4: complete printing job
        // Part 5: delete printing job
        ;

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
		*/
}

object SimulationConfig {
    val MAX_USERS = 1; //100;
    val INITIAL_WAIT_IN_SECONDS = 1;
    val INTERMEDIATE_WAIT_TIME_IN_MILLIS = 600;
    val MAX_SIMULATION_TIME_IN_SECONDS = 60; // 1 minute at most
}

