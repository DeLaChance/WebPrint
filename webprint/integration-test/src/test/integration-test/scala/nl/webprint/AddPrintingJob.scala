package nl.webprint;

import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
 * This object can make a HTTP-call to add a printing job.
 *
 */
class AddPrintingJob(fileName: String) {

    val filePath = getClass.getClassLoader.getResource("dummyfile.txt").toURI.toString
      .replaceAll("file:", "");

    println("Using filePath=" + filePath);

  	val addPrintingJob = exec(
        http("Add a printing job")
            .post(Config.POST_JOB)
            .bodyPart(
                RawFileBodyPart("file", filePath)
                  .fileName(fileName)
                ).asMultipartForm
                .check(status.is(200)
            )
            .check(jsonPath("$..identifier.identifier").ofType[String].saveAs("identifier"))
    ).exec(session => {
        if( session.contains("identifier") )
            session
        else
            session.markAsFailed
    }).exitHereIfFailed;
}
