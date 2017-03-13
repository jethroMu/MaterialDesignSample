package example.jethro.com.materialdemo.utils;

import android.content.Context;
import android.util.Log;


import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import example.jethro.com.materialdemo.entity.AppData;
import example.jethro.com.materialdemo.entity.SimpleItem;

public class FileLoader {
    private static final String TAG = FileLoader.class.getSimpleName();

    public static List<SimpleItem> loadSampleItems(Context context) {

        String json;
        try {
            json = loadJson(context);
        } catch (IOException e) {
            Log.e(TAG, "Unable to load JSON", e);
            return null;
        }

        AppData appData = new Gson().fromJson(json, AppData.class);
        return appData.toSimpleItems();
    }

    private static String loadJson(Context context) throws IOException {
        StringBuilder buffer = new StringBuilder();
        InputStream inputStream = context.getAssets().open("top_google_charts.json");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String str;

        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }

        bufferedReader.close();

        return buffer.toString();
    }
}
