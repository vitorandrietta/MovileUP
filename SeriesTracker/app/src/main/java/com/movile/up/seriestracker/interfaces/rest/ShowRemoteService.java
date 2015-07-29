package com.movile.up.seriestracker.interfaces.rest;
import com.movile.up.seriestracker.util.ApiConfiguration;
import com.movile.up.seriestracker.model.models.Show;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;


/**
 * Created by root on 22/07/15.
 */
public interface ShowRemoteService {

    @Headers({
            "trakt-api-key: " + ApiConfiguration.API_KEY,
            "trakt-api-version: " + ApiConfiguration.API_VERSION
    })

    @GET("/shows/{show}?extended=full,images")
    void getShowDetails(
            @Path("show") String show,
            Callback<Show> callback);

    @Headers({
            "trakt-api-key: " + ApiConfiguration.API_KEY,
            "trakt-api-version: " + ApiConfiguration.API_VERSION
    })
    @GET("/shows/popular?limit=1000&extended=full,images")
    void getShows(
            Callback<List<Show>> callback);
}
