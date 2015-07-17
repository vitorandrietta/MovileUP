package com.movile.up.seriestracker.business.connection;
import android.content.Context;
import android.content.res.Resources;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.configuration.ApiConfiguration;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by android on 7/16/15.
 */
public class ConnectionManager {


    public static HttpURLConnection configureConnection(String url, Context context, String requestVerb) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.setReadTimeout(context.getResources().getInteger(R.integer.api_timeout_read));
        connection.setConnectTimeout(context.getResources().getInteger(R.integer.api_timeout_connect));
        try {
            connection.setRequestMethod(requestVerb);
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("trakt-api-version",ApiConfiguration.API_VERSION);
        connection.setRequestProperty("trakt-api-key", ApiConfiguration.API_KEY);
        return connection;
    }
}
