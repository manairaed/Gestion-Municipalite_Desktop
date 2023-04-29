/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author AMEN
 */
public class ClientController implements Initializable {

    @FXML
    private AnchorPane ap_main;
    @FXML
    private Button btnSend;
    @FXML
    private TextField txtMessage;
    @FXML
    private ScrollPane spMain;
    @FXML
    private VBox vboxMessage;

    private Client client;

    /**
     * Initializes the controller class.
     */

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            client = new Client(new Socket("Localhost", 1521));
            System.out.println("Connecte !!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        vboxMessage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                spMain.setVvalue((Double) newValue);
            }

        });

        client.receiveMessageFromServer(vboxMessage);

        btnSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String messageToSend = txtMessage.getText();
                if (!messageToSend.isEmpty()){
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER_RIGHT);
                    hBox.setPadding(new Insets(5,5,5,10));
                    Text text = new Text(messageToSend);
                    TextFlow textFlow = new TextFlow(text);
                    textFlow.setStyle(" -fx-color: rgb(239,242,255); "
                            + " -fx-background-color: rgb(15,125,242);"
                            + " -fx-background-radius: 20px;");
                    textFlow.setPadding(new Insets(5, 10, 5, 10));
                    text.setFill(Color.color(0.934, 0.945, 0.996));
                    hBox.getChildren().add(textFlow);
                    vboxMessage.getChildren().add(hBox);
                    client.sendMessageToServer(messageToSend);
                    txtMessage.clear();
                }
            }

        });
    }
    public static void addLabel(String messageFromServer, VBox vbox){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 5, 5, 10));
        Text text = new Text(messageFromServer);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle(" -fx-background-color: rgb(233,233,235)"
                + " -fx-background-radius: 20px");
        textFlow.setPadding(new Insets(5, 10, 5, 10));
        hBox.getChildren().add(textFlow);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vbox.getChildren().add(hBox);
                
            }

        });
    }

}
