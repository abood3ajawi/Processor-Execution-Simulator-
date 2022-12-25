
public class Processor implements Runnable{
    int id;
    private TaskInformation task;
    private QueueTask queue;
    Processor(QueueTask queue,int id){
        this.queue= queue;
        this.id  = id;
    }
    public boolean isAvailable() {
        return task==null;
    }
    public void  setTask(TaskInformation task) throws InterruptedException {
        synchronized (this) {

            this.task = task;
            deAssign();
        }
    }
   public void   deAssign(){
       if(!queue.getQueueTask().isEmpty()) {
           synchronized (this) {
               queue.getQueueTask().remove();
           }
           if(queue.getQueueTask().isEmpty()){
               System.out.println("empty queue!");
               queue.simulateQueue();
           }
       }
        else {
            System.out.println("empty queue!");
            queue.simulateQueue();
        }
    }
   public void simulateProcessor(){
        System.out.println("Processor("+id+") -- > Task ID :" + (task != null ?task.getId():"free !"));
   }
    private void startExecution() throws InterruptedException {
        Thread.sleep(task.getExecutionTime()* 1000);
    }
    @Override
    public void run() {
        try {
                startExecution();
                task = null;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
