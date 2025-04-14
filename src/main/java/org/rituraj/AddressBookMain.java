package org.rituraj;

import java.sql.Connection;
import java.util.*;
import java.util.Scanner;

public class AddressBookMain {

    private Map<String, AddressBook> bookMap;
    private Scanner sc;
    private CRUD crud;

    public AddressBookMain(Connection con) {
        this.bookMap = new HashMap<>();
        this.sc = new Scanner(System.in);
        this.crud = new CRUD(con);


        AddressBook dbBook = new AddressBook("Default");
        List<Person> dbPeople = crud.loadAllContacts();
        for (Person p : dbPeople) dbBook.addPerson(p);
        bookMap.put("Default", dbBook);
    }

    public void run() {
        while (true) {
            System.out.println("1. Add AddressBook\n" +
                    "                2. Add Contact\n" +
                    "                3. Edit Contact\n" +
                    "                4. Delete Contact\n" +
                    "                5. View Contacts\n" +
                    "                6. Search by City\n" +
                    "                7. Search by State\n" +
                    "                8. Count by City/State\n" +
                    "                9. Sort by Name\n" +
                    "                10. Sort by City/State/Zip\n" +
                    "                0. Exit");

            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> addAddressBook();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void addAddressBook() {
        System.out.print("Enter AddressBook name: ");
        String name = sc.nextLine();
        bookMap.putIfAbsent(name, new AddressBook(name));
        System.out.println("Created.");
    }

}
