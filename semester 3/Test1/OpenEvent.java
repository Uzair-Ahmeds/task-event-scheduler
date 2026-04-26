import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class OpenEvent extends JPanel
{
    Priority priority;
    Event currentEvent;

    JPanel clockPanel, pandaPanel, eventPanel,
           addTaskPanel;              //eventPanel

    JButton homeButton,                            //clockPanel 
            addEventButton,                                                     //pandaPanel
            addOtherEventButton, openTaskFieldsButton, backButton,             //eventPanel
            addTaskButton, clearButton;                                        //addTaskPanel

    JTextField eventNameField, openingDateField, closingDateField,          //pandaPanel
               taskNameField, taskDateField;                        //addTaskPanel
    
    JRadioButton impRadio, urgRadio, norRadio;
    ButtonGroup radioGroup;

    JLabel clockPic,                                                //clockPanel
           pandaPic, upComingEventLabel, addHeadingLabel, exicitingHeadingLabel, eventsHeadingLabel, eventNameLabel, openingDateLabel, closingDateLabel,                                        //pandaPanel
           whatNextLabel, openingTopLabel, closingTopLabel, openingDateTopLabel, closingDateTopLabel, quoteLabel, quoteWriterLabel,                                         //eventPanel
           taskNameLabel, taskDateLabel, selectTagLabel;            //addTaskPanel
    
    JSeparator pandaPanelSeparator;

    //upcoming events entities
    JLabel event_title, event_Odate, event_cdate, title, Odate, CDate, heading, headingtask;


    OpenEvent()
    {
        priority = new Priority();
        setLayout(null);
        setClockPanel();
        setPandaPanel();

    }

    public  void setCurrentEvent(Event currentEvent) {this.currentEvent = currentEvent;}

    void setClockPanel()
    {
        clockPanel = new JPanel(null);
        clockPanel.setBounds(0, 0, 250, 654);
        clockPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        clockPanel.setBackground(new Color(45, 45, 45));  
        clockPanel.setOpaque(true);

        clockPic=new JLabel();
        clockPic.setFont(new Font("Monospaced", Font.PLAIN, 40)); 
        clockPic.setForeground(Color.WHITE); 
        clockPic.setOpaque(true);
        clockPic.setBackground(new Color(0, 0, 0)); 
        clockPic.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        clockPic.setBounds(0, 0, 250, 80);

        Timer timer = new Timer(1000, e -> {
            String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
            clockPic.setText(time);  
        });
        timer.start();

        homeButton=new JButton("Home");
        try {
            homeButton.setIcon(new ImageIcon(getClass().getResource("Img\\Home.png")));
        } catch (Exception e) {
            System.err.println("Error loading Home icon: " + e.getMessage());
        }
        homeButton.setBounds(10, 150, 200,70);
        
        Style.buttons(homeButton);

        clockPanel.add(clockPic);
        clockPanel.add(homeButton); 
        add(clockPanel);
    }

    void setPandaPanel()
    {
        pandaPanel=new JPanel();
        pandaPanel.setBackground(Color.LIGHT_GRAY);
        pandaPanel.setLayout(null);
        pandaPanel.setBounds(250,0,800,1000);

        upComingEventLabel=new JLabel("No UpComing Events");
        upComingEventLabel.setFont(new Font("Segoe UI", Font.PLAIN, 40)); 
        upComingEventLabel.setForeground(Color.BLACK);
        upComingEventLabel.setBounds(40, 70, 400, 55);

        pandaPanelSeparator=new JSeparator();
        pandaPanelSeparator.setBounds(30, 205, 550,10);
        pandaPanelSeparator.setForeground(Color.BLACK);

        pandaPic=new JLabel();
        try {
            pandaPic.setIcon(new ImageIcon(getClass().getResource("Img\\panda is excited.png")));
        } catch (Exception e) {
            pandaPic.setText("[Panda Image]");
            System.err.println("Error loading panda image: " + e.getMessage());
        }
        pandaPic.setBounds(20, 190, 360,360);

        addHeadingLabel=new JLabel("Add ");
        addHeadingLabel.setBounds(360,200, 100,100);

        exicitingHeadingLabel=new JLabel("Exciting");
        exicitingHeadingLabel.setBounds(400, 240, 200, 120);

        eventsHeadingLabel=new JLabel("Events");
        eventsHeadingLabel.setBounds(400, 280,200,120);

        Style.heading(addHeadingLabel, exicitingHeadingLabel, eventsHeadingLabel);

        eventNameLabel=new JLabel("Name");
        eventNameLabel.setBounds(300, 325, 120, 120);
        eventNameField=new JTextField();
        eventNameField.setBounds(410, 370, 180, 20);
 
        JLabel dateFormatLabel = new JLabel("YYYY-MM-DD");
        dateFormatLabel.setBounds(300, 395, 180, 20);

        openingDateLabel=new JLabel("Opening date");
        openingDateLabel.setBounds(300, 360, 120, 120);
        openingDateField=new JTextField();
        openingDateField.setBounds(410, 405, 180, 20);
 
        closingDateLabel=new JLabel("Closing date");
        closingDateLabel.setBounds(300, 390, 120, 120);
        closingDateField=new JTextField();
        closingDateField.setBounds(410, 435, 180, 20);


        addEventButton=new JButton("ADD Event");
        Style.buttons(addEventButton);
        addEventButton.setBounds(450, 530, 90,30);
        addEventButton.addActionListener(e -> addEventAction());

        pandaPanel.add(upComingEventLabel); 
        pandaPanel.add(pandaPanelSeparator);
        pandaPanel.add(pandaPic);
        pandaPanel.add(addHeadingLabel); pandaPanel.add(exicitingHeadingLabel); pandaPanel.add(eventsHeadingLabel); 
        pandaPanel.add(eventNameLabel); pandaPanel.add(closingDateLabel); pandaPanel.add(openingDateLabel);
        pandaPanel.add(eventNameField); pandaPanel.add(openingDateField); pandaPanel.add(closingDateField);
        pandaPanel.add(addEventButton);
        pandaPanel.add(dateFormatLabel);

        Style.labelField(eventNameLabel, openingDateLabel, closingDateLabel, 
                        eventNameField, openingDateField, closingDateField);

        add(pandaPanel);
    }

    void openEventPanel(Event e)
    {
        eventPanel=new JPanel();
        eventPanel.setLayout(null);
        eventPanel.setBackground(Color.LIGHT_GRAY);
        eventPanel.setBounds(0, 0, 1000,1000);       
        
        whatNextLabel=new JLabel("What’s Next? ");
        whatNextLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18)); 
        whatNextLabel.setForeground(Color.BLACK);
        whatNextLabel.setBounds(380, 0, 180, 40);        

        openingTopLabel=new JLabel("Event opening date");
        openingTopLabel.setBounds(270, 25, 180, 40);

        openingDateTopLabel=new JLabel();
        openingDateTopLabel.setBounds(270, 55, 180, 30);
        openingDateTopLabel.setText(e.openingDate.toString());

        closingTopLabel=new JLabel("Event closing date");
        closingTopLabel.setBounds(450, 25, 180, 40);

        closingDateTopLabel=new JLabel();
        closingDateTopLabel.setBounds(450, 55, 180, 30);
        closingDateTopLabel.setText(e.closingDate.toString());

        JSeparator marginline_for_openingdate=new JSeparator();
        marginline_for_openingdate.setBounds(270, 90, 135, 20 );
        marginline_for_openingdate.setForeground(Color.BLACK);

        JSeparator marginline_for_closingdate=new JSeparator();
        marginline_for_closingdate.setBounds(450, 90, 135, 20 );
        marginline_for_closingdate.setForeground(Color.BLACK);

        addOtherEventButton=new JButton("Add another Event");
        addOtherEventButton.setBounds(630,35,140,22);
        addOtherEventButton.addActionListener(ea -> addOtherEventAction());


        openTaskFieldsButton=new JButton("Add Task");
        openTaskFieldsButton.setBounds(630,75,140,22);
        openTaskFieldsButton.addActionListener(ea -> openAddTaskFieldsAction());


        backButton=new JButton("Back");
        backButton.setBounds(800,560,100,20);
        backButton.addActionListener(ea -> backAction());

        addQoute();

        Style.buttons(addOtherEventButton, openTaskFieldsButton, backButton);
        Style.labelField(openingTopLabel, closingTopLabel, openingDateTopLabel, closingDateTopLabel);

        eventPanel.add(whatNextLabel);
        eventPanel.add(openingTopLabel); eventPanel.add(closingTopLabel);
        eventPanel.add(openingDateTopLabel); eventPanel.add(closingDateTopLabel);
        eventPanel.add(marginline_for_openingdate); eventPanel.add(marginline_for_closingdate);
        eventPanel.add(addOtherEventButton); eventPanel.add(openTaskFieldsButton); eventPanel.add(backButton);

        add(eventPanel);
        repaint();
        revalidate();
    }

    void addQoute()
    {
        quoteLabel=new JLabel("“The best way to predict the future is to create it.” ");
        quoteLabel.setFont(new Font("Blackadder ITC", 0, 28)); 
        quoteLabel.setForeground(new Color(0x008080)); // Teal color
        quoteLabel.setBounds(260, 100, 600, 80);

        quoteWriterLabel=new JLabel("– Peter Drucker");
        quoteWriterLabel.setFont(new Font("Viner Hand ITC", 2, 18)); 
        quoteWriterLabel.setForeground(new Color(0x008080));
        quoteWriterLabel.setBounds(500, 140, 200, 80);

        eventPanel.add(quoteLabel); eventPanel.add(quoteWriterLabel);
    }

    protected void setAddTaskPanel()
    {
        addTaskPanel=new JPanel();
        addTaskPanel.setLayout(null);
        addTaskPanel.setBackground(Color.LIGHT_GRAY);
        addTaskPanel.setBounds(250, 220, 451, 434);

        taskNameLabel = new JLabel("Task Name"); 
        taskNameLabel.setBounds(20, 70, 80, 20);

        taskNameField = new JTextField(); 
        taskNameField.setBounds(20, 100, 180, 30);
        
        taskDateLabel = new JLabel("Task Date (YYYY-MM-DD)");
        taskDateLabel.setBounds(20, 140, 80, 20);
        
        taskDateField = new JTextField();
        taskDateField.setBounds(20, 160, 180, 30);

        selectTagLabel = new JLabel("Select Tag");
        selectTagLabel.setBounds(20, 200, 180, 20);

        radioGroup = new ButtonGroup();

        impRadio = new JRadioButton("Important"); 
        impRadio.setBounds(20, 230, 100, 20);
        impRadio.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        impRadio.setForeground(Color.BLACK);
        impRadio.setBackground(Color.LIGHT_GRAY);

        urgRadio = new JRadioButton("Urgent"); 
        urgRadio.setBounds(20, 260, 100, 20);
        urgRadio.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        urgRadio.setForeground(Color.BLACK);
        urgRadio.setBackground(Color.LIGHT_GRAY);

        norRadio = new JRadioButton("Normal");
        norRadio.setBounds(20, 290, 100, 20);
        norRadio.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        norRadio.setForeground(Color.BLACK);
        norRadio.setBackground(Color.LIGHT_GRAY);

        radioGroup.add(impRadio); radioGroup.add(urgRadio); radioGroup.add(norRadio);

        addTaskButton = new JButton("Add Task"); 
        addTaskButton.setBounds(250,260,90,22);
        addTaskButton.addActionListener(ea -> addTaskAction());
//        Add_task.addActionListener(this);
        
        clearButton = new JButton("CLEAR");
        clearButton.setBounds(250,300,210,22);
        clearButton.addActionListener(ea -> clearAction());

        Style.labelField(taskNameLabel, taskDateLabel, selectTagLabel, taskNameField, taskDateField);
        Style.buttons(addTaskButton, clearButton);

        addTaskPanel.add(taskNameLabel); addTaskPanel.add(taskNameField);
        addTaskPanel.add(taskDateLabel); addTaskPanel.add(taskDateField);
        addTaskPanel.add(selectTagLabel); 
        addTaskPanel.add(impRadio); addTaskPanel.add(urgRadio); addTaskPanel.add(norRadio);
        addTaskPanel.add(addTaskButton); addTaskPanel.add(clearButton); 

        eventPanel.add(addTaskPanel);
        eventPanel.repaint();
        eventPanel.revalidate();
 
        repaint();  revalidate();
    }

    public void show_coming_events()
    {

        if(priority.minheap.isEmpty())
            return;

        else
        {
            upComingEventLabel.setVisible(false);
            Event on_top=priority.top_event();
        
            if(!on_top.isCompleted)
            {
            
                String open=on_top.openingDate.toString();
                String close=on_top.closingDate.toString();
                String name=on_top.eventTitle;

                
                JLabel task_name_heading, task_detail_heading, task_date_heading, task_tag_heading;
                JLabel taskname, taskdetail, taskdate, tasktag;
                String tname, tdetail, tdate, tTag;
                Task on_top_of_event=on_top.task_priority();

                heading=new JLabel("Coming Events" );
                heading.setBounds(30, 20, 200, 30);

                event_title=new JLabel("Title");
                event_title.setBounds(10, 60, 80, 20);

                if(title==null)
                {
                    title=new JLabel(name);
                    title.setBounds(200, 60, 200, 20);
                }
                
                title.setText(name); // Update text without re-adding the component
                title.setBounds(200, 60, 200, 20);

                event_Odate=new JLabel("Opening date");
                event_Odate.setBounds(10, 90, 100, 20);

                if(Odate==null)
                {
                    Odate=new JLabel(open);
                    Odate.setBounds(200, 90, 80, 20);
                }

                Odate.setText(open); // Update text without re-adding the component
                Odate.setBounds(200, 90, 80, 20);

                event_cdate=new JLabel("Closing date");
                event_cdate.setBounds(10, 120, 100, 20);

                if(CDate==null)
                {
                    CDate=new JLabel(close);
                    CDate.setBounds(200, 120, 100, 20);
                }

                CDate.setText(close); // Update text without re-adding the component
                CDate.setBounds(200, 120, 100, 20);

                if(on_top_of_event!=null)
                {
                
                    tname=on_top_of_event.name;
                    tdate=on_top_of_event.date.toString();
                    tTag=on_top_of_event.tag;

                    headingtask=new JLabel("Related Tasks");
                    headingtask.setBounds(310, 20, 200, 30);

                    task_name_heading=new JLabel("Name");
                    task_name_heading.setBounds(300, 60, 100, 20);

                    taskname=new JLabel(tname);
                    taskname.setBounds(350, 60, 100, 20);

                    task_date_heading=new JLabel("Date");
                    task_date_heading.setBounds(300, 90, 80, 20);

                    taskdate=new JLabel(tdate);
                    taskdate.setBounds(350, 90, 80, 20);
                    
                    task_tag_heading=new JLabel("Tag");
                    task_tag_heading.setBounds(300, 150, 80, 20);

                    tasktag=new JLabel(tTag);
                    tasktag.setBounds(350, 150, 100, 20);

                    Style.labelField(task_name_heading, task_date_heading, 
                    task_tag_heading, taskname, taskdate, tasktag);

                    pandaPanel.add(task_name_heading);
                    pandaPanel.add(taskname);
                    pandaPanel.add(task_date_heading);
                    pandaPanel.add(taskdate);
                    pandaPanel.add(task_tag_heading);
                    pandaPanel.add(tasktag);
                    pandaPanel.add(headingtask);
                }

                pandaPanel.add(heading);
                pandaPanel.add(event_title);
                pandaPanel.add(title); 
                pandaPanel.add(event_Odate);
                pandaPanel.add(Odate);
                pandaPanel.add(event_cdate);
                pandaPanel.add(CDate);
            }
            
            else
            {
                upComingEventLabel.setVisible(true);
            }
            
        }
        
        //styleLabelField(event_title, event_Odate, event_cdate, title, Odate, CDate, heading, headingtask);
    }

