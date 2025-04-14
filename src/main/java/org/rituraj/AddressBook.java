package org.rituraj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressBook {
    private String name;
    private List<Person> contacts = new ArrayList<>();

    public AddressBook(String name) {
        this.name = name;
        this.contacts = new ArrayList<>();
    }



}

