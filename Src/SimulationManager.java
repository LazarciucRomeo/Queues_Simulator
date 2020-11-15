import javax.naming.Name;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.BlockingQueue;

import static javax.swing.UIManager.*;


public class SimulationManager  implements Runnable {



    public int timeLimit=100;
    public int maxProcessingTime=10;
    public int minProcessingTime=2;
    public int numberOfServers=100;
    public int numberOfClients=100;
    public SelectionPolicy selectionPolicy=SelectionPolicy.SHORTEST_TIME;
    Vector<Integer> vec = new Vector<Integer>();
    private Scheduler scheduler;
     private List<Task> generatedTasks ;








    public SimulationManager() {
        generatedTasks = new ArrayList<>();

        scheduler = new Scheduler(20, 10);

    }
    class SortbyTime implements Comparator<Task>
    {
        public int compare(Task a, Task  b)
        {
            return a. getArrivalTime()- b. getArrivalTime();
        }
    }
    public void generateNRandomTasks(){
        int  idTasks;
        int rand1;
        int rand2;

        Random rand = new Random(198);

        for(int i=0;i< vec.get(0);i++) {

            idTasks=i+1;
            rand1=vec.get(3)+(rand.nextInt(vec.get(4)-vec.get(3)+1));
            rand2=vec.get(5)+(rand.nextInt(vec.get(6)-vec.get(5)+1));
            Task clienti= new Task(idTasks, rand1, rand2);

            generatedTasks.add(clienti);
        }
        Collections.sort(generatedTasks, new SortbyTime());


    }
    public void citireFisier(){
        int i=0;
        try{
            File Obj = new File("In-Test.txt");
            Scanner myReader=new Scanner(Obj);

            while(myReader.hasNextLine()){
                String data = myReader.nextLine ();

                String[] datasplit=data.split(",");
                for (String val :datasplit ) {

                    vec.add(Integer.parseInt(val));
                    System.out.println(vec.get(i));
                    i++;
                }
            }
            myReader.close();

        }
        catch (FileNotFoundException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
    public void afisareFisier(){
        int i=0;
        try {
            File out = new File("Out-Test");
            FileWriter myWriter= new FileWriter(out);
            for( i=0; i<= vec.get(2) ; i++) {

                myWriter.write("Time " + i + "\n");
                myWriter.write("Waiting clients: ");
                    for(int j=0; j< vec.get(0); j++)
                myWriter.write(generatedTasks.get(j).toString());
                    myWriter.write("\n");
                    for(int x=0; x<vec.get(1); x++)
                myWriter.write("Queue closed\n");

            }
            myWriter.close();
            System.out.println("Success ");

        }
        catch (IOException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }

    }


    @Override
    public  void  run() {

        int currentTime=0;
        citireFisier();
        generateNRandomTasks();

        File out = new File("Out-Test.txt");
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(out);
        }
         catch (IOException e) {
        e.printStackTrace();

    }


            while (currentTime <=vec.get(2)) {
                int ok = 0;




                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        myWriter.write("Time " + currentTime + "\n");
                        myWriter.write("Waiting clients: ");
                        for (int j = 0; j <vec.get(0); j++) {



                            if (currentTime == generatedTasks.get(j).getArrivalTime()) {


                                scheduler.dispatchTask(generatedTasks.get(j));

                                generatedTasks.remove(0);
                                vec.set(0,vec.get(0)-1);
                                j--;


                            }
                            else {
                                myWriter.write(generatedTasks.get(j).toString());
                            }


                        }
                        myWriter.write("\n");
                        for (int x = 1; x <= vec.get(1); x++) {

                            int z = x + 1;
                            if (scheduler.getServers().get(x).getTasks().isEmpty() == false) {
                                myWriter.write("Queue " + z + ":" + scheduler.getServers().get(x).getTasks() + "\n");
                            } else
                                myWriter.write("Queue " + z + ":" + "closed\n");



                            if (scheduler.getServers().get(x).getTasks().isEmpty() == true)
                                ok = 1;
                            else
                                ok = 0;
                        }
                            if (generatedTasks.isEmpty() == true && ok == 1)
                                currentTime = vec.get(2);


                    } catch (IOException e) {

                    }


                    currentTime++;

                }
                try {
                    myWriter.close();
                    System.out.println("Success");
                } catch (IOException e) {

                }
                System.exit(1);

    }

    public static void main(String[] args){
        SimulationManager gen=new SimulationManager();
        Thread t= new Thread(gen);

        t.start();


    }
}
