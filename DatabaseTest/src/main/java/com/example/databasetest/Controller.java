package com.example.databasetest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;


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

    private Parent root;

    private Controller_Edit controller_edit;

    private FXMLLoader fxmlLoader = new FXMLLoader();

    private ObservableList<Enterpise> list_Enterprise = FXCollections.observableArrayList();

    private Stage editStage = null;



    Connection connection;
    SqlQuery query = new SqlQuery();

    FileChooser fileChooser = new FileChooser();

    /**
     * Вызывается диалоговое окно для открытия файла
     */
    @FXML
    void openFile()
    {
        //Заголовок проводника
        fileChooser.setTitle("Выберите файл, который хотите открыть");


        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));

        //Доступные разрешения файлов для выбора
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Database", "*.db"));

        String fileName = String.valueOf(fileChooser.showOpenDialog(null));

        try {
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

    /**
     * Вызывается диалоговое окно для создания файла
     */
    @FXML
    void createFile()
    {
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

    }

    @FXML
    void initialize()
    {


        //Устанавливаем возможность выбирать больше одной записи
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        table.getEditingCell();
        try {
            connection = DB_Connect.getConnection();
            System.out.println("Подключился");

            ResultSet rs = connection.createStatement().executeQuery(query.getSelect());

            //Пока что то есть, то считываем
            int i =0;
            while (rs.next())
            {
                list_Enterprise.add(new Enterpise
                        (
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("banking_details"),
                        rs.getString("contact_person")
                        ));

                System.out.println(list_Enterprise.get(i).id+ "||" + list_Enterprise.get(i).name+ "||" + list_Enterprise.get(i).banking_details+ "||" + list_Enterprise.get(i).contact_person);
                i++;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        col_id.setCellValueFactory(new PropertyValueFactory<Enterpise, Integer>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<Enterpise, String>("name"));
        col_bank_details.setCellValueFactory(new PropertyValueFactory<Enterpise, String>("banking_details"));
        col_contact_person.setCellValueFactory(new PropertyValueFactory<Enterpise, String>("contact_person"));

        initLoader();
        init_double_click();



        table.setItems(list_Enterprise);
        init_search();

    }

    /**
     * Включение возможности поиска в таблице
     */
    private void init_search()
    {
        FilteredList<Enterpise> filteredList = new FilteredList<>(list_Enterprise, b->true);

        textField_search.textProperty().addListener((observable, oldValue, newValue) ->
        {
            filteredList.setPredicate(enterpise ->
            {
                if (newValue == null || newValue.isEmpty())
                {
                    return true;
                }

                String filter = newValue.toLowerCase().replaceAll("\\s+", " ");

                if(enterpise.getBanking_details().toLowerCase().indexOf(filter) != -1)
                {
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

        SortedList<Enterpise> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedList);
    }

    private void init_double_click()
    {
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2)
                {
                    System.out.println("Двойной клик");
                    MenuItem_edit_click();
                }
            }
        });
    }

    /**
     * Добавление записи в базу
     * @param actionEvent
     */
    public void button_add_click(ActionEvent actionEvent) {
        //Получаем данные и убираем лишние пробелы
        String name = textField_name.getText().trim().replaceAll("\\s+", " ");
        String bank = textField_bank_details.getText().trim().replaceAll("\\s+", " ");
        String contact = textField_contact_person.getText().trim().replaceAll("\\s+", " ");

        //Если строки не пустые, то добавляем
        if (!name.isEmpty() && !bank.isEmpty() && !contact.isEmpty())
        {
            System.out.println("не пустой");


            //Добавляем их
            try {
                //Добавление в базу
                connection.createStatement().executeUpdate(query.getInsert(name, bank, contact));

                ResultSet rs = connection.createStatement().executeQuery("select * from Enterprise order by id desc limit 1");

                rs.next();
                //Добавляю запись в observabellist
                list_Enterprise.add(new Enterpise
                        (
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("banking_details"),
                                rs.getString("contact_person")
                        ));

            } catch (SQLException e) {
                System.out.println(e);
            }
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
        query.DELETE(connection, array);
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

        query.UPDATE(connection, enterpise);

    }

    /**
     * Инициализируем контроллер окна редактирования
     */
   private void initLoader()
   {
       try
       {

            fxmlLoader.setLocation(getClass().getResource("DB_Edit_Interface.fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle("Bundles.Locale", new Locale("en")));
            fxmlLoader.load();
            root = fxmlLoader.getRoot();
            controller_edit = fxmlLoader.getController();
       }
       catch (IOException e)
       {
           System.out.println(e);
       }
   }


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
        }
        editStage.showAndWait();
    }
}