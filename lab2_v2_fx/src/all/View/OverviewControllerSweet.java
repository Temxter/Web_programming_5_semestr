package all.View;

import all.Sweet.SweetFX;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import all.MainApp;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OverviewControllerSweet {
    @FXML
    private TableView<SweetFX> sweetTable;
    @FXML
    private TableColumn<SweetFX, String> firstNameColumn;
    @FXML
    private TableColumn<SweetFX, String> lastNameColumn;

    @FXML
    private Label nameLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label weightLabel;
    @FXML
    private Label additionalLabel;
    @FXML
    private Label consistLabel;
    @FXML
    private Label totalWeightLabel;

    // Ссылка на главное приложение.
    private MainApp mainApp;

    /**
     * Конструктор.
     * Конструктор вызывается раньше метода initialize().
     */
    public OverviewControllerSweet() {
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        // Инициализация таблицы адресатов с двумя столбцами.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());

        // Очистка дополнительной информации об адресате.
        showSweetDetails(null);

        // Слушаем изменения выбора, и при изменении отображаем
        // дополнительную информацию об адресате.
        sweetTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSweetDetails(newValue));
    }

    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Добавление в таблицу данных из наблюдаемого списка
        sweetTable.setItems(mainApp.getPresentData());
        reflashWeight();
    }

    /**
     * Заполняет все текстовые поля, отображая подробности об адресате.
     * Если указанный адресат = null, то все текстовые поля очищаются.
     *
     * @param sweet — адресат типа SweetFX или null
     */
    private void showSweetDetails(SweetFX sweet) {
        if (sweet != null) {
            // Заполняем метки информацией из объекта person.
            nameLabel.setText(sweet.getName());
            typeLabel.setText(sweet.getType());
            weightLabel.setText(String.valueOf(sweet.getWeight()));
            additionalLabel.setText(sweet.getAdditional());
            consistLabel.setText(sweet.getConsist());
        } else {
            // Если sweet = null, то убираем весь текст.
            nameLabel.setText("");
            typeLabel.setText("");
            weightLabel.setText("");
            additionalLabel.setText("");
            consistLabel.setText("");
        }
    }

    /**
     * Вызывается, когда пользователь кликает по кнопке удаления.
     */
    @FXML
    private void handleDeleteSweet() {
        int selectedIndex = sweetTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            sweetTable.getItems().remove(selectedIndex);
            reflashWeight();
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    /**
     * Вызывается, когда пользователь кликает по кнопке New...
     * Открывает диалоговое окно с дополнительной информацией нового адресата.
     */
    @FXML
    private void handleNewSweet() {
        SweetFX tempSweet = new SweetFX();
        boolean okClicked = mainApp.showPersonEditDialog(tempSweet);
        if (okClicked) {
            mainApp.getPresentData().add(tempSweet);
            reflashWeight();
        }
    }

    /**
     * Вызывается, когда пользователь кликает по кнопка Edit...
     * Открывает диалоговое окно для изменения выбранного адресата.
     */
    @FXML
    private void handleEditPerson() {
        SweetFX selectedSweet = sweetTable.getSelectionModel().getSelectedItem();
        if (selectedSweet != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedSweet);
            if (okClicked) {
                showSweetDetails(selectedSweet);
                reflashWeight();
            }

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    void reflashWeight(){
        ObservableList<SweetFX> presentData = mainApp.getPresentData();
        double totalWeight = 0.0;
        for (SweetFX sweet : presentData){
            totalWeight += sweet.getWeight();
        }
        totalWeightLabel.setText("Total weight: " + String.valueOf(totalWeight));
    }
}