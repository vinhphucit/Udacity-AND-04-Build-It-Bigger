/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.phuctran.backend;

import com.jokeprovider.JokeProvider;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "jokeapi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.phuctran.com",
                ownerName = "backend.phuctran.com",
                packagePath = ""
        )
)
public class MyEndpoint {
    JokeProvider jokeProvider = new JokeProvider();

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "tellJoke", httpMethod = ApiMethod.HttpMethod.GET)
    public MyBean tellJoke() {
        MyBean response = new MyBean();
        response.setData(jokeProvider.getJoke());

        return response;
    }

}
