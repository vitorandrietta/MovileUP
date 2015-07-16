package com.movile.up.seriestracker.model.converters;


import com.google.gson.Gson;
import com.movile.up.seriestracker.model.models.Episode;
import com.movile.up.seriestracker.model.models.Season;
import com.movile.up.seriestracker.model.models.Show;

import java.io.Reader;

public class ModelConverter {

    private Gson mGson;

    public ModelConverter() {
        mGson = new Gson();
    }

    public Episode toEpisode(Reader reader) {
        return mGson.fromJson(reader, Episode.class);
    }

    public Show toShow(Reader reader) {
        return null;
    }

    public Season toSeason(Reader reader) {
        return null;
    }

}

