package prgrm.in.services;

import prgrm.in.UIUpdator;
import prgrm.in.models.ProjectModel;
import prgrm.in.services.threads.ProcessingThread;
import prgrm.in.services.threads.SourceCodeThread;
import prgrm.in.services.threads.TerminationThread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by archit on 21/3/17.
 */
public class CrawlServices {

    public final ProjectModel projectModel;
    public final FormatServices formatServices;
    public final ExctractServices extractServices;
    private final UIUpdator uiupdate;

    public CrawlServices(ProjectModel projectModel,ExctractServices exctractServices, FormatServices formatServices
                         ,UIUpdator uiupdate){

        this.projectModel=projectModel;
        this.extractServices=exctractServices;
        this.formatServices=formatServices;
        this.uiupdate=uiupdate;
    }
    public void start(){
        MemoryQueue memoryQueue=new MemoryQueue();
        memoryQueue.toDownloadUrlQueue.add(this.projectModel.baseUrl);

        Runnable sourceCodeThread=new SourceCodeThread(this,memoryQueue,uiupdate);
        Runnable processingThread=new ProcessingThread(this,memoryQueue);



        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(sourceCodeThread, 0,10, TimeUnit.MILLISECONDS);


        ScheduledExecutorService executor2 = Executors.newScheduledThreadPool(1);
        executor2.scheduleAtFixedRate(processingThread, 0,10, TimeUnit.MILLISECONDS);


        ScheduledExecutorService executor3 = Executors.newScheduledThreadPool(1);
        Runnable terminatingThread=new TerminationThread(memoryQueue,executor,executor2,executor3);

        executor3.scheduleAtFixedRate(terminatingThread, 0,100, TimeUnit.MILLISECONDS);
    }


}
