package all.View;

import all.Sweet.SweetFX;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Окно для изменения информации об адресате.
 */
public class SweetEditDialogController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField weightField;
    @FXML
    private TextField additionalField;
    @FXML
    private TextField consistField;

    private Stage dialogStage;
    private SweetFX sweet;
    private boolean okClicked = false;

    /**
     * Инициализирует класс-контроллер. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Устанавливает сцену для этого окна.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Задаёт адресата, информацию о котором будем менять.
     *
     * @param sweet
     */
    public void setPerson(SweetFX sweet) {
        this.sweet = sweet;
        nameField.setText(sweet.getName());
        typeField.setText(sweet.getType());
        weightField.setText(String.valueOf(sweet.getWeight()));
        additionalField.setText(sweet.getAdditional());
        consistField.setText(sweet.getConsist());
    }

    /**
     * Returns true, если пользователь кликнул OK, в другом случае false.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке OK.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            sweet.setName(nameField.getText());
            sweet.setAdditional(additionalField.getText());
            sweet.setConsist(consistField.getText());
            sweet.setType(typeField.getText());
            sweet.setWeight(Double.parseDouble(weightField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке Cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Проверяет пользовательский ввод в текстовых полях.
     *
     * @return true, если пользовательский ввод корректен
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }
        if (typeField.getText() == null || typeField.getText().length() == 0) {
            errorMessage += "No valid type!\n";
        }
        if (consistField.getText() == null || consistField.getText().length() == 0) {
            errorMessage += "No valid consist!\n";
        }

        if (weightField.getText() == null || weightField.getText().length() == 0) {
            errorMessage += "No valid weight!\n";
        } else {
            // пытаемся преобразовать почтовый код в int.
            try {
                Double.parseDouble(weightField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n";
            }
        }

        if (additionalField.getText() == null || additionalField.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}