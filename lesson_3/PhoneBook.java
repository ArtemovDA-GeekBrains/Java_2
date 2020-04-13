package ru.geekbrains.java_2.hw_3;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBook {
    private HashMap<String, ArrayList<String>> phoneBook = new HashMap<>();

    public void add(String name, String phone, String email) {
        ArrayList<String> telList = phoneBook.get(name);
        if (telList == null) telList = new ArrayList<>();
        telList.add(phone);
        telList.add(email);
        phoneBook.put(name, telList);
    }

    public ArrayList<String> findPhone(String name) {
        return phoneBook.get(name);
    }

    public ArrayList<String> findEmail(String name) {
        return phoneBook.get(name);
    }
}
