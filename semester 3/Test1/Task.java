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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Task)) {
            return false;
        }
        Task other = (Task) obj;
        return this.name.equals(other.name) && this.date.equals(other.date) && this.tag.equals(other.tag);
    }

    @Override
    public int hashCode() {
        return (name + date.toString() + tag).hashCode();
    }

    public String toString()
    {
        return "[Task Name: "+name+", Task Date: "+date+", Tag: "+tag+"]";
    }


}
