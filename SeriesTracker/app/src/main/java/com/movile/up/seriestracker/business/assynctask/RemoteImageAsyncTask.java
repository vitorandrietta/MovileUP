package com.movile.up.seriestracker.business.assynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.movile.up.seriestracker.interfaces.ImageLoader;

import java.io.IOException;
import java.net.URL;

public class RemoteImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

    private static final String TAG = RemoteImageAsyncTask.class.getSimpleName();
    private ImageLoader imageLoader;

    public RemoteImageAsyncTask(ImageLoader imageLoader){
        this.imageLoader = imageLoader;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String url = params[0];
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(new URL(url).openStream());
        } catch (IOException e) {
            Log.e(TAG, "Error fetching image from " + url, e);
        }
        return bitmap;
    }
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageLoader.LoadImage(bitmap);
    }
}
