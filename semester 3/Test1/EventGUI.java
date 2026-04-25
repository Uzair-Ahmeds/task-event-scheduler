import java.awt.*;
import javax.swing.*;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EventGUI implements ActionListener {

    //Object of set priority class
    Priority priority=new Priority();

    //current event (To ensure that dependent task are added sahi s)
    Event current_event;

    //GUI
    JFrame main_frame;
    JPanel main_panel;
    JPanel panel_for_clock;
    JPanel panel_for_funcationalites_of_addevent;
    JPanel panda_panel;
    //JPanel view_all_events;
    JLabel panda;
    JLabel coming_events;
    JLabel clock_pic;
    JLabel add_event;
    JLabel opening_date;
    JLabel eventopening_date;
    JLabel closing_date_label;
    JLabel top_closing_date;
    JSeparator marginline_for_closingdate;
    JSeparator marginline_for_openingdate;
    JButton add_event_button;
    JButton add_task_button;
    JLabel quote_label;
    JLabel quotewriter_label;
    JButton hamburger_menu;
    JPanel add_task_panel;
    JButton add_event_button_pandapanel;
    JTextField eventname, eventopeningdate, eventclosingdate;
    JButton view_all;
    //Icons on left
    JButton Home, search, logout;

    //add_task method' vairbales
    JButton Add_task, add_another_task;
    JTextField task_name, task_detail, task_date;
    JRadioButton Important, Urgent, Normal;
    ButtonGroup tagGroup = new ButtonGroup();

    //show coming events method variables
    JLabel event_title, event_Odate, event_cdate, title, Odate, CDate, heading, headingtask;

    //back button for panel for functionalities
    JButton back;


    EventGUI()
    {
        start();
    }

    void start(){
        JLabel add_heading;
        JLabel exciting_heading;
        JLabel events_heading;
        JLabel excited_panda;
        JSeparator margin_between_pandas; 

        JLabel event_name_label, openingdate_label, closingdatelabel, tag_label;
        JSeparator margin_name, margin_openingdate, margin_closingdate;
        JRadioButton tag1, tag2 , tag3;
        ButtonGroup taggroup = new ButtonGroup();

        main_frame=new JFrame("Event class");
        main_panel=new JPanel();
        main_panel.setLayout(null);
        main_panel.setBackground(Color.LIGHT_GRAY);
        main_panel.setPreferredSize(new java.awt.Dimension(701,201));
       
        //clockPanel();
        panel_for_clock=new JPanel();
        panel_for_clock.setLayout(null);
        panel_for_clock.setBounds(0, 0, 250, 654);
        panel_for_clock.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel_for_clock.setBackground(new Color(45, 45, 45));  
        panel_for_clock.setOpaque(true);

        Font monospacedFont = new Font("Monospaced", Font.PLAIN, 40); 
        clock_pic=new JLabel();
        clock_pic.setFont(monospacedFont); 
        clock_pic.setForeground(Color.WHITE); 
        clock_pic.setOpaque(true);
        clock_pic.setBackground(new Color(0, 0, 0)); 
        clock_pic.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        clock_pic.setBounds(0, 0, 250, 80);

        Timer timer = new Timer(1000, e -> {
            String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
            clock_pic.setText(time);  
        });
        timer.start();

        Home=new JButton("HOME");
        Home.setIcon(new ImageIcon(getClass().getResource("Home.png"))); 
        Home.setBounds(10, 150, 200,70);
        Home.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Home.setBackground(Color.BLACK);
        Home.setForeground(Color.white);
        Home.addActionListener(this);

        search=new JButton("Search");
        search.setIcon(new ImageIcon(getClass().getResource("search-icon.png"))); 
        search.setBounds(10, 240, 200,70);
        search.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        search.setBackground(Color.BLACK);
        search.setForeground(Color.white);
        Home.addActionListener(this);

        logout=new JButton("Logout");
        logout.setIcon(new ImageIcon(getClass().getResource("signout.png"))); 
        logout.setBounds(10, 320, 200,70);
        logout.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        logout.setBackground(Color.BLACK);
        logout.setForeground(Color.white);
        logout.addActionListener(this);

        view_all=new JButton("View All");
        view_all.setIcon(new ImageIcon(getClass().getResource("view.png"))); 
        view_all.setBounds(10, 400, 200,70);
        view_all.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        view_all.setBackground(Color.BLACK);
        view_all.setForeground(Color.white);
        view_all.addActionListener(this);        

        panel_for_clock.add(clock_pic);
        panel_for_clock.add(Home);
        panel_for_clock.add(search);
        panel_for_clock.add(logout);
        panel_for_clock.add(view_all);
        //end clockPanel();

        //pandaPanel();
        panda_panel=new JPanel();
        panda_panel.setBackground(Color.LIGHT_GRAY);
        panda_panel.setLayout(null);
        panda_panel.setBounds(250,0,800,1000);

        coming_events=new JLabel("No UpComing Events");
        coming_events.setFont(new Font("Segoe UI", Font.PLAIN, 40)); 
        coming_events.setForeground(Color.BLACK);
        coming_events.setBounds(40, 70, 400, 55);

        //eliminated.
        hamburger_menu=new JButton();
        hamburger_menu.setBackground(Color.LIGHT_GRAY);
        hamburger_menu.setIcon(new ImageIcon(getClass().getResource("/Menu.png"))); 
        hamburger_menu.setBorder(null);
        hamburger_menu.setBounds(620, 10, 60,41);

        margin_between_pandas=new JSeparator();
        margin_between_pandas.setBounds(30, 205, 550,10);
        margin_between_pandas.setForeground(Color.BLACK);

        //panda image
        excited_panda=new JLabel();
        //LOC for adding actual image in frame.
        excited_panda.setIcon(new ImageIcon(getClass().getResource("panda is excited.png"))); 
        excited_panda.setBounds(20, 190, 360,360);

        add_heading=new JLabel("Add ");
        add_heading.setBackground(new Color(102, 51, 0));
        add_heading.setFont(new Font("Segoe UI", Font.PLAIN, 36)); 
        add_heading.setForeground(Color.BLACK);
        add_heading.setBounds(360,200, 100,100);

        //noFound.....
        exciting_heading=new JLabel("Exciting");
        exciting_heading.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        exciting_heading.setForeground(Color.BLACK);
        exciting_heading.setBounds(400, 240, 200, 120);

        events_heading=new JLabel("Events");
        events_heading.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 36)); 
        events_heading.setForeground(Color.BLACK);
        events_heading.setBounds(400, 280,200,120);

        event_name_label=new JLabel("Name");
        event_name_label.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        event_name_label.setForeground(Color.BLACK);
        event_name_label.setBounds(300, 325, 120, 120);

        eventname=new JTextField();
        eventname.setBackground(Color.LIGHT_GRAY);
        eventname.setFont(new Font("Segoe UI", Font.PLAIN, 12)); 
        eventname.setForeground(Color.BLACK);
        eventname.setBorder(null);
        eventname.setBounds(410, 365, 180, 20);
 
        margin_name=new JSeparator();
        margin_name.setBounds(410, 390, 180, 10);
        margin_name.setForeground(Color.BLACK);

        openingdate_label=new JLabel("Opening date ");
        openingdate_label.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        openingdate_label.setForeground(Color.BLACK);
        openingdate_label.setBounds(300, 360, 120, 120);

        eventopeningdate=new JTextField();
        eventopeningdate.setBackground(Color.LIGHT_GRAY);
        eventopeningdate.setFont(new Font("Segoe UI", Font.PLAIN, 12)); 
        eventopeningdate.setForeground( Color.BLACK);
        eventopeningdate.setBorder(null);
        eventopeningdate.setBounds(410, 400, 180, 20);

        margin_openingdate=new JSeparator();
        margin_openingdate.setBounds(410, 420, 180, 10);
        margin_openingdate.setForeground(Color.BLACK);
 
        closingdatelabel=new JLabel("Closing date");
        closingdatelabel.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        closingdatelabel.setForeground( Color.BLACK);
        closingdatelabel.setBounds(300, 390, 120, 120);

        eventclosingdate=new JTextField();
        eventclosingdate.setBackground( Color.LIGHT_GRAY);
        eventclosingdate.setFont(new Font("Segoe UI", Font.PLAIN, 12)); 
        eventclosingdate.setForeground( Color.BLACK);
        eventclosingdate.setBorder(null);
        eventclosingdate.setBounds(410, 430, 180, 20);

        margin_closingdate=new JSeparator();
        margin_closingdate.setBounds(410, 450, 180, 10);
        margin_closingdate.setForeground(Color.BLACK);

        tag_label=new JLabel("Select tag:");
        tag_label.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        tag_label.setForeground(Color.BLACK);
        tag_label.setBounds(300, 420, 120, 120);

        tag1=new JRadioButton("Important");
        tag1.setBounds(300, 500, 100, 20);
        tag1.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        tag1.setForeground(Color.BLACK);
        tag1.setBackground(Color.LIGHT_GRAY);
        tag1.addActionListener(this);
    
        tag2=new JRadioButton("Urgent");
        tag2.setBounds(300, 520, 100, 20);
        tag2.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        tag2.setForeground(Color.BLACK);
        tag2.setBackground(Color.LIGHT_GRAY);
        tag2.addActionListener(this);
    
        tag3=new JRadioButton("Normal");
        tag3.setBounds(300, 540, 100, 20);
        tag3.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        tag3.setForeground(Color.BLACK);
        tag3.setBackground(Color.LIGHT_GRAY);
        tag3.addActionListener(this);

        taggroup.add(tag1);
        taggroup.add(tag2);
        taggroup.add(tag3);

        add_event_button_pandapanel=new JButton("ADD");
        add_event_button_pandapanel.setBackground(Color.BLACK);
        add_event_button_pandapanel.setFont(new Font("SimSun", 1, 18)); 
        add_event_button_pandapanel.setForeground(Color.WHITE);
        add_event_button_pandapanel.setBounds(450, 530, 90,30);
        add_event_button_pandapanel.addActionListener(this);
        

        panda_panel.add(coming_events);
        panda_panel.add(hamburger_menu);
        panda_panel.add(margin_between_pandas);
        panda_panel.add(excited_panda);
        panda_panel.add(add_heading);
        panda_panel.add(exciting_heading);
        panda_panel.add(events_heading);
        panda_panel.add(event_name_label);
        panda_panel.add(eventname);
        panda_panel.add(margin_name);
        panda_panel.add(openingdate_label);
        panda_panel.add(eventopeningdate);
        panda_panel.add(margin_openingdate);
        panda_panel.add(closingdatelabel);
        panda_panel.add(eventclosingdate);
        panda_panel.add(margin_closingdate);
        panda_panel.add(tag_label);
        panda_panel.add(tag_label);
        panda_panel.add(tag1);
        panda_panel.add(tag2);
        panda_panel.add(tag3);
        panda_panel.add(add_event_button_pandapanel);


        main_panel.add(panel_for_clock);
        main_panel.add(panda_panel);
        main_frame.add(main_panel);
        main_frame.setSize(958,654);
        main_frame.setVisible(true);
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    //GUI of  EVENT PANEL Initialized by ADD button at panda panel, 
    void addevent(Event e){

        //event panel
        panel_for_funcationalites_of_addevent=new JPanel();
        panel_for_funcationalites_of_addevent.setLayout(null);
        panel_for_funcationalites_of_addevent.setBackground(Color.LIGHT_GRAY);
        panel_for_funcationalites_of_addevent.setBounds(0, 0, 1000,1000);
        
        back=new JButton("Back");
        back.setBackground( Color.BLACK);
        back.setFont(new Font("SimSun", 0, 14)); 
        back.setForeground(Color.WHITE);
        back.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        back.setBounds(800,560,100,20);
        back.addActionListener(this);

        String open, close;
        open=e.openingDate.toString();
        close=e.closingDate.toString();

        add_event=new JLabel("What’s Next? ");
        add_event.setFont(new Font("Segoe UI", Font.PLAIN, 18)); 
        add_event.setForeground(Color.BLACK);
        add_event.setBounds(380, 0, 180, 40);

        opening_date=new JLabel("Event opening date");
        opening_date.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        opening_date.setForeground(Color.BLACK);
        opening_date.setBounds(270, 25, 180, 40);

        eventopening_date=new JLabel();
        eventopening_date.setFont(new Font("Segoe UI", Font.PLAIN, 12)); 
        eventopening_date.setForeground(Color.BLACK);
        eventopening_date.setBounds(270, 55, 180, 30);
        eventopening_date.setText(open);

        closing_date_label=new JLabel("Event closing date");
        closing_date_label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        closing_date_label.setForeground(Color.BLACK);
        closing_date_label.setBounds(450, 25, 180, 40);

        top_closing_date=new JLabel();
        top_closing_date.setFont(new Font("Segoe UI", Font.PLAIN, 12)); 
        top_closing_date.setForeground(Color.BLACK);
        top_closing_date.setBounds(450, 55, 180, 30);
        top_closing_date.setText(close);

        marginline_for_openingdate=new JSeparator();
        marginline_for_openingdate.setBounds(270, 90, 135, 20 );
        marginline_for_openingdate.setForeground(Color.BLACK);
        marginline_for_closingdate=new JSeparator();
        marginline_for_closingdate.setBounds(450, 90, 135, 20 );
        marginline_for_closingdate.setForeground(Color.BLACK);

        add_event_button=new JButton("Add another Event");
        add_event_button.setBackground( Color.BLACK);
        add_event_button.setFont(new Font("SimSun", 0, 14)); 
        add_event_button.setForeground(Color.WHITE);
        add_event_button.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add_event_button.setBounds(630,35,140,22);
        add_event_button.addActionListener(this);

        add_task_button=new JButton("Add Task");
        add_task_button.setBackground(Color.BLACK);
        add_task_button.setFont(new Font("SimSun", 0, 14)); 
        add_task_button.setForeground( Color.WHITE);
        add_task_button.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add_task_button.setBounds(630,75,140,22);
        add_task_button.addActionListener(this);

        quote_label=new JLabel("“The best way to predict the future is to create it.” ");
        quote_label.setFont(new Font("Blackadder ITC", 0, 28)); 
        quote_label.setForeground(new Color(0x008080)); // Teal color
        quote_label.setBounds(260, 100, 600, 80);

        quotewriter_label=new JLabel("– Peter Drucker");
        quotewriter_label.setFont(new Font("Viner Hand ITC", 2, 18)); 
        quotewriter_label.setForeground(new Color(0x008080));
        quotewriter_label.setBounds(500, 140, 200, 80);

        hamburger_menu=new JButton();
        hamburger_menu.setBackground( Color.LIGHT_GRAY);
        hamburger_menu.setIcon(new ImageIcon(getClass().getResource("Menu.png"))); 
        hamburger_menu.setBorder(null);
        hamburger_menu.setBounds(870, 30, 41,28);

        panel_for_funcationalites_of_addevent.add(add_event);
        panel_for_funcationalites_of_addevent.add(opening_date);
        panel_for_funcationalites_of_addevent.add(eventopening_date);
        panel_for_funcationalites_of_addevent.add(closing_date_label);
        panel_for_funcationalites_of_addevent.add(top_closing_date);
        panel_for_funcationalites_of_addevent.add(marginline_for_openingdate);
        panel_for_funcationalites_of_addevent.add(marginline_for_closingdate);
        panel_for_funcationalites_of_addevent.add(add_event_button);
        panel_for_funcationalites_of_addevent.add(add_task_button);
        panel_for_funcationalites_of_addevent.add(quote_label);
        panel_for_funcationalites_of_addevent.add(quotewriter_label);
        panel_for_funcationalites_of_addevent.add(hamburger_menu);
        panel_for_funcationalites_of_addevent.add(back);

        main_panel.add(panel_for_funcationalites_of_addevent);
        main_panel.revalidate();
        main_panel.repaint();

    }

    void setCurrentEvent(Event event) {
        this.current_event=event;
    }

    void addtasks(){
        
        add_task_panel=new JPanel();
        add_task_panel.setLayout(null);
        add_task_panel.setBackground(Color.LIGHT_GRAY);
        add_task_panel.setBounds(250, 220, 451, 434);

        JLabel name_label, detail_label, date_label, tag_label;
        JSeparator margin_name, margin_detail, margin_date;

        name_label=new JLabel("Task name");
        name_label.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        name_label.setForeground(Color.BLACK);
        name_label.setBounds(20, 10, 80, 20);

        task_name=new JTextField();
        task_name.setBackground(Color.LIGHT_GRAY);
        task_name.setFont(new Font("Segoe UI", Font.PLAIN, 12)); 
        task_name.setForeground(Color.BLACK);
        task_name.setBorder(null);
        task_name.setBounds(20, 30, 180, 30);

        margin_name=new JSeparator();
        margin_name.setBounds(20, 60, 180, 10);
        margin_name.setForeground(Color.BLACK);

        detail_label=new JLabel("Detail ");
        detail_label.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        detail_label.setForeground(Color.BLACK);
        detail_label.setBounds(20, 80, 80, 20);

        task_detail=new JTextField();
        task_detail.setBackground(Color.LIGHT_GRAY);
        task_detail.setFont(new Font("Segoe UI", Font.PLAIN, 12)); 
        task_detail.setForeground(Color.BLACK);
        task_detail.setBorder(null);
        task_detail.setBounds(20, 100, 180, 30);

        margin_detail=new JSeparator();
        margin_detail.setBounds(20,130, 180, 10);
        margin_detail.setForeground(Color.BLACK);

        date_label=new JLabel("Date ");
        date_label.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        date_label.setForeground(Color.BLACK);
        date_label.setBounds(20, 140, 80, 20);

        task_date=new JTextField();
        task_date.setBackground(Color.LIGHT_GRAY);
        task_date.setFont(new Font("Segoe UI", Font.PLAIN, 12)); 
        task_date.setForeground(Color.BLACK);
        task_date.setBorder(null);
        task_date.setBounds(20, 160, 180, 30);

        margin_date=new JSeparator();
        margin_date.setBounds(20, 190, 180, 10);
        margin_date.setForeground(Color.BLACK);

        tag_label=new JLabel("Select Tag ");
        tag_label.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        tag_label.setForeground(Color.BLACK);
        tag_label.setBounds(20, 200, 180, 20);

        Important=new JRadioButton("Important");
        Important.setBounds(20, 230, 100, 20);
        Important.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        Important.setForeground(Color.BLACK);
        Important.setBackground(Color.LIGHT_GRAY);
    
        Urgent=new JRadioButton("Urgent");
        Urgent.setBounds(20, 260, 100, 20);
        Urgent.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        Urgent.setForeground(Color.BLACK);
        Urgent.setBackground(Color.LIGHT_GRAY);
    
        Normal=new JRadioButton("Normal");
        Normal.setBounds(20, 290, 100, 20);
        Normal.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
        Normal.setForeground(Color.BLACK);
        Normal.setBackground(Color.LIGHT_GRAY);

        tagGroup.add(Important);
        tagGroup.add(Urgent);
        tagGroup.add(Normal);

        Add_task=new JButton("Add");
        Add_task.setBackground(Color.BLACK);
        Add_task.setFont(new Font("SimSun", 0, 14)); 
        Add_task.setForeground( Color.WHITE);
        Add_task.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Add_task.setBounds(250,260,90,22);
        Add_task.addActionListener(this);

        add_another_task=new JButton("Add another task");
        add_another_task.setBackground( Color.BLACK);
        add_another_task.setFont(new Font("SimSun", 0, 14)); 
        add_another_task.setForeground(Color.WHITE);
        add_another_task.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add_another_task.setBounds(250,300,210,22);
        add_another_task.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                task_name.setText("");
                task_detail.setText("");
                task_date.setText("");
                Important.setSelected(false);
                Urgent.setSelected(false);
                Normal.setSelected(false);

            }
        });
        

        add_task_panel.add(name_label);
        add_task_panel.add(task_name);
        add_task_panel.add(detail_label);
        add_task_panel.add(task_detail);
        add_task_panel.add(date_label);
        add_task_panel.add(task_date);
        add_task_panel.add(margin_name);
        add_task_panel.add(margin_detail);
        add_task_panel.add(margin_date);
        add_task_panel.add(tag_label);
        add_task_panel.add(Important);
        add_task_panel.add(Urgent);
        add_task_panel.add(Normal);
        add_task_panel.add(Add_task);
        add_task_panel.add(add_another_task);

        panel_for_funcationalites_of_addevent.add(add_task_panel);
        panel_for_funcationalites_of_addevent.revalidate();
        panel_for_funcationalites_of_addevent.repaint();

        main_panel.revalidate();
        main_panel.repaint();
    }

    //After adding EVENT GUI change in panda panel
    public void show_coming_events()
    {

        if(priority.minheap.isEmpty())
            return;

        else
        {
            coming_events.setVisible(false);
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
                heading.setFont(new Font("Segoe UI", Font.PLAIN, 20)); 
                heading.setForeground(Color.BLACK);
                heading.setBounds(30, 20, 200, 30);

                event_title=new JLabel("Title");
                event_title.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
                event_title.setForeground(Color.BLACK);
                event_title.setBounds(10, 60, 80, 20);

                if(title==null)
                {
                    title=new JLabel(name);
                    title.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
                    title.setForeground(Color.BLACK);
                    title.setBounds(200, 60, 200, 20);
                }
                
                title.setText(name); // Update text without re-adding the component
                title.setBounds(200, 60, 200, 20);

                event_Odate=new JLabel("Opening date");
                event_Odate.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
                event_Odate.setForeground(Color.BLACK);
                event_Odate.setBounds(10, 90, 100, 20);

                if(Odate==null)
                {
                    Odate=new JLabel(open);
                    Odate.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
                    Odate.setForeground(Color.BLACK);
                    Odate.setBounds(200, 90, 80, 20);
                }

                Odate.setText(open); // Update text without re-adding the component
                Odate.setBounds(200, 90, 80, 20);

                event_cdate=new JLabel("Closing date");
                event_cdate.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
                event_cdate.setForeground(Color.BLACK);
                event_cdate.setBounds(10, 120, 100, 20);

                if(CDate==null)
                {
                    CDate=new JLabel(close);
                    CDate.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
                    CDate.setForeground(Color.BLACK);
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
                    headingtask.setFont(new Font("Segoe UI", Font.PLAIN, 20)); 
                    headingtask.setForeground( Color.BLACK);
                    headingtask.setBounds(310, 20, 200, 30);

                    task_name_heading=new JLabel("Name");
                    task_name_heading.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
                    task_name_heading.setForeground(Color.BLACK);
                    task_name_heading.setBounds(300, 60, 100, 20);

                    taskname=new JLabel(tname);
                    taskname.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
                    taskname.setForeground(Color.BLACK);
                    taskname.setBounds(350, 60, 100, 20);

                    task_date_heading=new JLabel("Date");
                    task_date_heading.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
                    task_date_heading.setForeground(Color.BLACK);
                    task_date_heading.setBounds(300, 90, 80, 20);

                    taskdate=new JLabel(tdate);
                    taskdate.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
                    taskdate.setForeground( Color.BLACK);
                    taskdate.setBounds(350, 90, 80, 20);
                    
                    task_detail_heading=new JLabel("Detail");
                    task_detail_heading.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
                    task_detail_heading.setForeground(Color.BLACK);
                    task_detail_heading.setBounds(300, 120, 80, 20);


                    task_tag_heading=new JLabel("Tag");
                    task_tag_heading.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
                    task_tag_heading.setForeground(Color.BLACK);
                    task_tag_heading.setBounds(300, 150, 80, 20);

                    tasktag=new JLabel(tTag);
                    tasktag.setFont(new Font("Segoe UI", Font.PLAIN, 14)); 
                    tasktag.setForeground(Color.BLACK);
                    tasktag.setBounds(350, 150, 100, 20);

                    panda_panel.add(task_name_heading);
                    panda_panel.add(taskname);
                    panda_panel.add(task_detail_heading);
                    panda_panel.add(task_date_heading);
                    panda_panel.add(taskdate);
                    panda_panel.add(task_tag_heading);
                    panda_panel.add(tasktag);
                    panda_panel.add(headingtask);
                }

                panda_panel.add(heading);
                panda_panel.add(event_title);
                panda_panel.add(title); 
                panda_panel.add(event_Odate);
                panda_panel.add(Odate);
                panda_panel.add(event_cdate);
                panda_panel.add(CDate);
            }

            else
            {
                coming_events.setVisible(true);
            }
        }
    }

    public void actionPerformed(ActionEvent e) 
    {   
        if(e.getSource()==add_event_button_pandapanel)
        {
            String openingDate=eventopeningdate.getText().trim();
            String closingDate=eventclosingdate.getText().trim();
            String name=eventname.getText();

            if (openingDate.isEmpty() || closingDate.isEmpty() || name.isEmpty()) 
            {
                JOptionPane.showMessageDialog(main_frame, "Please fill in all the fields!", "Error", JOptionPane.ERROR_MESSAGE);
            } 
            else
            {
                eventopeningdate.setText("");
                eventclosingdate.setText("");
                eventname.setText("");
                try 
                {
                    if (!openingDate.matches("\\d{4}-\\d{2}-\\d{2}") || !closingDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                   
                        throw new DateTimeParseException("Invalid format", openingDate, 0);
                    }

                    LocalDate opening_Date = LocalDate.parse(openingDate);
                    LocalDate closing_Date = LocalDate.parse(closingDate);

                    if (closing_Date.isBefore(opening_Date)) 
                    {
                        JOptionPane.showMessageDialog(main_frame, "Closing date cannot be before opening date!", "Error", JOptionPane.ERROR_MESSAGE);
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(main_frame, "Event added successfully! Opening Date: " + openingDate + ", Closing Date: " + closingDate, "Success", JOptionPane.INFORMATION_MESSAGE);
                        Event event=new Event(name, opening_Date, closing_Date);
                        panda_panel.setVisible(false);
                        priority.add_event_in_minheap(event);
                        addevent(event);
                        setCurrentEvent(event);
                        
                    }
                } 
            
                catch (DateTimeParseException ex) 
                {
                    JOptionPane.showMessageDialog(main_frame, "Invalid date format! Please use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        else if(e.getSource()==add_task_button)
        {
            addtasks();
        }

        else if(e.getSource()==add_event_button)
        {
            panel_for_funcationalites_of_addevent.setVisible(false);
            show_coming_events();
            panda_panel.setVisible(true);
        }

        else if(e.getSource()==hamburger_menu){

        }

        else if (e.getSource()==Add_task)
        {
        
            String name, detail, tag;
            LocalDate Taskdate;

            name=task_name.getText();
            detail=task_detail.getText();

            if(Important.isSelected())
            tag="Important";
            else if(Urgent.isSelected())
            tag="Urgent";
            else if(Normal.isSelected())
            tag="Normal";
            else
            tag=null;
            
            if (name.isEmpty() || detail.isEmpty() || tag==null) {
                JOptionPane.showMessageDialog(main_frame, "Please fill in all the fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else
            {
                String dateText=task_date.getText(); 
                
                try {
                    Taskdate = LocalDate.parse(dateText);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(main_frame, "Invalid date format! Use yyyy-mm-dd.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } 

                if (Taskdate.isBefore(current_event.openingDate) || Taskdate.isAfter(current_event.closingDate)) 
                {
                    JOptionPane.showMessageDialog(
                        null, 
                        "Error: Task date must be between " + current_event.openingDate + " and " + current_event.closingDate, 
                        "Invalid Date", 
                        JOptionPane.ERROR_MESSAGE
                        
                    );
                    return;
                }

                current_event.addtask(name,  Taskdate, tag);
                //priority.add_event_in_minheap(current_event);
                
                JOptionPane.showMessageDialog(main_frame, "Task added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                current_event.updateDoneStatus();
            }
        }

        //else if(e.getSource()==view_all){
          //  Show_events_frame frame=new Show_events_frame(priority, panel_for_funcationalites_of_addevent, panda_panel);
        //}
        else if(e.getSource()==back){
            panel_for_funcationalites_of_addevent.setVisible(false);
            show_coming_events();
            panda_panel.setVisible(true);
        }
        
    }


}
