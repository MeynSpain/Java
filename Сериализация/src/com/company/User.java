package com.company;

import java.io.Serializable;

public class User implements Serializable {
    int lifeLevel;
    Sword sword = new Sword();

    //Поля static и transient не сеарилизуются
    //transient поля после того как мы достанем объект из файла будут иметь значения по умолчанию
}
