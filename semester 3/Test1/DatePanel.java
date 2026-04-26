import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DatePanel extends JPanel 
{
    private class DatePanelItem extends JPanel
    {
        //JLabel nameLabel;
        JLabel tagLabel, donePic;
        JButton deleteButton;
        JButton doneButton;

        DatePanelItem(String tag,  DatePanel parent)
        {
            // nameLabel = new JLabel(name);
            // nameLabel.setBounds(0, 0, 150, 30);
            // add(nameLabel);

            tagLabel = new JLabel(tag);
            tagLabel.setBounds(190, 0, 120, 30);
            add(tagLabel);

            deleteButton = new JButton("Delete");
            deleteButton.setBounds(300, 0, 120, 30);
            add(deleteButton);

            doneButton = new JButton("Mark Done");
            doneButton.setBounds(440, 0, 120, 30);
            add(doneButton);

            donePic = new JLabel();
            try {
                donePic.setIcon(new ImageIcon(getClass().getResource("Img\\markdone.png")));
            } catch (Exception e) {
                donePic.setText("✓"); // Fallback if image not found
            }
            donePic.setBounds(590, 0, 100, 40);
            donePic.setVisible(false);
            add(donePic);

            setLayout(null);

            doneButton.addActionListener(e -> doneAction());
        }


        protected void doneAction()
        {
            donePic.setVisible(true);
        }
        
    }

    private class TaskPanel extends DatePanelItem
    {
        JLabel nameLabel;

        TaskPanel(Task task, DatePanel parent)
        {
            super(task.tag, parent);

            nameLabel = new JLabel(task.name);
            nameLabel.setBounds(10, 0, 150, 30);
            add(nameLabel);

            deleteButton.addActionListener(e -> deleteAction(task, parent));

            Style.buttons(doneButton, deleteButton);
            Style.labelField(tagLabel, nameLabel);

        }

        protected void deleteAction(Task task, DatePanel parent)
        {
            parent.remove(this);

            int j=parent.taskList.indexOf(this);

            for (int i=j; i<parent.taskList.size(); i++)
            {
                int temp = parent.taskList.get(i).getBounds().y;
                parent.taskList.get(i).setBounds(0, temp-40, parent.getBounds().width, 30);
            }

            taskList.remove(j);

            MainFrame.hash.delHash(task);

            parent.y -= 40;
            parent.repaint();
        }

    }

    private class EventPanel extends DatePanelItem
    {
        JButton eventButton;
        EventDetail eventDetail;
        Event event;

        EventPanel (Event event, String name, String tag, DatePanel parent)
        {
            super(tag, parent);

            this.event = event;
            eventButton = new JButton(name);
            eventButton.setBounds(0, 0, 150, 30);

            add(eventButton);

            Style.buttons(doneButton, deleteButton, eventButton);
            Style.labelField(tagLabel);

            deleteButton.addActionListener(e -> deleteAction(event, parent));
            eventButton.addActionListener(e ->  eventBtnAction(event));
        }

        protected void deleteAction(Event event, DatePanel parent)
        {
            parent.remove(this);

            int j=parent.eventList.indexOf(this);

            for (int i=j; i<parent.eventList.size(); i++)
            {
                int temp = parent.eventList.get(i).getBounds().y;
                parent.eventList.get(i).setBounds(0, temp-40, parent.getBounds().width, 30);
            }

            eventList.remove(j);

            parent.y -= 40;
            parent.repaint();
        }

        protected void eventBtnAction(Event event)
        {
            try {
                JFrame eventFrame = new JFrame();
                eventFrame.setLayout(null);

                eventDetail = new EventDetail(event);
                eventFrame.add(eventDetail);

                doneButton.addActionListener(e -> doneAction());

                eventFrame.setSize(500, 350);
                eventFrame.setVisible(true);
                eventFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error opening event: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        @Override
        protected void doneAction()
        {
            if(event.canBeMarkDone())
            {
                donePic.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(this, 
                "Event Can't be Mark Done, Check Event Task", "Marking uncompleted Event", -1);
            }    
        }
    }

    JLabel date;
    LocalDate localDate;
    int y;
    ArrayList<TaskPanel> taskList = new ArrayList<>();
    ArrayList<EventPanel> eventList = new ArrayList<>();

    DatePanel (LocalDate localDate)
    {
        setLayout(null);
        setDate(localDate.toString());
        setBackground(Color.LIGHT_GRAY);
        y = 45;

    }

    void setDate(String dateString)
    {
        date = new JLabel(dateString);
        date.setBounds(250, 10, 120, 20);
        Style.labelField(date);

        add(date);
        repaint();
        revalidate();
    }

    public void addTaskPanel(Task task)
    {
        TaskPanel t = new TaskPanel(task, this);
        t.setBounds(0, y, 800, 40);
        taskList.add(t);
        add(t);
        y += 40;
        repaint();
    }
    
    public void addEventPanel (Event event, String name)
    {
        EventPanel e = new EventPanel(event, name, "Event", this);
        e.setBounds(0, y, 800, 40);
        eventList.add(e);
        add(e);
        y += 40;
        repaint();
    }
}
