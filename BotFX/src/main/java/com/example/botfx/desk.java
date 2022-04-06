package com.example.botfx;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class desk {

    void open_exe(String name) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        if (name == "калькулятор")
        {
            desktop.open(new File("\\Windows\\System32\\calc.exe"));
        }
        if (name == "paint" || name == "паинт")
        {
            desktop.open(new File("\\Windows\\System32\\mspaint.exe"));
        }



    }
}
