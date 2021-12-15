import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Data;

import java.util.ArrayList;

public class JavaFxApp extends Application {
      JSONHelper jsonHelper;
      VBox vBox;
      Button findButton;
      TextField textField = new TextField();

      private void infoAboutBus(ArrayList<Data> dataArrayList) {
            vBox.getChildren().clear();

            if (dataArrayList == null) {
                  Label notFound = new Label("Не удалось найти маршрут");
                  vBox.getChildren().add(notFound);
            } else {
                  for (Data d : dataArrayList) {
                        Label marschLable = new Label("Маршрут №" + d.getData().getMarsh());
                        vBox.getChildren().add(marschLable);

                        Label updatedTimeLabel = new Label("Updated: " + d.getUpdated_at());
                        vBox.getChildren().add(updatedTimeLabel);

                        Label timeNavLabel = new Label("Время: " + d.getData().getTimeNav());
                        vBox.getChildren().add(timeNavLabel);

                        Label latitudeLabel = new Label("latitude: " + d.getData().getLatitude());
                        vBox.getChildren().add(latitudeLabel);

                        Label longitudeLabel = new Label("Longitude: " + d.getData().getLongitude());
                        vBox.getChildren().add(longitudeLabel);

                        Label speedLabel = new Label("Speed: " + d.getData().getSpeed());
                        vBox.getChildren().add(speedLabel);

                        Label garagNumLabel = new Label("garagNum: " + d.getData().getGaragNumb());
                        vBox.getChildren().add(garagNumLabel);
                  }
            }
      }

      @Override
      public void start(Stage stage) {
            findButton = new Button("Искать");
            findButton.setMaxWidth(500);
            findButton.setMaxHeight(500);
            textField.setMaxWidth(500);
            textField.setMaxHeight(500);

            findButton.setOnAction((event -> {
                  jsonHelper = new JSONHelper();
                  ArrayList<Data> dataArrayList = jsonHelper.getBusesForMarsh(textField.getText());
                  infoAboutBus(dataArrayList);
            }));

            AnchorPane pane = new AnchorPane();
            vBox = new VBox(20);
            vBox.setTranslateY(50);
            HBox hBox = new HBox(20);
            hBox.getChildren().add(textField);
            hBox.getChildren().add(findButton);
            pane.getChildren().add(hBox);
            pane.getChildren().add(vBox);

            Scene scene = new Scene(pane, 720, 800);
            stage.setTitle("Kazan buses");
            stage.setScene(scene);
            stage.show();
      }
}
