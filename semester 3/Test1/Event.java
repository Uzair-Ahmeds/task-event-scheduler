import java.time.LocalDate;
import java.util.*;

public class Event 
{
    String eventTitle;
    LocalDate openingDate, closingDate;
    PriorityQueue<Task> taskQueue;    
    boolean isCompleted;

    Event(String eventTitle, LocalDate openingDate, LocalDate closingDate)
    {
        this.eventTitle = eventTitle;
        this.openingDate = openingDate;
        this.closingDate = closingDate;

        taskQueue = new PriorityQueue<>((task1, task2) -> task1.pValue.compareTo(task2.pValue));
    }
    
    public void addTask(Task task)
    {
        taskQueue.add(task);
    }

    Task task_priority() 
    {
        if(taskQueue.isEmpty())
        return null;
        else
        return taskQueue.peek();
    }

    void addtask(String name, LocalDate date, String tag)
    {
        
        Task t1=new Task(name, date, tag);
        taskQueue.add(t1);
    }

    boolean canBeMarkDone(){
        if(!taskQueue.isEmpty()){
        for(Task dep: taskQueue){
            if(!dep.isCompleted)
            return false;
        }}
        return true;
    }

    void updateDoneStatus() {
        if (!this.canBeMarkDone()) {
            this.isCompleted=false; 
        }
    }

    void removeTaskFromHeap(Task task) {
        taskQueue.remove(task);
    }
}
