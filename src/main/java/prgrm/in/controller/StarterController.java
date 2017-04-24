package prgrm.in.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import prgrm.in.MainCaller;
import prgrm.in.models.ProjectModel;
import prgrm.in.services.CrawlServices;
import prgrm.in.services.ExctractServices;
import prgrm.in.services.FormatServices;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by archit on 26/3/17.
 */
public class StarterController implements Initializable {
    @FXML
    Button GO_BUTTON;
    @FXML
    TextField DOMAIN_NAME,C_LIMIT;
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void START_CRAWL()throws Exception{
        int limit=-1;
        try{
            limit=Integer.parseInt(C_LIMIT.getText());
        }
        catch (Exception e){

        }
        new MainCaller().main(DOMAIN_NAME.getText(),limit);



    }
}
