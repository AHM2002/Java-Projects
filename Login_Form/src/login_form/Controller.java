package Login_Form;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.sql.*;

public class Controller {
//    private String default_user = "admin";
//    private String default_pass = "admin";
    
    @FXML
    private PasswordField pass;
    @FXML
    private TextField username;
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    @FXML
    void upNext(ActionEvent event) throws ClassNotFoundException, SQLException {
        String uname = username.getText();
        String pas = pass.getText();
        
        if(uname.equals("")&& pas.equals("")){
            JOptionPane.showMessageDialog(null, "Username or Password blank!");
        }
//        if(username.getText().equals(default_user) && pass.getText().equals(default_pass)){
        else{
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/supermarket?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "8910");
                pst = con.prepareStatement("Select * from admin where name=? and password=?");
                pst.setString(1, uname);
                pst.setString(2, pas);
                rs = pst.executeQuery();
                if(rs.next()){
//                    JOptionPane.showMessageDialog(null, "Login Success!");
//                 Load the Second.fxml file
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();                  
                    stage.close();
                    Parent secondRoot = FXMLLoader.load(getClass().getResource("home_view.fxml"));
                    Scene secondScene = new Scene(secondRoot);

                // Create a new stage for the Second.fxml file
//                Stage primaryStage = new Stage();
                    stage.setScene(secondScene);
                    stage.show();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Login Failed!");
                    username.setText("");
                    pass.setText("");
                    username.requestFocus();
                    } 
                }
                catch (IOException e) {
                e.printStackTrace();
                }
                
            }        
    }

}
