import java.util.List;

interface Strategy {
    public void addTask(List<Server> servers, Task t);
}