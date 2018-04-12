package nl.webprint;

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object FindPrintingJobById {

  val findPrintingJobById = exec(
      http("Find a printing job by id")
          .get(session =>
            Config.FIND_JOB_BY_ID.replaceAll(":jobId", session("identifier").as[String])
          )
          .check(status.is(200))
      );

}
