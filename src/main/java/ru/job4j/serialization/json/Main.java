package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.java.Contact;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7 913 052 49 28\"}");

        List<String> listStatuses = new ArrayList<>();
        listStatuses.add("Student");
        listStatuses.add("Free");
        JSONArray jsonStatuses = new JSONArray(listStatuses);

        JSONObject jsonPerson = new JSONObject();
        final Person person = new Person(false, 40,
                new Contact(11111, "+7 913 052 49 28"),
                new String[] {"Worker", "Free"});
        jsonPerson.put("sex", person.getSex());
        jsonPerson.put("age", person.getAge());
        jsonPerson.put("contact", jsonContact);
        jsonPerson.put("statuses", jsonStatuses);

        List<Integer> listSize = new ArrayList<>();
        listSize.add(52);
        listSize.add(165);
        listSize.add(42);
        listSize.add(58);
        JSONArray jsonSize = new JSONArray(listSize);

        final Worker worker = new Worker(true, 6, "Engineer",
                new Person(false, 40, new Contact(11111, "+7 913 052 49 28"),
                        new String[] {"Worker", "Free"}), new int[] {52, 165, 42, 58});

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("q", worker.isQ());
        jsonObject.put("experience", worker.getExperience());
        jsonObject.put("post", worker.getPost());
        jsonObject.put("person", jsonPerson);
        jsonObject.put("size", jsonSize);

        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(worker).toString());
    }
}