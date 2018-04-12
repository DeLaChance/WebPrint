package nl.webprint;

/**
  * Global configuration for all simulations.
  *
  * This object contains the default port numbers and (base) url's.
  *
  */
object Config {

    val PORT_NUMBER = 8181;

    val BASE_URL = "http://127.0.0.1:" + PORT_NUMBER;

    val GET_JOBS = BASE_URL + "/api/job/";
    val FIND_JOB_BY_ID = BASE_URL + "/api/job/:jobId";
    val POST_JOB = BASE_URL + "/api/job/";
    val DELETE_JOB_BY_ID = BASE_URL + "/api/job/:jobId";

    val START_JOB_BY_ID = BASE_URL + "/api/job/:jobId/start";
    val COMPLETE_JOB_BY_ID = BASE_URL + "/api/job/:jobId/complete";

}
