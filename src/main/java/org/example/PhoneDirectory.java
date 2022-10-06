package org.example;

import java.util.*;

public class PhoneDirectory {
    public Map<String, List<Contact>> contactsList = new HashMap<>();

    //Создание группы контактов
    public void addGroup(String groupName) {
        if (contactsList.containsKey(groupName)) {
            System.out.println("Группа с таким названием уже существует");
        }
        contactsList.put(groupName, new ArrayList<>());
    }

    public void addContactToGroup(Contact contact, String[] groupNames) {
        for (String groupName : groupNames) {
            if (!contactsList.containsKey(groupName)) {
                addGroup(groupName);
            }
            List<Contact> currentList = contactsList.get(groupName);
            currentList.add(contact);
            contactsList.put(groupName, currentList);
        }
    }

    public void printInfo() {
        if (contactsList.isEmpty()) {
            System.out.println("Список пуст!");
        } else {
            System.out.println("Список групп и контактов: ");
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, List<Contact>> element : contactsList.entrySet()) {
                sb.append("\n");
                sb.append(element.getKey());
                sb.append("\n");
                for (Contact contactPrint : element.getValue()) {
                    sb.append("\t");
                    sb.append(contactPrint);
                    sb.append("\n");
                }
            }
            System.out.println(sb);
        }
    }

    public long getCountWomanAdultsTheGroup(String groupName) {
        List<Contact> list = contactsList.get(groupName);
        return list.stream()
                .filter(p -> p.getAge() >= 18)
                .filter(p -> p.getSex().equals("W"))
                .count();
    }

    public long getCountManAdultsTheGroup(String groupName) {
        List<Contact> list = contactsList.get(groupName);
        return list.stream()
                .filter(p -> p.getAge() >= 18)
                .filter(p -> p.getSex().equals("M"))
                .count();
    }
}
