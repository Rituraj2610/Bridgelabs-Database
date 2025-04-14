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

    public boolean addPerson(Person p) {
        // UC6: Check for duplicate person in AddressBook
        if (contacts.contains(p)) {
            return false;
        }
        contacts.add(p);
        return true;
    }

    public boolean deletePerson(String firstName) {
        // UC3: Delete person by name
        return contacts.removeIf(p -> p.getFirstName().equalsIgnoreCase(firstName));
    }

    public void editPerson(String firstName, Scanner sc) {
        // UC2: Edit person by name
        for (Person p : contacts) {
            if (p.getFirstName().equalsIgnoreCase(firstName)) {
                System.out.print("New last name: ");
                p.setLastName(sc.nextLine());
                System.out.print("New phone: ");
                p.setPhone(sc.nextLine());
                System.out.print("New email: ");
                p.setEmail(sc.nextLine());
                return;
            }
        }
        System.out.println("Person not found.");
    }



}

