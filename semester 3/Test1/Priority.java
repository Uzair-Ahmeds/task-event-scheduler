import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Priority{
    PriorityQueue<Event> minheap;
    boolean is_done;
    
    Priority(){
        minheap=new PriorityQueue<>((e1, e2) -> e1.openingDate.compareTo(e2.openingDate));
        is_done=false;
        
    }

    void add_event_in_minheap(Event e){
        minheap.add(e);
    }

    Event top_event(){
        return minheap.peek();
    }

    int size() {
        return minheap.size();
    }

    List<Event> getAllEvents() {
        List<Event> allevents = new ArrayList<>(minheap);
        return allevents;
    }

    void markdone(){
        is_done=true;
    }

    void remove_event(Event e){
        minheap.remove(e);
    }

}