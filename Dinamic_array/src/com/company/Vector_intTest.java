package com.company;

import static org.junit.jupiter.api.Assertions.*;

class Vector_intTest {

    @org.junit.jupiter.api.Test
    void push_back() {
        Vector_int arr = new Vector_int(1);
        arr.push_back(101);
        assertEquals(101, arr.last());
    }

    @org.junit.jupiter.api.Test
    void set() {
        Vector_int arr = new Vector_int(10);
        arr.set(4,101);
        assertEquals(101, arr.get(4));
    }

    @org.junit.jupiter.api.Test
    void resize() {
        Vector_int arr = new Vector_int(1);
        arr.resize(10);
        assertEquals(10, arr.get_size());
    }

    @org.junit.jupiter.api.Test
    void copy() {
        Vector_int arr = new Vector_int(1);
        Vector_int arr2 = new Vector_int(10, 101);
        arr.copy(arr2);
        assertEquals(10, arr.get_size());
        for (int i = 0; i < arr.get_size(); i++)
        {
            assertEquals(101, arr.get(i));
        }
    }

    @org.junit.jupiter.api.Test
    void insert() {
        Vector_int arr = new Vector_int(10);
        arr.insert(2,101);
        assertEquals(101, arr.get(2));
        assertEquals(11, arr.get_size());
    }

    @org.junit.jupiter.api.Test
    void swap() {
        Vector_int arr = new Vector_int(10);
        arr.set(0,101);
        arr.swap(0,7);
        assertEquals(101, arr.get(7));
        assertEquals(0, arr.get(0));
    }

    @org.junit.jupiter.api.Test
    void get() {
        Vector_int arr = new Vector_int(10);
        arr.set(4, 23);
        assertEquals(23, arr.get(4));
    }

    @org.junit.jupiter.api.Test
    void get_size() {
        Vector_int arr = new Vector_int(10);
        assertEquals(10, arr.get_size());
    }

    @org.junit.jupiter.api.Test
    void pop_back() {
        Vector_int arr = new Vector_int(10);
        arr.pop_back();
        assertEquals(9, arr.get_size());
    }

    @org.junit.jupiter.api.Test
    void erase() {
        Vector_int arr = new Vector_int(10);
        arr.erase(2,4);
        assertEquals(6, arr.get_size());
    }

    @org.junit.jupiter.api.Test
    void clear() {
        Vector_int arr = new Vector_int(10);
        arr.clear();
        assertEquals(0, arr.get_size());
    }

    @org.junit.jupiter.api.Test
    void last() {
        Vector_int arr = new Vector_int(10);
        arr.push_back(101);
        assertEquals(101, arr.last());
    }
}