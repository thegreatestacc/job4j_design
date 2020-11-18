package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        Person person = new Person(20, true, "firstname", "secondname",
                new Address("city", "street", 21L, 81),
                new Pet[]{new Pet("name1", 3), new Pet("name2", 1)});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        final String personJson = "{\"age\":22,\"sex\":false,\"firstName\":\"firstname1\",\"lastName\":\"lastname\",\"address\":{\"city\":\"city1\",\"street\":\"street2\",\"houseNumber\":1,\"flatNumber\":5},\"pets\":[{\"name\":\"name3\",\"age\":2},{\"name\":\"name4\",\"age\":5}]}";
        final Person person1 = gson.fromJson(personJson, Person.class);
        System.out.println(person1);
    }
}
