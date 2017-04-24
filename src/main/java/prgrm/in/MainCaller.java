package prgrm.in;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import prgrm.in.models.ProjectModel;
import prgrm.in.services.CrawlServices;
import prgrm.in.services.ExctractServices;
import prgrm.in.services.FormatServices;

/**
 * Created by archit on 26/3/17.
 */
public class MainCaller  {

    public static String url;
    public static int limit;
    public static Stage stage;

    public void main(String url,int limit)throws Exception {
        MainCaller.url=url;
        MainCaller.limit=limit;
        Stage dialog=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
        Scene scene = new Scene(root);
        dialog.setScene(scene);
        dialog.initOwner(stage);
        dialog.show();

    }
}
