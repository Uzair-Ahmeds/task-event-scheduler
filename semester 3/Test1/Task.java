import java.time.LocalDate;

public class Task 
{
    String name;
    LocalDate date;
    String tag;
    Integer pValue;
    boolean isCompleted;

    Task (String name, LocalDate date, String tag)
    {
        this.name = name;
        this.date = date;
        this.tag = tag.toLowerCase();

        if (tag.equals("important")) pValue = 0;
        else if (tag.equals("urgent")) pValue = 1;
        else pValue = 2;

        isCompleted = false;

    }

    public void markDone() {isCompleted = true;}

    public String toString()
    {
        return "[Task Name: "+name+", Task Date: "+date+", Tag: "+tag+"]";
    }


}
