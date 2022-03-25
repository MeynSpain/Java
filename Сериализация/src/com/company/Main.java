package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        List<User> users = new ArrayList<>();

        User user = new User();
        user.lifeLevel = 55;
        user.sword.damage = 100;
        user.sword.level = 10;

        User user1 = new User();
        user1.lifeLevel = 30;
        user1.sword.damage = 45;
        user1.sword.level = 2;

        User user2 = new User();
        user2.lifeLevel = 50;
        user2.sword.damage = 50;
        user2.sword.level = 3;

        users.add(user);
        users.add(user1);
        users.add(user2);
    //Запихиваем лист внутрь файла
        FileOutputStream fos = new FileOutputStream("users.data");
        ObjectOutputStream write = new ObjectOutputStream(fos);
        write.writeObject(users);
        write.close();
        //Вытаскиваем из файла лист
        FileInputStream ios = new FileInputStream("users.data");
        ObjectInputStream read = new ObjectInputStream(ios);
        List<User> p = (List<User>) read.readObject();
        read.close();
        System.out.println("\nlist:");
        for (User gamer: p)
        {
            System.out.println(gamer.lifeLevel);
            System.out.println(gamer.sword.level);
            System.out.println(gamer.sword.damage);
        }
        System.out.println();

        //Запись в файл
        FileOutputStream filewrite = new FileOutputStream("user", true);

        ObjectOutputStream object = new ObjectOutputStream(filewrite);
        object.writeObject(user);
        object.close();

        //Чтение из файла
        FileInputStream fileread = new FileInputStream("user");

        ObjectInputStream objectInputStream = new ObjectInputStream(fileread);
        User user4 = (User) objectInputStream.readObject();


        objectInputStream.close();
        System.out.println(user4.lifeLevel);
        System.out.println(user4.sword.level);
        System.out.println(user4.sword.damage);

    }
}
