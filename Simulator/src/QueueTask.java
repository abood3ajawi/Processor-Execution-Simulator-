import java.util.LinkedList;
import java.util.Queue;
public class QueueTask implements Runnable {
    private Queue<TaskInformation> queueTask;

    public QueueTask(){
     queueTask = new LinkedList<>();
    }

    public Queue<TaskInformation> getQueueTask() {
        return queueTask;
    }
    public void simulateQueue(){
        System.out.println("inQueue : ");
        for (TaskInformation taskInformation : queueTask) {
            System.out.println("    Task ID "+taskInformation.getId()+" -- CreationTime :  "
                    +taskInformation.getCreationTime()+" -- ExecutionTime : "+taskInformation.getExecutionTime()+" -- Priority : "+taskInformation.getPriority());
        }
        System.out.println("--------------------");
    }
    @Override
    public void run() {
    }
}
