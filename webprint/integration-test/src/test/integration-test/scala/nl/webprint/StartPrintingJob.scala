package nl.webprint;

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object StartPrintingJob {

  val startPrintingJob = exec(
      http("Start a printing job by id")
          .post(session =>
            Config.START_JOB_BY_ID.replaceAll(":jobId", session("identifier").as[String])
          )
          .check(status.is(200))
      );

}
