/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculator;
 
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
 
/**
 * FXML Controller class
 *
 * @author rajesh
 */
public class Controller implements Initializable {
 
    Double temp = 0.0, sum = 0.0;
    boolean isOperatorPressed;
    String operatorPressed = "";
    
    @FXML
    TextField outputTF;       
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        outputTF.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                if (!newValue.matches("\\d*([\\.]\\d*)?")) {
//                    outputTF.setText(oldValue);
//                }
//            }
//        });
    }    
    
    @FXML
    private void onNumberClick(ActionEvent event) {
//        if(event.getSource() instanceof Button) {
            Button btn = (Button)event.getSource();                                               
            if(isOperatorPressed) {
                outputTF.setText(outputTF.getText()+btn.getText().trim());                
            } else {
                outputTF.setText(outputTF.getText().trim() + btn.getText().trim());
            }
            isOperatorPressed = false;
//        }
    }
    
    @FXML
    private void onOperatorClick(ActionEvent event) {
//        if(event.getSource() instanceof Button) {
            Button btn = (Button)event.getSource();            
            if (!outputTF.getText().isEmpty()) {
//                String str = outputTF.getText();
//                String[] txt = str.split("[X+-/%]");
//                System.out.println(Double.valueOf(str));
//                for (String txt1 : txt) {
//                    System.out.println(txt1);
//
//                    temp += Double.valueOf(txt1);
//                }
                temp = Double.valueOf(outputTF.getText());
                
                switch (operatorPressed) {                    
                    case "/":
                        sum /= temp;
                        break;
//                    case "%":
//                        sum = temp*100;
//                        break;
                    case "X":
                        sum *= temp;
                        break;
                    case "+":
                        sum += temp;
                        break;
                    case "-":
                        sum -= temp;
                        break;
                    default:
                        sum = temp;
                }
            }                     
            if (btn.getText().equals("=")) {
                outputTF.setText(String.valueOf(sum));  
                operatorPressed = "";
            }
            else if (btn.getText().equals("%")) {
                outputTF.setText(String.valueOf(sum*100));
                }
            else {
                operatorPressed = btn.getText().trim();
                System.out.println(operatorPressed);
                System.out.println("SUM: "+String.valueOf(sum));
                System.out.println("TEMP: "+String.valueOf(temp));

//                outputTF.setText(outputTF.getText()+operatorPressed);                
                outputTF.setText("");                
            }
            isOperatorPressed = true;
        }
//    }        
    
    @FXML
    private void onDELClick(ActionEvent event) {
        if(outputTF.getText().length() > 0) {
            outputTF.setText(outputTF.getText(0, outputTF.getText().length() - 1));
        }        
    }
    
    @FXML
    private void onCEClick(ActionEvent event) {
        outputTF.setText("");
        temp = 0.0;
        sum = 0.0;
        isOperatorPressed = false;
        operatorPressed = "";
    }
}