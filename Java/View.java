package centralScotlandCollegeNamespace;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.plugin2.message.Message;

import javax.swing.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public class View {

    public void displayMessageBox(String message)
    {
//        JOptionPane.showMessageDialog(null, message);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Info!");
        alert.setHeaderText("Read Information Below!");
        alert.setContentText(message);
        alert.setResizable(true);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }

    public String displayInputBox(String message)
    {
        //return JOptionPane.showInputDialog(message);
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Enter Details!");
        dialog.setHeaderText("Enter What Is Asked Below!");
        dialog.setContentText(message);
        dialog.setResizable(true);
        dialog.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    public String displayChoiceBox(String message, String choice1, String choice2)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Select A Option!");
        alert.setHeaderText(message);
        alert.setResizable(true);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        ButtonType firstChoice = new ButtonType(choice1);
        ButtonType secondChoice = new ButtonType(choice2);
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(firstChoice,secondChoice, cancel);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == firstChoice){
            return firstChoice.getText();
        } else if (result.get() == secondChoice){
            return secondChoice.getText();
        } else {
            return null;
        }
    }

    public String displayChoiceBox(String message, String choice1, String choice2, String choice3){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Select A Option!");
        alert.setHeaderText(message);
        alert.setResizable(true);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        ButtonType firstChoice = new ButtonType(choice1);
        ButtonType secondChoice = new ButtonType(choice2);
        ButtonType thirdChoice = new ButtonType(choice3);
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(firstChoice,secondChoice,thirdChoice ,cancel);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == firstChoice){
            return firstChoice.getText();
        } else if (result.get() == secondChoice){
            return secondChoice.getText();
        } else if (result.get() == thirdChoice){
            return thirdChoice.getText();
        } else {
            return null;
        }

    }
}
