package com.yube.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

public class JsonAdapter {

    private final Gson gson;

    public JsonAdapter() {
        this.gson = getGson();
    }

    public <T> T getObject(String jsonObject, Class<T> clazz) {
        return gson.fromJson(jsonObject, clazz);
    }

    public <T> List<T> getList(String jsonArray, Class<T> clazz) {
        Type typeOfT = TypeToken.getParameterized(List.class, clazz).getType();
        return gson.fromJson(jsonArray, typeOfT);
    }

    private Gson getGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) ->
                new Date(json.getAsJsonPrimitive().getAsLong()));
        return builder.create();
    }
}
