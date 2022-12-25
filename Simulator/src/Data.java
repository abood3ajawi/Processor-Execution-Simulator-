import java.io.*;
import java.nio.charset.StandardCharsets;

public class Data {
   private final int numberProcessors;
   private final int numberClockCycles;
   private int numberTask;
   private TaskInformation[] taskInformation;

    public Data(int numberProcessors, int numberClockCycles, String filePath){
       this.numberProcessors =numberProcessors;
       this.numberClockCycles =numberClockCycles;
       read(filePath);
   }
    public int getNumberProcessors() {
        return numberProcessors;
    }
    public int getNumberClockCycles() {
        return numberClockCycles;
    }
    public int getNumberTask(){ return numberTask; }
    public TaskInformation[] getTaskInformation() { return taskInformation; }
    private   void read(String filePath){
     try(FileInputStream input = new FileInputStream(new File(filePath))) {
         InputStreamReader isr = new InputStreamReader(input, StandardCharsets.UTF_8);
         BufferedReader buffer = new BufferedReader(isr);
         String line;
         numberTask = Integer.parseInt(buffer.readLine());
         taskInformation =new TaskInformation[numberTask];
         int count = 0;
         while ((line = buffer.readLine()) != null) {
             taskInformation[count] =new TaskInformation(Character.getNumericValue(line.charAt(0)),Character.getNumericValue(line.charAt(2)),Character.getNumericValue(line.charAt(4)),count+1);
             count++;
         }
     } catch (IOException e) {
         throw new RuntimeException(e);
     }
        arrange();
        for (TaskInformation taskInformation: taskInformation) {
            System.out.println(taskInformation.getId()+" "+ taskInformation.getCreationTime()+" "+taskInformation.getExecutionTime()+" "+taskInformation.getPriority());
        }
    }
    public  void arrange(){
        for(int i = 0 ;i<getNumberTask();i++){
            for(int j = 0 ;j<getNumberTask();j++){
                if(taskInformation[i].getCreationTime() ==taskInformation[j].getCreationTime()  ){
                    if(taskInformation[i].getPriority()>taskInformation[j].getPriority()){
                        TaskInformation temp = taskInformation[i];
                        taskInformation[i] =taskInformation[j];
                        taskInformation[j] = temp;
                    }
                    else if(taskInformation[i].getPriority()==taskInformation[j].getPriority()){
                        if(taskInformation[i].getExecutionTime()>taskInformation[j].getExecutionTime()) {
                            TaskInformation temp = taskInformation[j];
                            taskInformation[j] = taskInformation[i];
                            taskInformation[i] = temp;
                        }

                   }
                }
            }

        }
    }
}
