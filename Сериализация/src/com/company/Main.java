package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user = new User();

        user.lifeLevel = 55;
        user.sword.damage = 100;
        user.sword.level = 10;

        //Запись в файл
        FileOutputStream filewrite = new FileOutputStream("user");

        ObjectOutputStream object = new ObjectOutputStream(filewrite);
        object.writeObject(user);
        object.close();

        //Чтение из файла
        FileInputStream fileread = new FileInputStream("user");

        ObjectInputStream objectInputStream = new ObjectInputStream(fileread);
        User user1 = (User) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println(user1.lifeLevel);
        System.out.println(user1.sword.level);
        System.out.println(user1.sword.damage);

    }
}
