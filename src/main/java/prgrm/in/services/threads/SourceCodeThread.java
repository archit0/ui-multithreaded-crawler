package prgrm.in.services.threads;

import prgrm.in.UIUpdator;
import prgrm.in.services.CrawlServices;
import prgrm.in.services.MemoryQueue;
import prgrm.in.utils.RequestUtils;

/**
 * Created by archit on 22/3/17.
 */
public class SourceCodeThread implements Runnable {
    private final MemoryQueue memoryQueue;
    private final CrawlServices crawlServices;
    private final UIUpdator uiUpdator;

    public SourceCodeThread(CrawlServices crawlServices, MemoryQueue memoryQueue, UIUpdator uiUpdator) {
        this.crawlServices = crawlServices;
        this.memoryQueue = memoryQueue;
        this.uiUpdator=uiUpdator;
    }

    public void run() {
        try {
            if(this.memoryQueue.toDownloadUrlQueue.size()!=0) {
                String url = this.memoryQueue.toDownloadUrlQueue.take();
                int total = this.memoryQueue.data.size();
                if (total >= this.crawlServices.projectModel.crawlLimit
                        && this.crawlServices.projectModel.crawlLimit != -1) {
                    uiUpdator.update("Crawl Limit Reached Can't Crawl "+url);
                } else {
                    if (!this.memoryQueue.data.containsKey(url)) {
                        uiUpdator.update((total + 1) + ":" + url);
                        String sourceCode = RequestUtils.sourceCode(url);
                        this.memoryQueue.data.put(url, sourceCode);
                        this.memoryQueue.toProcessUrlQueue.add(url);
                    }
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
