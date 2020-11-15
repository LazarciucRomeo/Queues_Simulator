public class Task {
    private int ID;
     private int arrivalTime;
    private int processingTime;


    public Task(int ID, int arrivalTime,int processingTime){

        this.ID=ID;
        this.arrivalTime=arrivalTime;
        this.processingTime=processingTime;

    }

    @Override
    public String toString(){
       return "(" + this.ID + "," + this.arrivalTime + "," + this.processingTime + ") ";
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public int getID() {
        return ID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }





}

