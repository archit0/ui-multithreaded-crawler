package prgrm.in;
import prgrm.in.services.MemoryQueue;
import prgrm.in.models.ProjectModel;
import prgrm.in.services.CrawlServices;
import prgrm.in.services.ExctractServices;
import prgrm.in.services.FormatServices;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.util.Scanner;

/**
 * Created by archit on 22/3/17.
 */
public class Starter  extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/start.fxml"));
        primaryStage.setTitle("Multithreaded Web Crawler");
        Scene sc = new Scene(root);
        sc.setFill(null);
        primaryStage.setScene(sc);

        primaryStage.show();
        MainCaller.stage=primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
