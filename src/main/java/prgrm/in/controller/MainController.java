package prgrm.in.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import prgrm.in.MainCaller;
import prgrm.in.UIUpdator;
import prgrm.in.services.MemoryQueue;
import prgrm.in.models.ProjectModel;
import prgrm.in.services.CrawlServices;
import prgrm.in.services.ExctractServices;
import prgrm.in.services.FormatServices;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by archit on 26/3/17.
 */
public class MainController implements Initializable{
    @FXML
    Label URL_TO_CRAWL;
    @FXML
    ListView<String> CRAWL_LIST;
    @FXML
    TextArea SOURCE_CODE;
    String url;
    int limit;
    UIUpdator ui;
    public void initialize(URL location, ResourceBundle resources) {
        url= MainCaller.url;
        limit=MainCaller.limit;

        URL_TO_CRAWL.setText("Crawling: "+url);
        ui=new UIUpdator(CRAWL_LIST,SOURCE_CODE);


        ExctractServices exctractServices=new ExctractServices();
        FormatServices formatServices=new FormatServices();
        ProjectModel projectModel=new ProjectModel();
        projectModel.crawlLimit=limit;
        projectModel.baseUrl=formatServices.formatUrl(url);
        CrawlServices crawlServices=new CrawlServices(projectModel,exctractServices,formatServices,ui);
        crawlServices.start();

        CRAWL_LIST.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                String urls= CRAWL_LIST.getSelectionModel().getSelectedItem();
                urls=urls.substring(urls.indexOf(":")+1).trim();
                ui.getUrl(urls);
            }
        });
    }

}
