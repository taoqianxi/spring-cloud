package com.tao;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.LongSerializationPolicy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class GsonUtil {

    private static final GsonBuilder builder;

    static {
        builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
//        builder.setPrettyPrinting();
//        builder.serializeSpecialFloatingPointValues();
        builder.disableHtmlEscaping();
        builder.setLenient();
        builder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
        builder.registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>)
                (o, type, jsonSerializationContext) -> new JsonPrimitive(o.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        builder.registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>)
                (date, type, jsonSerializationContext) -> new JsonPrimitive(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        builder.registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (jsonElement, type, jsonDeserializationContext) -> {
            String datetime = jsonElement.getAsJsonPrimitive().getAsString();
            return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        });
        builder.registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (jsonElement, type, jsonDeserializationContext) -> {
            String datetime = jsonElement.getAsJsonPrimitive().getAsString();
            return LocalDate.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        });
    }

    public static <T> T fromJsonToObject (String json, Class<T> type){
        Gson gson = builder.create();
        return gson.fromJson(json,type);
    }

    public static <T> T fromJsonToObject (String json, java.lang.reflect.Type type){
        Gson gson = builder.create();
        return gson.fromJson(json,type);
    }

    public static <T> String fromObjectToJson(T Object){
        Gson gson = builder.create();
        return gson.toJson(Object);
    }



}
