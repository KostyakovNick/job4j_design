package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.serialization.java.Contact;

public class Main {
    public static void main(String[] args) {
        final Worker worker = new Worker(true, 6, "Engineer",
                new Person(false, 40, new Contact(11111, "+7 913 052 49 28"),
                        new String[] {"Worker", "Free"}), new int[] {52, 165, 42, 58});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(worker));

        final String workerJson =
                "{"
                        + "\"q\":true,"
                        + "\"experience\":6,"
                        + "\"post\":\"Engineer\","
                        + "\"person\":"
                        + "{"
                        + "\"sex\":false,"
                        + "\"age\":40,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7 913 052 49 28\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Worker\",\"Free\"]"
                        + "},"
                        + "\"size\":"
                        + "[52, 165, 42, 58]"
                        + "}";
        final Worker workerMod = gson.fromJson(workerJson, Worker.class);
        System.out.println(workerMod);
    }
}