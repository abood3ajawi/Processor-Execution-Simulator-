public class Simulator {
    private QueueTask queue;
    private Scheduler scheduler;
    private Processor[] processor;
    private Clock clock;
    private Data data;
    Simulator(Data data){
       this.data=data;
       queue =new QueueTask();
       clock =new Clock(data);
       processor =new Processor[data.getNumberProcessors()];
        for (int i =0 ;i< data.getNumberProcessors();i++){
            processor[i] = new Processor(queue , i+1);
        }
       scheduler =new Scheduler(queue ,processor,clock);
    }
    public void addTask(){
        for(int i =0;i<data.getNumberTask();i++){
           if(data.getTaskInformation()[i].getCreationTime()==clock.getClock() && !data.getTaskInformation()[i].isDone()){
               queue.getQueueTask().add(data.getTaskInformation()[i]);
               data.getTaskInformation()[i].Done();
           }
        }
    }

    public void start(){
        boolean[] simulateHelper = new boolean[data.getNumberClockCycles()+1];
        Thread clockThread = new Thread(clock);
        Thread queueThread = new Thread(queue);
        Thread schedulerThread = new Thread(scheduler);
        Thread addTask= new Thread(() -> {
            addTask();
            while(clock.getClock() <= data.getNumberClockCycles()) {
                if(!simulateHelper[clock.getClock()]) {
                    clock.simulateClock();
                    queue.simulateQueue();
                    for (Processor processor:processor) {
                        processor.simulateProcessor();
                    }
                    simulateHelper[clock.getClock()] =true;
                }
                addTask();

            }
        });
        clockThread.start();
        queueThread.start();
        addTask.start();
        schedulerThread.start();
    }
}
