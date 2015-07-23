package com.movile.up.seriestracker.interfaces.rest;

import com.movile.up.seriestracker.configuration.ApiConfiguration;
import com.movile.up.seriestracker.model.models.Episode;
import com.movile.up.seriestracker.model.models.Show;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by root on 22/07/15.
 */
public interface ShowRemoteService {
    @Headers({

            "trakt-api-version: "+ ApiConfiguration.API_VERSION,
            "trakt-api-key: " + ApiConfiguration.API_KEY

    })

    @GET("/shows/{show}?extended=full,images")
    void getShowDetails(
            @Path("show") String show,
            Callback<Show> callback);

}
