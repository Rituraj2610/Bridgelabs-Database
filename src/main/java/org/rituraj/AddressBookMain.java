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
                case 2 -> addContact();
                case 3 -> editContact();
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

    private void addContact() {
        System.out.print("Enter AddressBook name: ");
        String name = sc.nextLine();
        AddressBook book = bookMap.get(name);
        if (book == null) {
            System.out.println("AddressBook not found.");
            return;
        }

        System.out.print("First name: ");
        String fname = sc.nextLine();
        System.out.print("Last name: ");
        String lname = sc.nextLine();
        System.out.print("Street: ");
        String street = sc.nextLine();
        System.out.print("City: ");
        String city = sc.nextLine();
        System.out.print("State: ");
        String state = sc.nextLine();
        System.out.print("Zip: ");
        String zip = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Type (family/friends): ");
        String type = sc.nextLine();

        Address addr = new Address(street, city, state, zip);
        Person p = new Person(fname, lname, addr, email, phone, type);

        if (book.addPerson(p)) {
            System.out.println("Contact added.");
        } else {
            System.out.println("Duplicate contact!");
        }
    }

    private void editContact() {
        System.out.print("Enter AddressBook name: ");
        String name = sc.nextLine();
        AddressBook book = bookMap.get(name);
        if (book != null) {
            System.out.print("Enter first name of person to edit: ");
            book.editPerson(sc.nextLine(), sc);
        }
    }



}
