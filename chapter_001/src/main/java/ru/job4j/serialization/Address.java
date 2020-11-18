package ru.job4j.serialization;

import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable {
    private final String city;
    private final String street;
    private final long houseNumber;
    private final long flatNumber;

    public Address(String city, String street, long houseNumber, long flatNumber) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return houseNumber == address.houseNumber &&
                flatNumber == address.flatNumber &&
                Objects.equals(city, address.city) &&
                Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, houseNumber, flatNumber);
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", flatNumber=" + flatNumber +
                '}';
    }
}
