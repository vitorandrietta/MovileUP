package com.movile.up.seriestracker.business;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.model.converters.ModelConverter;
import com.movile.up.seriestracker.model.models.Episode;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.sql.Connection;

/**
 * Created by android on 7/16/15.
 */
public class FetchEpisodeRemote {


    private static final String TAG = FetchEpisodeRemote.class.getSimpleName();
    private static final String ASSET_NAME = "episode.json";


    public Episode get(Context context) {
        Episode episode = null;
        InputStreamReader reader = null;

        try {
            String url = context.getString(R.string.api_url_base).
                    concat(context.getString(R.string.api_url_episode)).concat("?extended=full,images");
            HttpURLConnection connection = ConnectionManager.configureConnection(url, context, "GET");
            InputStream stream = null;
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                stream = connection.getInputStream();
                reader = new InputStreamReader(stream);
                episode = new ModelConverter().toEpisode(reader);
            }
            reader = new InputStreamReader(stream);
            episode = new ModelConverter().toEpisode(reader);
        } catch (IOException e) {
            Log.e(TAG, "Error loading local content from file", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error releasing resource", e);
                }
            }
        }

        return episode;
    }

}
