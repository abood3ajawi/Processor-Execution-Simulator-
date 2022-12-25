public class TaskInformation {
    private final int creationTime;
    private final int executionTime;
    private final int priority;

    private final int id;

    private boolean done;
    public TaskInformation(int creationTime,int executionTime,int priority, int id){
        this.creationTime = creationTime;
        this.executionTime = executionTime;
        this.priority = priority;
        this.id = id;
        this.done =false;
    }

    public int getCreationTime() {
        return creationTime;
    }
    public int getExecutionTime() {
        return executionTime;
    }
    public int getPriority() {
        return priority;
    }
    public boolean isDone(){
        return done;
    }

    public int getId() {
        return id;
    }
    public void Done(){
         done=true;
    }

}
