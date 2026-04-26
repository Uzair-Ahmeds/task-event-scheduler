import javax.swing.*;
import java.awt.*;
import java.util.*;

public class EventDetail extends JPanel
{
    JLabel  openingDateLabel, closingDateLabel,
            taskNameLabel, taskTagLabel, taskDateLabel, taskDonePic;

    JButton prevBtn, nextBtn, taskDoneBtn, taskDelBtn, eventDoneBtn;

    JPanel taskPanel;

    int current_index = 0;

    Event event;

    ArrayList<Task> all_tasks; 

    EventDetail(Event event)
    {
        if (event == null) {
            JOptionPane.showMessageDialog(null, "Error: Event cannot be null", "Null Event", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        this.event = event;
        all_tasks = new ArrayList<>(event.taskQueue);

        setLayout(null);
        setBounds(0, 0, 500, 500);
        setBackground(Color.LIGHT_GRAY);


        openingDateLabel = new JLabel("Event Opening date:        " + event.openingDate);
        openingDateLabel.setBounds(60, 0, 300, 30);

        closingDateLabel=new JLabel("Event Closing date:         " + event.closingDate);
        closingDateLabel.setBounds(60, 40, 300, 30); 

        eventDoneBtn = new JButton("Done");
        eventDoneBtn.setBounds(50,310,100,20);

        if (all_tasks.size()!=0)
        {
            Task task = all_tasks.get(current_index);

            taskPanel = new JPanel();
            taskPanel.setLayout(null);
            taskPanel.setBounds(10,  80, 500, 200); 
            taskPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            taskPanel.setBackground(Color.LIGHT_GRAY);

            taskNameLabel = new JLabel("Name:    " + task.name);
            taskNameLabel.setBounds(50, 5, 200, 20); 

            taskDonePic = new JLabel();
            try {
                if(task.isCompleted){
                    taskDonePic.setIcon(new ImageIcon(getClass().getResource("Img\\markdone.png")));
                    taskDonePic.setBounds(260, 30, 100, 40);
                    taskPanel.add(taskDonePic);
                } else {
                    taskDonePic.setIcon(new ImageIcon(getClass().getResource("Img\\markdone.png")));
                    taskDonePic.setBounds(260, 30, 100, 40);
                    taskPanel.add(taskDonePic);
                    taskDonePic.setVisible(false);
                }
            } catch (Exception e) {
                System.err.println("Error loading image: " + e.getMessage());
                taskDonePic.setText("✓");
                taskDonePic.setBounds(260, 30, 100, 40);
                taskPanel.add(taskDonePic);
                if (!task.isCompleted) {
                    taskDonePic.setVisible(false);
                }
            }

                taskTagLabel=new JLabel("Tag:       " + task.tag); 
                taskTagLabel.setBounds(50, 28, 200, 20); 

                taskDateLabel=new JLabel("Date:     "+ task.date); 
                taskDateLabel.setBounds(50, 51, 200, 20);

                prevBtn=new JButton("Previous");
                prevBtn.setBounds(30,140,70,20);
                prevBtn.addActionListener(e -> prevAction());

                nextBtn=new JButton("Next");
                nextBtn.setBounds(200,140,70,20);
                nextBtn.addActionListener(e -> nextAction());

                taskDoneBtn=new JButton("Done");
                taskDoneBtn.setBounds(320,140,120,20);
                taskDoneBtn.addActionListener(e -> markTaskDoneAction());

                taskDelBtn = new JButton("Delete Task");
                taskDelBtn.setBounds(200,170,120,20);
                taskDelBtn.addActionListener(e -> delTaskAction(event, task));

                taskPanel.add(taskNameLabel); taskPanel.add(taskTagLabel); taskPanel.add(taskDateLabel);
                taskPanel.add(prevBtn); taskPanel.add(nextBtn); taskPanel.add(taskDelBtn); taskPanel.add(taskDoneBtn);
                taskPanel.add(taskDonePic);

                Style.buttons(prevBtn, nextBtn, taskDelBtn, taskDoneBtn);
                Style.labelField(taskNameLabel, taskTagLabel, taskDateLabel);
                add(taskPanel);
            }
            
            add(openingDateLabel); add(closingDateLabel);
            add(eventDoneBtn);  

            Style.labelField(openingDateLabel, closingDateLabel);
            Style.buttons(eventDoneBtn);
    }

    void update_task(Task task){
        taskNameLabel.setText("Name:    " + task.name);
        taskTagLabel.setText("Tag:       " + task.tag);
        taskDateLabel.setText("Date:     " + task.date);

        if (task.isCompleted) {
            taskDonePic.setVisible(true);
        } else {
            taskDonePic.setVisible(false);
        }
        taskPanel.revalidate();
        taskPanel.repaint();
    }


    protected void eventDoenAction(Event event)
    {
        if (event == null) {
            JOptionPane.showMessageDialog(null, "Error: Event is null", "Null Event", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!event.canBeMarkDone() )
         JOptionPane.showMessageDialog(null, "This event can't be marked done. Complete dependent tasks first.");
        
        else if(event.isCompleted)
         JOptionPane.showMessageDialog(null, "This event is already done.");

        else
        {
            JOptionPane.showMessageDialog(null, "This Event is marked done.");
            event.isCompleted = true;
            taskDonePic=new JLabel();
            try {
                taskDonePic.setIcon(new ImageIcon(getClass().getResource("Img\\markdone.png")));
            } catch (Exception e) {
                taskDonePic.setText("✓");
            }
            taskDonePic.setBounds(510, 10, 100, 40);
            taskPanel.add(taskDonePic);
            taskDonePic.setVisible(true);
        }
    }

    protected void prevAction()
    {
        if(current_index>0){
            current_index--;
            update_task(all_tasks.get(current_index));}
        else {
        JOptionPane.showMessageDialog(taskNameLabel, "This is the first task.");
        }
    }

    protected void nextAction()
    {
        if(current_index<all_tasks.size()-1){
            current_index++;
            update_task(all_tasks.get(current_index));
        } else {
        JOptionPane.showMessageDialog(taskNameLabel, "This is the last task.");
        }

    }

    protected void markTaskDoneAction()
    {
        if (all_tasks == null || all_tasks.isEmpty()) {
            JOptionPane.showMessageDialog(taskNameLabel, "No tasks available.");
            return;
        }
        
        Task task=all_tasks.get(current_index);
        if(!task.isCompleted)
        {
         task.isCompleted=true;
         JOptionPane.showMessageDialog(taskNameLabel, "This task is marked Done.");
         try {
             taskDonePic.setIcon(new ImageIcon(getClass().getResource("Img\\markdone.png")));
         } catch (Exception e) {
             taskDonePic.setText("✓");
         }
         taskDonePic.setVisible(true);
         taskPanel.revalidate();
         taskPanel.repaint();
        }
        else
            JOptionPane.showMessageDialog(taskNameLabel, "This task is already done.");
    }

    protected void delTaskAction(Event event, Task task)
    {
        event.removeTaskFromHeap(task);
        all_tasks.remove(current_index);
        event.updateDoneStatus();
        JOptionPane.showMessageDialog(null, "Task removed successfully.");
        MainFrame.hash.delHash(task);
    }

    

}
