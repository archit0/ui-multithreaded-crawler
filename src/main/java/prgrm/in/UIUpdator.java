package prgrm.in;

import com.mongodb.*;
import com.mongodb.util.JSON;
import javafx.application.Platform;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import prgrm.in.services.MemoryQueue;

/**
 * Created by archit on 26/3/17.
 */
public class UIUpdator {
    private final ListView<String> crawl;
    private final TextArea SOURCE_CODE;
    DBCollection coll;
    public UIUpdator(ListView crawledList, TextArea SOURCE_CODE){
        this.crawl=crawledList;
        this.SOURCE_CODE=SOURCE_CODE;
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Now connect to your databases
        DB db = mongoClient.getDB("crawler");
        coll = db.getCollection("data");

    }
    public void update(String url){
        final String ar=url;
        Platform.runLater(new Runnable() {
             public void run() {
                crawl.getItems().add((ar));
            }
        });
    }
    public void getUrl(String url) {
        BasicDBObject doc = new BasicDBObject("url", url);

        DBCursor dbCursor = coll.find(doc);
        while (dbCursor.hasNext()) {

            DBObject ab=dbCursor.next();

            SOURCE_CODE.setText(ab.get("sourceCode").toString());
            break;
        }
    }
}
