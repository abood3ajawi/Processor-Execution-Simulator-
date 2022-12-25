
public class Clock implements Runnable {
    private int clock;
    private final int numberClockCycles;
    public Clock(Data data){
        clock =1;
        this.numberClockCycles = data.getNumberClockCycles();
    }
    public int getClock() {
        return clock;
    }
    public void setClock() {
        clock++;
    }
    public void simulateClock(){
        for (int i =1 ; i<=clock ;i++ ){
            System.out.print("-- cycle -- " +(i) );
        }
        System.out.println();
    }
    public void start(){
        for ( ;clock<=numberClockCycles;setClock()) {
            try {

                    Thread.sleep(1000);

            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }}}

    @Override
    public void run() {
        start();
    }
}

/*
*
*   ScheduledExecutorService someScheduler = Executors.newScheduledThreadPool(1);
    Runnable someTask = new RunnableClass(); // From step 2 above
    long timeDelay = 3;
    someScheduler.scheduleAtFixedRate(someTask, 0, timeDelay, TimeUnit.SECONDS);
    class RunnableClass implements Runnable {
        public void run() {
            setClock();
            System.out.println(getClock());
        }
    }
* */
