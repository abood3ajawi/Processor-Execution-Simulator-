import java.util.Queue;
import java.util.TreeMap;
public class Scheduler implements Runnable {
    QueueTask queue ;
    Processor[] processor;
    Clock clock;
    public Scheduler(QueueTask queue , Processor[] processor,Clock clock){
        this.queue =queue;
        this.processor =processor;
        this.clock = clock;
    }
    public Processor availableProcess(){
        Processor processAvailable = null;
        for (Processor value : processor) {
            if (value.isAvailable()) {
                processAvailable = value;
                break;
            }
        }
        return processAvailable;
    }
    @Override
    public void run() {
    while (clock.getClock() <= 10){
        Processor processAvailable = availableProcess();
        TaskInformation fistElementInQueue =(!queue.getQueueTask().isEmpty() ? queue.getQueueTask().element() : null);
        if(processAvailable == null || fistElementInQueue ==null) continue;
        try {
            processAvailable.setTask(fistElementInQueue);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Thread availableProcessThread = new Thread(processAvailable);
            availableProcessThread.start();

    }
        }

    }
