import java.util.*;

public class HashMap 
{
    ArrayList<Task>[] listArray;

    HashMap()
    {
        listArray = new ArrayList[3];

        listArray[0] = new ArrayList<Task>();
        listArray[1] = new ArrayList<Task>();
        listArray[2] = new ArrayList<Task>();

    }

    void hashTask(Task task)
    {
        if (task.tag.equals("important")) listArray[0].add(task);
        else if (task.tag.equals("urgent")) listArray[1].add(task);
        else if (task.tag.equals("normal"))  listArray[2].add(task);
    }

    void delHash(Task task)
    {
        if (task.tag.equals("important"))
        {
            Iterator<Task> iterator = listArray[0].iterator();

            while (iterator.hasNext()) 
            {
                Task t = iterator.next();
                if (t.equals(task)) 
                    iterator.remove(); 
            }
        }

        else if (task.tag.equals("urgent"))
        {
            Iterator<Task> iterator = listArray[1].iterator();
            
            while (iterator.hasNext()) 
            {
                Task t = iterator.next();
                if (t.equals(task)) 
                    iterator.remove(); 
            }        
        }

        else if (task.tag.equals("normal"))
        {
            Iterator<Task> iterator = listArray[2].iterator();
            
            while (iterator.hasNext()) 
            {
                Task t = iterator.next();
                if (t.equals(task)) 
                    iterator.remove(); 
            }        
        }
    }

    ArrayList<Task> getImp() {return listArray[0];}
    ArrayList<Task> getUrg() {return listArray[1];}
    ArrayList<Task> getNor() {return listArray[2];}
}
