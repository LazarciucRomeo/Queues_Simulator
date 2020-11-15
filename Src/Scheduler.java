import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;



public Scheduler(int maxNoServers, int maxTasksPerServer){
        servers= new ArrayList<>();
        for(int i=0; i< maxNoServers; i++) {
            Server Serv = new Server();
            servers.add(Serv);
            Thread thread = new Thread( Serv);
            thread.start();
        }
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        changeStrategy(SelectionPolicy.SHORTEST_QUEUE);

}

public void changeStrategy(SelectionPolicy policy){



    if(policy == SelectionPolicy.SHORTEST_QUEUE){
        strategy =  new ConcreteStrategyQueue();
    }
    if(policy ==  SelectionPolicy.SHORTEST_TIME){
        strategy = new ConcreteStrategyTime();
    }
}
public void dispatchTask(Task t){

    strategy.addTask(servers,t);

}
public List<Server> getServers(){ return servers;
}




}
