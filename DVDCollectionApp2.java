import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DVDCollectionApp2  extends Application {
    private DVDCollection model;
    private ListView<DVD> tList;

    public DVDCollectionApp2() {
        model = DVDCollection.example1();
    }

    public void start(Stage primaryStage) {
        Pane  aPane = new Pane();

        // Create the labels
        Label label1 = new Label("DVDs");
        Label label2 = new Label("Title");
        Label label3 = new Label("Year");
        Label label4 = new Label("Length");
        label1.relocate(10,10);
        label2.relocate(10,202);
        label3.relocate(10,242);
        label4.relocate(120,242);

        // Create the TextFields
        TextField tField = new TextField();
        TextField yField = new TextField();
        TextField lField = new TextField();
        tField.relocate(50,200);
        yField.relocate(50,240);
        lField.relocate(180,240);
        tField.setPrefSize(500,30);
        yField.setPrefSize(55,30);
        lField.setPrefSize(45,30);

        // Create the lists
        tList = new ListView<DVD>();
        tList.setItems(FXCollections.observableArrayList(model.getDVDList()));
        tList.relocate(10,40);
        tList.setPrefSize(540,150);
        tList.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DVD dvd = tList.getSelectionModel().getSelectedItem();

                tField.setText(dvd.getTitle());
                yField.setText("" + dvd.getYear());
                lField.setText("" + dvd.getDuration());
            }
        });

        // Create the buttons
        DVDButtonPane buttonPane = new DVDButtonPane();
        buttonPane.relocate(250,240);

        // Retrieve buttons
        ObservableList<Node> children = buttonPane.getChildren();

        // Retrieve addButton
        Button addButton = (Button) children.get(0);
        /**
         * Add error checking to this method (you get user input here)
         */
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String newTitle = tField.getText();
                int y           = Integer.parseInt(yField.getText());
                int minutes     = Integer.parseInt(lField.getText());

                tList.getItems().add(new DVD(newTitle, y, minutes));
            }
        });

        // Retrieve deleteButton
        Button deleteButton = (Button) children.get(1);
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DVD dvd      = tList.getSelectionModel().getSelectedItem();
                String title = dvd.getTitle();
                int year     = dvd.getYear();
                int minutes  = dvd.getDuration();

                for (int i = 0; i < tList.getItems().size(); i++) {
                    if (year == tList.getItems().get(i).getYear() &&
                            minutes == tList.getItems().get(i).getDuration() &&
                            title.equals(tList.getItems().get(i).getTitle())) {
                        tList.getItems().remove(i);
                        break;
                    }
                }
            }
        });

        // Add all the components to the window
        aPane.getChildren().addAll(label1, label2, label3, label4, tField, yField,
                lField, tList, buttonPane);

        primaryStage.setTitle("My DVD Collection");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane, 548, 268));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
