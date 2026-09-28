/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import utiles8puzzle.Utils;

/**
 *
 * @author jsoler
 */
public class FXMLDocumentController implements Initializable {
    private Label labelMessage;
    @FXML
    private Button b1, b2, b3, b4, b5, b6, b7, b8, bReiniciar;
    @FXML
    private GridPane tablero;
    @FXML
    private Button hueco;
    
    private int filaHueco = 2;
    private int columnaHueco = 2;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empezarPuzzle();
    }    

    @FXML
    private void mueveNumero(MouseEvent event) {
        // Obtener el botón que fue clicado
        Button boton = (Button) event.getSource();
        
        // Obtener su posición actual en el GridPane
        Integer fila = GridPane.getRowIndex(boton);
        Integer columna = GridPane.getColumnIndex(boton);
        if(fila == null){fila = 0;}
        if(columna == null){columna = 0;}
        
        boolean esAdyacente =
                (fila.equals(filaHueco) && Math.abs(columna - columnaHueco) == 1) ||
                // Misma fila y columnas consecutivas
                (columna.equals(columnaHueco) && Math.abs(fila - filaHueco) == 1);
                // Misma columna y filas consecutivas 
        
        if(esAdyacente) {
            GridPane.setRowIndex(boton, filaHueco);
            GridPane.setColumnIndex(boton, columnaHueco);
            
            filaHueco = fila;
            columnaHueco = columna;
        
            GridPane.setRowIndex(hueco, filaHueco);
            GridPane.setColumnIndex(hueco, columnaHueco);
            
            verificarVictoria();
        }
    }

    @FXML
    private void reiniciarPuzzle(MouseEvent event) {
        empezarPuzzle();
    }
    
    private void empezarPuzzle() {
        int[] numeros = Utils.generarVectorAleatorio(8);
        int i=0;
        for (Node node : tablero.getChildren()) {
            if (node != hueco) {
                ((Button) node).setText(String.valueOf(numeros[i]));
                i++;
            }
        }
        // Colocar hueco en la esquina inferior derecha
        filaHueco = 2;
        columnaHueco = 2;
        GridPane.setRowIndex(hueco,filaHueco);
        GridPane.setColumnIndex(hueco,columnaHueco);
    }
    
    private void verificarVictoria() {
        int numeroEsperado = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Saltar el hueco
                if (i == filaHueco && j == columnaHueco) continue;

                Button boton = getBotonEnPosicion(i, j);
                if (boton == null) return; // seguridad

                int numBoton = Integer.parseInt(boton.getText());
                if (numBoton != numeroEsperado) return; // si falla, salir sin alerta

                numeroEsperado++;
            }
        }

        // Verificar que el hueco esté en (2,2)
        if (filaHueco != 2 || columnaHueco != 2) return;

        // Si llegamos hasta aquí, todo está correcto
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Puzzle completo!");
        alert.setHeaderText(null);
        alert.setContentText("🎉 ¡Enhorabuena, has resuelto el puzzle!");
        alert.showAndWait();
    }
    
    private Button getBotonEnPosicion(int fila,int columna) {
        for (Node node : tablero.getChildren()) {
            Integer f = GridPane.getRowIndex(node);
            Integer c = GridPane.getColumnIndex(node);
            if (f == null) f = 0;
            if (c == null) c = 0;

            if (f == fila && c == columna) return (Button) node;
        }
        return null;
    }
}

