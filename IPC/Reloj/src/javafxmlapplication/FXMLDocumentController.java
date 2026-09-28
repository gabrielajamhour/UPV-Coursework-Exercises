/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;
import java.util.concurrent.ForkJoinWorkerThread;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author jsoler
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label hora;
    
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TareaReloj tarea = new TareaReloj();
        Thread th = new Thread(tarea);
        th.setDaemon(true);
        th.start();
//        hora.textProperty().bind(tarea.messageProperty());
    } 
    
    class TareaReloj extends Task<Void> {

        int DELAY = 500;

        @Override
        protected Void call() throws Exception {
            while (true) {
                // Actualizar mensaje
                updateMessage(LocalDateTime.now().
                        format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)));
                Thread.sleep(DELAY);
                Platform.runLater(()->{  hora.setText(LocalDateTime.now().
                        format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)));  });
            }
        }
    }
    
}