//Action Listeners

    protected void addEventAction()
    {
        String openingDate=openingDateField.getText().trim();
        String closingDate=closingDateField.getText().trim();
        String name=eventNameField.getText();

        if (openingDate.isEmpty() || closingDate.isEmpty() || name.isEmpty()) 
            JOptionPane.showMessageDialog(this, "Please fill in all the fields!", "Error", JOptionPane.ERROR_MESSAGE);
        
            
        else
        {
            openingDateField.setText("");
            closingDateField.setText("");
            eventNameField.setText("");
            
            try 
            {
                if (!openingDate.matches("\\d{4}-\\d{2}-\\d{2}") || !closingDate.matches("\\d{4}-\\d{2}-\\d{2}")) 
                   throw new DateTimeParseException("Invalid format", openingDate, 0);

                LocalDate opening_Date = LocalDate.parse(openingDate);
                LocalDate closing_Date = LocalDate.parse(closingDate);

                if (closing_Date.isBefore(opening_Date)) 
                    JOptionPane.showMessageDialog(this, "Closing date cannot be before opening date!", "Error", JOptionPane.ERROR_MESSAGE);
                 
                else 
                {
                    JOptionPane.showMessageDialog(this, "Event added successfully! Opening Date: " + openingDate + ", Closing Date: " + closingDate, "Success", JOptionPane.INFORMATION_MESSAGE);
                    Event event=new Event(name, opening_Date, closing_Date);
                    pandaPanel.setVisible(false);
                    priority.add_event_in_minheap(event);
                    openEventPanel(event);
                    setCurrentEvent(event);
                    DashBoard.addEvent(event);
                }
            } 
            
            catch (DateTimeParseException ex) 
            {
                JOptionPane.showMessageDialog(this, "Invalid date format! Please use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    protected void openAddTaskFieldsAction () {setAddTaskPanel();}

    protected void addOtherEventAction()
    {
        eventPanel.setVisible(false);
        show_coming_events();
        pandaPanel.setVisible(true);
    }

    protected void addTaskAction()
    {
        String name, tag;
        LocalDate Taskdate;

        name=taskNameField.getText();

        if(impRadio.isSelected())
        tag="Important";
        else if(urgRadio.isSelected())
        tag="Urgent";
        else if(norRadio.isSelected())
        tag="Normal";
        else
        tag=null;
        
        if (name.isEmpty() ||  tag==null) {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else
        {
            String dateText=taskDateField.getText(); 
                
            try {
                Taskdate = LocalDate.parse(dateText);
            } 
            catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid date format! Use yyyy-mm-dd.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } 

            if (Taskdate.isBefore(currentEvent.openingDate) || Taskdate.isAfter(currentEvent.closingDate)) 
            {
                JOptionPane.showMessageDialog(
                null, 
                "Error: Task date must be between " + currentEvent.openingDate + " and " + currentEvent.closingDate, 
                "Invalid Date", 
                JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            currentEvent.addTask(new Task(name, Taskdate, tag));
            MainFrame.hash.hashTask(new Task(name, Taskdate, tag));
              //priority.add_event_in_minheap(current_event);
                
            JOptionPane.showMessageDialog(this, "Task added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            currentEvent.updateDoneStatus();
        }

    }

    protected void clearAction()
    {
        taskNameField.setText("");
        taskDateField.setText("");
        impRadio.setSelected(false);
        urgRadio.setSelected(false);
        norRadio.setSelected(false);
    }

    protected void backAction()
    {
        eventPanel.setVisible(false);
        show_coming_events();
        pandaPanel.setVisible(true);
    }
}