package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        jsonObjectSerialization();

        Person person = new Person(20, true, "firstname", "secondname",
                new Address("city", "street", 21L, 81),
                new Pet[]{new Pet("name1", 3), new Pet("name2", 1)});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        final String personJson = "{\"age\":22,\"sex\":false,\"firstName\":\"firstname1\",\"lastName\":\"lastname\",\"address\":{\"city\":\"city1\",\"street\":\"street2\",\"houseNumber\":1,\"flatNumber\":5},\"pets\":[{\"name\":\"name3\",\"age\":2},{\"name\":\"name4\",\"age\":5}]}";
        final Person person1 = gson.fromJson(personJson, Person.class);
        System.out.println(person1);
    }

    public static void jsonObjectSerialization() {
        Person person = new Person(20, true, "firstname", "secondname",
                new Address("city", "street", 21L, 81),
                new Pet[]{new Pet("name1", 3), new Pet("name2", 1)});
        final String personJson = "{\"age\":50,\"sex\":false,\"firstName\":\"firstname2\",\"lastName\":\"lastname1\",\"address\":{\"city\":\"city2\",\"street\":\"street3\",\"houseNumber\":3,\"flatNumber\":7},\"pets\":[{\"name\":\"name4\",\"age\":4},{\"name\":\"name5\",\"age\":8}]}";
        final JSONObject jsonPerson = new JSONObject(personJson);
        final JSONObject jsonPerson1 = new JSONObject();
        System.out.println("json object");
        System.out.println(jsonPerson);

        jsonPerson1.put("age", person.getAge());
        jsonPerson1.put("sex", person.isSex());
        jsonPerson1.put("firstName", person.getFirstName());
        jsonPerson1.put("lastName", person.getLastName());
        jsonPerson1.put("Address", person.getAddress());
        jsonPerson1.put("Pets", person.getPets());

        System.out.println(jsonPerson1.toString());
    }
}
