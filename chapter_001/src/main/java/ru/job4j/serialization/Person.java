package ru.job4j.serialization;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Person implements Serializable {
    private final int age;
    private final boolean sex;
    private final String firstName;
    private final String lastName;
    private final Address address;
    private final Pet[] pets;

    public Person(int age, boolean sex, String firstName, String lastName, Address address, Pet[] pets) {
        this.age = age;
        this.sex = sex;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.pets = pets;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                sex == person.sex &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(address, person.address) &&
                Arrays.equals(pets, person.pets);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(age, sex, firstName, lastName, address);
        result = 31 * result + Arrays.hashCode(pets);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", sex=" + sex +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", pets=" + Arrays.toString(pets) +
                '}';
    }
}
