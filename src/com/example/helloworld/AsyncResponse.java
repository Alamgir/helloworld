package com.example.helloworld;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by Alamgir on 9/26/2014.
 */
public interface AsyncResponse {

    /**
     * The task is finished with a JsonNode result - probably from an API call to a third-party endpoint
     * @param result
     */
    void taskFinished(JsonNode result);

    /**
     * The task is finished with a String Array
     * @param result
     */
    void taskFinished(String result);

}
