import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import java.util.concurrent.atomic.AtomicInteger;

public class Server implements  Runnable{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;


    public Server(){
       tasks=  new ArrayBlockingQueue<Task>(100);
       AtomicInteger waitingPeriod= new AtomicInteger();

    }
    public void addTask (Task newTask){
        tasks.add(newTask);



    }
    public  void run(){

        while(true){

        Task i= tasks.peek();
        if(i!=null) {
            i.setProcessingTime(i.getProcessingTime() - 1);

            if (i.getProcessingTime() == 0){
                i = tasks.poll();

        }
        }



            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
    public String getTasks(){
    String x ="";

      for(Task i: tasks ) {

          x += i.toString() ;
      }
            return x;

    }
    public BlockingQueue<Task> getListaTask(){
        return tasks;
    }
}
