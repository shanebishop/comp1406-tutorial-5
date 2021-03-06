import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DVDCollectionApp1 extends Application {
    public void start(Stage primaryStage) {
        Pane aPane = new Pane();

        // Create the labels
        Label titleLabel = new Label("Title");
        Label yearLabel  = new Label("Year");
        Label lenLabel   = new Label("Length");
        titleLabel.relocate(10,10);
        yearLabel.relocate(220,10);
        lenLabel.relocate(290,10);

        // Create the lists
        String[]    titles = {"Star Wars", "Java is cool", "Mary Poppins", "The Green Mile"};
        String[]    years = {"1978", "2002", "1968", "1999"};
        String[]    lengths = {"124", "93", "126", "148"};

        ListView<String> titlesList = new ListView<String>();
        titlesList.setItems(FXCollections.observableArrayList(titles));
        titlesList.relocate(10,35);
        titlesList.setPrefSize(200,150);

        ListView<String> yearsList = new ListView<String>();
        yearsList.setItems(FXCollections.observableArrayList(years));
        yearsList.relocate(220,35);
        yearsList.setPrefSize(60,150);

        ListView<String> lengthsList = new ListView<String>();
        lengthsList.setItems(FXCollections.observableArrayList(lengths));
        lengthsList.relocate(290,35);
        lengthsList.setPrefSize(60,150);

        // Create button pane
        DVDButtonPane buttonPane = new DVDButtonPane();
        buttonPane.relocate(30,200);

        // Don’t forget to add the components to the window, set the title,
        // make it non-resizable, set Scene dimensions and then show the stage
        aPane.getChildren().addAll(titleLabel, yearLabel, lenLabel, titlesList,
                yearsList, lengthsList, buttonPane);
        primaryStage.setTitle("My DVD Collection");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane, 349, 228));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
