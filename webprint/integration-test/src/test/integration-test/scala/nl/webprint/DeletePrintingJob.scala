package nl.webprint;

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object DeletePrintingJob {

  val deletePrintingJob = exec(
    http("Delete a printing job")
        .delete(session =>
          Config.DELETE_JOB_BY_ID.replaceAll(":jobId", session("identifier").as[String])
        )
        .check(status.is(200))
    );
}
