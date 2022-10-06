package org.example;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PhoneDirectoryTest {

    private static long suiteStartTime;
    private long testStartTime;

    @BeforeAll
    public static void initSuite() { System.out.println("Running StringTest"); suiteStartTime = System.nanoTime();
    }
    @AfterAll
    public static void completeSuite() {
        System.out.println("StringTest complete: " + (System.nanoTime() - suiteStartTime));
    }

    @BeforeEach
    void setUp() {
        System.out.println("Starting new nest"); testStartTime = System.nanoTime();
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test complete:" + (System.nanoTime() - testStartTime));
    }

    @Test
    void addGroup() {
        //arrange
        Map<String, List<Contact>> expected = new HashMap<>();
        expected.put("Дом", new ArrayList<>());

        //act
        PhoneDirectory phoneDirectory = new PhoneDirectory();
        phoneDirectory.addGroup("Дом");
        Map<String, List<Contact>> actual = phoneDirectory.contactsList;

        //assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addContactToGroup() {
        //arrange
        Contact contact1 = new Contact("Денис", "Быков", "+79896290043", 34, "M");
        Contact contact2 = new Contact("Мария", "Маркова", "+79515307296", 26, "W");
        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact1);
        contacts.add(contact2);
        Map<String, List<Contact>> expected = new HashMap<>();
        expected.put("Друзья", contacts);
        expected.put("Работа", contacts);

        //act
        PhoneDirectory phoneDirectory = new PhoneDirectory();
        String[] groups = new String[]{"Работа", "Друзья"};
        phoneDirectory.addContactToGroup(contact1, groups);
        phoneDirectory.addContactToGroup(contact2, groups);
        Map<String, List<Contact>> actual = phoneDirectory.contactsList;

        //assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getCountWomanAdultsTheGroup() {
        //arrange
        Contact contact = new Contact("Денис", "Быков", "+79896290043", 34, "M");
        Contact contact1 = new Contact("Максим", "Никитин", "+79996290805", 20, "M");
        Contact contact2 = new Contact("Мария", "Маркова", "+79515307296", 26, "W");
        PhoneDirectory phoneDirectory = new PhoneDirectory();
        String[] groups = new String[]{"Работа", "Друзья"};
        phoneDirectory.addContactToGroup(contact, groups);
        phoneDirectory.addContactToGroup(contact1, groups);
        phoneDirectory.addContactToGroup(contact2, groups);
        String groupName = "Работа";
        long expected = 1;

        //act
        long actual = phoneDirectory.getCountWomanAdultsTheGroup(groupName);

        //assert
        Assertions.assertEquals(expected, actual);
    }
}