package nl.webprint;

import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
 * This object can make a HTTP-call to add a printing job.
 * 
 */
class AddPrintingJob(fileName: String) {
    
  	val headers = Map(
	  "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
	  "Content-Type" -> "multipart/form-data; boundary=---------------------------169761768719568529011904878927"
	);

    val addPrintingJob = exec(
        http("Add a printing job")
            .post(Config.POST_JOB)
        	  .headers(headers)
            .bodyPart(
                RawFileBodyPart("file", fileName)
                .fileName(fileName)
                .transferEncoding("UTF-8"))
                .asMultipartForm
            .check(status.is(200))
            .check(jsonPath("$..identifier.identifier").ofType[String].saveAs("identifier"))
    ).exec(session => {
        if( session.contains("identifier") )
            session
        else
            session.markAsFailed
    }).exitHereIfFailed;
}

