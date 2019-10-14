package centralScotlandCollegeNamespace;

import javafx.application.Application;
import javafx.stage.Stage;

//What this class does is that it allows the Java application GUI to run
public class Start extends Application {

    @Override
    public void start(Stage primaryStage) {
        Controller controller = new Controller();
        controller.checkDetails();
    }
}
