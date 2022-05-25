package com.example.databasetest;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.StringTokenizer;

public class FileOperations {

    private  FileChooser fileChooser = new FileChooser();

    FileOperations()
    {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Database", "*.db"));
    }


    public  Connection createFile(Connection connection, StringProperty name_of_file, ObservableList<Enterpise> list)
    {
        try
        {
            //закрываем предыдущее соединение
            connection.close();

            //Просто очищаем лист
            list.clear();

            //Очищаем имя открытого файла
            name_of_file.setValue("");

            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  Connection openFile(Connection connection, StringProperty name_of_file, ObservableList<Enterpise> list_Enterprise)
    {
        //Заголовок проводника
        fileChooser.setTitle("Выберите файл, который хотите открыть");

        //Инициализация начальной дирректории
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));

        //Доступные разрешения файлов для выбора
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Database", "*.db"));

        //Показываем диалоговое окно, и запоминаем путь
        String fileName = String.valueOf(fileChooser.showOpenDialog(null));

        //Проверяем выбран ли файл
        if (fileName != "null")
        {
            System.out.println("файл выбран:" + fileName);
            try
            {
                name_of_file.setValue(getFileName(fileName));
                //Закрываем старое соединение
                connection.close();

                //И делаем новое, к выбранной базе данных
                connection = DB_Connect.connect(fileName);

                //Заполняем лист
                MyList.fillObservableList(list_Enterprise, connection);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;

    }

    public  Connection saveFile(Connection connection, StringProperty name_of_file, ObservableList<Enterpise> list)
    {
        try
        {
            //Если коннекта нет, то вызываем диалоговое окно для сохранения
            if (connection.isClosed())
            {
                connection = saveFile_As(connection, name_of_file, list);
            }
            else
            {
                SqlQuery query = new SqlQuery();
                query.send(connection, list);
            }

            return connection;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public  Connection saveFile_As(Connection connection, StringProperty name_of_file, ObservableList<Enterpise> list)
    {
        //Заголовок диалогового окна
        fileChooser.setTitle("Сохранить как");

        //Инициализация начальной дирректории
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));

        //Сохраняем путь и имя файла
        String fileName = String.valueOf(fileChooser.showSaveDialog(null));

        SqlQuery query = new SqlQuery();

        if (fileName != "null")
        {
            try {
                name_of_file.setValue(getFileName(fileName));

                //Создаем новый коннект
                connection = DB_Connect.newConnect(fileName);

                query.CreateTable(connection);
                query.send(connection, list);

            } catch (SQLException e) {
                System.out.println(e);;
            }

            query.send(connection, list);
        }
        return connection;
    }
    private  String getFileName(String path_to_file)
    {
        StringTokenizer str = new StringTokenizer(path_to_file, "\\");

        for (int i = 0, count = str.countTokens(); i < count - 1; i++)
        {
            str.nextToken();
        }
        return  str.nextToken();
    }

}
