package ru.geekbrains.java_2.hw_3;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        //  1. Создать массив с набором слов (20-30 слов, должны встречаться повторяющиеся):
        //  - Найти список слов, из которых состоит текст (дубликаты не считать);
        //  - Посчитать сколько раз встречается каждое слово (использовать HashMap);

        String[] words = {
                "Java",
                "Int",
                "Float",
                "Double",
                "Boolean",
                "String",
                "Array",
                "Java",
                "Class",
                "Abstract",
                "Public",
                "Private",
                "Default",
                "Static",
                "Protected",
                "Java",
                "Object",
                "Package",
                "System",
                "Java",
                "Catch",
                "Throw",
                "This",
                "Instanceof",
                "Implements",
                "Windows"
        };

        HashMap<String, Integer> hm = new HashMap<>();
        for(String word: words) {
            Integer res = hm.get(word);
            hm.put(word, res == null ? 1 : res + 1);
        }
        System.out.println(hm);

        //  2. Написать простой класс PhoneBook(внутри использовать HashMap):
        //  - В качестве ключа использовать фамилию
        //  - В каждой записи всего два поля: phone, e-mail
        //  - Отдельный метод для поиска номера телефона по фамилии
        //    (ввели фамилию, получили ArrayList телефонов), и отдельный метод для поиска e-mail по фамилии. Следует учесть, что под одной фамилией может быть несколько записей.
        //  Итого должно получиться 3 класса Main, PhoneBook, Person.

        PhoneBook pb = new PhoneBook();
        pb.add("Пушкин", "123456","pushkin@mail.com");
        pb.add("Лермонтов", "234567", "lermontov@mail.com");
        pb.add("Есенин", "345678","esenin@mail.com");
        pb.add("Чехов", "456789", "chehov@mail.com");

        pb.add("Пушкин", "654321","pushkin_2@mail.com");

        //Не разобрался как сделать отдельные методы для телефона и e-mail.

        System.out.println(" У Пушкина номера: " + pb.findPhone("Пушкин"));
        System.out.println(" У Пушкина e-mail: " + pb.findEmail("Пушкин"));
    }
}
