package com.example.databasetest;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;


public class Controller {

    private Stage mainStage;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    private MenuItem MenuItem_delete;

    @FXML
    private MenuItem MenuItem_edit;

    @FXML
    private Button button_add;

    @FXML
    private TableColumn<Enterpise, Integer> col_id;

    @FXML
    private TableColumn<Enterpise, String> col_name;

    @FXML
    private TableColumn<Enterpise, String> col_bank_details;

    @FXML
    private TableColumn<Enterpise, String> col_contact_person;

    @FXML
    private ContextMenu contextMenu;

    @FXML
    private TableView<Enterpise> table;

    @FXML
    private TextField textField_bank_details;

    @FXML
    private TextField textField_contact_person;

    @FXML
    private TextField textField_name;

    @FXML
    private TextField textField_search;

    @FXML
    private Text label_fileName;

    @FXML
    private Text labelSave;

    private Parent root;

    private Controller_Edit controller_edit;

    private FXMLLoader fxmlLoader = new FXMLLoader();

    private ObservableList<Enterpise> list_Enterprise = FXCollections.observableArrayList();

    private Stage editStage = null;

    //Коннект с файлом базы данных
    private Connection connection;

    //SQL запросы
    private SqlQuery query = new SqlQuery();

    //Выбор файлов через диалоговые окна
    private FileChooser fileChooser = new FileChooser();

    private StringProperty name_of_file = new SimpleStringProperty("DB_test.db");


