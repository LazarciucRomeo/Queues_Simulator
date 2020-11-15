import java.util.List;

import static java.lang.Integer.MAX_VALUE;

public class ConcreteStrategyQueue implements Strategy {
        @Override
        public void addTask(List<Server> servers, Task t){
            int y=MAX_VALUE;
            Server nrserver = null;
            for(Server i:servers ){
               int x = i.getListaTask().size();
                if(x<y) {
                    y = x;
                    nrserver = i;
                }
            }
            nrserver.addTask(t);


        }
}