    /**
     * Создание новой таблицы, очищается таблица, но потом нужно будет сохранить этот файл
     */
    @FXML
    void createFile()
    {
        try
        {
            //закрываем предыдущее соединение
            connection.close();

            //Просто очищаем лист
            list_Enterprise.clear();

            //Очищаем имя открытого файла
            name_of_file.setValue("");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        /*
        fileChooser.setTitle("Введите название файла и выберите дирректорию, где хотите создать файл");

        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Database", "*.db"));
        String filename = String.valueOf(fileChooser.showSaveDialog(null));

        try {

            //Закрываем старое соединение
            connection.close();

            //Создаем новое
            connection = DB_Connect.newConnect(filename);

            //Создаем таблицу в новой базе
            query.CreateTable(connection);

            //Заполняем лист
            MyList.fillObservableList(list_Enterprise, connection);

        } catch (SQLException e) {
            System.out.println(e);
        }

         */

    }

    /**
     * Вызывается диалоговое окно для открытия файла
     */
    @FXML
    void openFile()
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
            System.out.println("файл выбран");
            try {
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
    }


    /**
     * Сохранение файла
     */
    public void saveFile()
    {
        try
        {
            //Если коннекта нет, то вызываем диалоговое окно для сохранения
            if (connection.isClosed())
            {
                saveFile_As();
            }
            else
            {
                query.send(connection, list_Enterprise);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Сохранить как
     */
    public void saveFile_As()
    {
        //Заголовок диалогового окна
        fileChooser.setTitle("Сохранить как");

        //Инициализация начальной дирректории
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));

        //Сохраняем путь и имя файла
        String fileName = String.valueOf(fileChooser.showSaveDialog(null));

        if (fileName != "null")
        {
            try {
                name_of_file.setValue(getFileName(fileName));

                //Создаем новый коннект
                connection = DB_Connect.newConnect(fileName);

                query.CreateTable(connection);
                query.send(connection, list_Enterprise);

            } catch (SQLException e) {
                System.out.println(e);;
            }

            query.send(connection, list_Enterprise);
        }
    }


    /**
     * Инициализация формы(конструктор формы)
     */
    @FXML
    void initialize()
    {

        //Делаем начальное значение надписи имени файла
        label_fileName.setText(name_of_file.get());

        //Устанавливаем возможность выбирать больше одной записи
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        table.getEditingCell();

        try {
            connection = DB_Connect.getConnection();
            System.out.println("Подключился");

            //Заполняем list_Enterprise
            MyList.fillObservableList(list_Enterprise, connection);

        } catch (SQLException e) {
            System.out.println(e);
        }

        //Инициализируем колонки таблицы
        col_id.setCellValueFactory(new PropertyValueFactory<Enterpise, Integer>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<Enterpise, String>("name"));
        col_bank_details.setCellValueFactory(new PropertyValueFactory<Enterpise, String>("banking_details"));
        col_contact_person.setCellValueFactory(new PropertyValueFactory<Enterpise, String>("contact_person"));

        //Инициализируем форму редактирования
        initLoader();

        //Инициализируем двойной клик
        init_double_click();

        //Связываем лист с таблицей
        //Вот эта строка уже не нужна, потому что, в инициализации поиска
        //list_Enterprise обернут уже в сортированный лист
//        table.setItems(list_Enterprise);

        //Инициализируем поиск
        init_search();

        //Доступные разрешения файлов для выбора
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Database", "*.db"));

        //Инициализируем слушатель для отслеживания имени открытого файла
        initFileNameListener();
    }

    /**
     * Инициализирует слушатель для отслеживания имени открытого файла
     */
    private void initFileNameListener() {

        name_of_file.addListener(new ChangeListener<String>() {
           @Override
           public void changed(ObservableValue<? extends String> observableValue, String s, String t1)
           {
               //обновляем надпись с именем файла
               update_fileName();
           }
        });
    }

    /**
     * Обновление надписи в имени файла
     */
    void update_fileName()
    {
        label_fileName.setText(name_of_file.get());
    }

    /**
     * Включение возможности поиска в таблице
     */
    private void init_search()
    {
        //Создаем Фильтр Лист, в который мы потом обернем observablelist
        FilteredList<Enterpise> filteredList = new FilteredList<>(list_Enterprise, b->true);

        //Добавляем слушатель на поле ввода поиска
        textField_search.textProperty().addListener((observable, oldValue, newValue) ->
        {
            //Добавляем предикат(условие, при котором слушатель будет работать
            filteredList.setPredicate(enterpise ->
            {
                if (newValue == null || newValue.isEmpty())
                {
                    return true;
                }

                //берем значение из поле поиска переводим в нижний регистр
                //и убираем все лишние пробелы
                String filter = newValue.toLowerCase().trim().replaceAll("\\s+", " ");

                //Проверяем совпадения банковских реквизитов
                //id, Контактного лица, названия компании
                if(enterpise.getBanking_details().toLowerCase().indexOf(filter) != -1) {
                    return true;
                }
                else if (enterpise.getId().toString().indexOf(filter) != -1)
                {
                    return true;
                }
                else if (enterpise.getContact_person().toLowerCase().indexOf(filter) != -1)
                {
                    return true;
                }
                else if (enterpise.getName().toLowerCase().indexOf(filter) != -1)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            });
        });

        //Теперь делаем сортированный лист, и оборачиваем в нег фильтр лист, в который уже обернули observable
        SortedList<Enterpise> sortedList = new SortedList<>(filteredList);

        //Вот хз что делает(что то с таблицей)
        sortedList.comparatorProperty().bind(table.comparatorProperty());

        //связываем таблицу с сортированным листом
        table.setItems(sortedList);
    }

    /**
     * Инициализация двойного клика по таблице.
     * Если сделать двойной клик, то вызывается окно редактирования записи
     */
    private void init_double_click()
    {
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                //Считаем клики(можно и больше, чем 2)
                if (mouseEvent.getClickCount() == 2)
                {
                    MenuItem_edit_click();
                }
            }
        });
    }

    /**
     * Добавление записи в базу
     * @param actionEvent
     */
    public void button_add_click(ActionEvent actionEvent)
    {
        //Получаем данные и убираем лишние пробелы
        String name = textField_name.getText().trim().replaceAll("\\s+", " ");
        String bank = textField_bank_details.getText().trim().replaceAll("\\s+", " ");
        String contact = textField_contact_person.getText().trim().replaceAll("\\s+", " ");

        //Если строки не пустые, то добавляем
        if (!name.isEmpty() && !bank.isEmpty() && !contact.isEmpty())
        {
            System.out.println("не пустой");

            //считаем id
            int id;

            //Если записей нет, то ставим id = 0
            if (list_Enterprise.isEmpty())
            {
                id = 0;
            }
            else
            {
                //Если уже есть записи,
                //Считываем id предыдущей записи
                id = list_Enterprise.get(list_Enterprise.size() - 1).getId();
            }

            //Добавляем запись в лист
            list_Enterprise.add(new Enterpise(id + 1, name, bank, contact));

            //Добавляем их
//            try {
//                //Добавление в базу
//                connection.createStatement().executeUpdate(query.getInsert(name, bank, contact));
//
//                ResultSet rs = connection.createStatement().executeQuery("select * from Enterprise order by id desc limit 1");
//
//                rs.next();
//                //Добавляю запись в observabellist
//                list_Enterprise.add(new Enterpise
//                        (
//                                rs.getInt("id"),
//                                rs.getString("name"),
//                                rs.getString("banking_details"),
//                                rs.getString("contact_person")
//                        ));
//
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
        }


    }

    /**
     * Удаление записи
     * @param actionEvent
     */
    public void MenuItem_delete_click(ActionEvent actionEvent) {

        //Получаем лист записей, которые нужно удалить
        ObservableList<Enterpise> array = table.getSelectionModel().getSelectedItems();
        //Удаляем записи из базы
//        query.DELETE(connection, array);
        //Удаляем из листа(из таблицы удалятся сами, изза того что она отображает содердимое листа)
        list_Enterprise.removeAll(array);
    }

    /**
     * Редактирование записи
     * @param
     */
    public void MenuItem_edit_click()
    {
        //получаем выбранную запись
        Enterpise enterpise = table.getSelectionModel().getSelectedItem();

        //Передаем ее в форму редактирования
        controller_edit.setEnterpise(enterpise);
        showEdidStage();

        //Добавляем уже измененную запись в лист
        list_Enterprise.set(table.getSelectionModel().getSelectedIndex(), enterpise);

        //Редактирование записи в базе
//        query.UPDATE(connection, enterpise);

    }

    /**
     * Инициализируем контроллер окна редактирования
     */
   private void initLoader()
   {
       try
       {
            //Подгружаем файл окна редактирования, и передаем его контроллер сюда
            fxmlLoader.setLocation(getClass().getResource("DB_Edit_Interface.fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle("Bundles.Locale", new Locale("ru")));
            fxmlLoader.load();
            root = fxmlLoader.getRoot();
            controller_edit = fxmlLoader.getController();

       }
       catch (IOException e)
       {
           System.out.println(e);
       }
   }


    /**
     * Показывает окно редактирования
     */
    private void showEdidStage()
    {
        //Если окно еще не создавали, то создаем
        if (editStage == null)
        {
            editStage = new Stage();
            editStage.setTitle(fxmlLoader.getResources().getString("key.Edit"));
            editStage.setScene(new Scene(root));
            editStage.initModality(Modality.WINDOW_MODAL);
            editStage.initOwner(mainStage);
            editStage.getIcons().add(new Image("file:src\\main\\resources\\icons\\mainIcon.png"));
        }
        editStage.showAndWait();
    }




    public void closeProgram(ActionEvent actionEvent) {
        System.out.println("exit");
    }

    /**
     * Возвращает имя файла
     * @param path_to_file путь к файлу
     * @return
     */
    private String getFileName(String path_to_file)
    {
        StringTokenizer str = new StringTokenizer(path_to_file, "\\");

        for (int i = 0, count = str.countTokens(); i < count - 1; i++)
        {
            str.nextToken();
        }
      return  str.nextToken();
    }
}