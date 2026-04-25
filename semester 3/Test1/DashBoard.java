import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.time.*;

public class DashBoard extends JPanel
{
    static JPanel displayPanel;
    JPanel tagPanel, navPanel, functionPanel;
    JButton prevButton, nextButton, openEventButton, addTaskButton, impButton, urgentButton, normalButton;
    //replace with class datePanel
    static ArrayList<DatePanel> datePanelList;
    static DoublyLinkedList<String> panelNames;
    static CardLayout card;
    DatePanel d;
    ActionListener addTask;
    
    DashBoard()
    {
        card = new CardLayout();
     
        setTagPanel();
        setDisplayPanel();
        setNavPanel();
        setFunctionPanel();

        setMainPanel();     //add other panels in the 'DashBoard extends JPanel'

        setActionListeners();

    }

    void setMainPanel()
    {
        setLayout(new BorderLayout(10, 15));

        add(tagPanel, BorderLayout.NORTH);
        add(displayPanel, BorderLayout.CENTER);
        add(functionPanel, BorderLayout.EAST);
        add(navPanel, BorderLayout.SOUTH);
    }

    void setTagPanel()
    {
        tagPanel = new JPanel(null);
        tagPanel.setPreferredSize(new Dimension(getWidth(), 100));
        tagPanel.setBackground(Color.DARK_GRAY);

        impButton = new JButton("Important");
        urgentButton = new JButton("Urgent");
        normalButton = new JButton("Normal");

        impButton.setBounds(180, 50, 120, 30);
        urgentButton.setBounds(340, 50, 120, 30);
        normalButton.setBounds(500, 50, 120, 30);

        tagPanel.add(impButton); tagPanel.add(urgentButton); tagPanel.add(normalButton);

        Style.buttons(impButton, urgentButton, normalButton);
    }

    void setFunctionPanel()
    {
        functionPanel = new JPanel(null);
        functionPanel.setPreferredSize(new Dimension(150, getHeight()));
        functionPanel.setBackground(Color.DARK_GRAY);

        addTaskButton = new JButton("Add Task");
        openEventButton = new JButton("Open Event");

        addTaskButton.setBounds(0, 60, 120, 30);
        openEventButton.setBounds(0, 190, 120, 30);

        functionPanel.add(addTaskButton); functionPanel.add(openEventButton);
        Style.buttons(addTaskButton, openEventButton);
    }

    void setDisplayPanel()
    {
        displayPanel = new JPanel(card);
        displayPanel.setBackground(Color.LIGHT_GRAY);

        panelNames = new DoublyLinkedList<>();

        datePanelList = new ArrayList<>();
    }

    void setNavPanel()
    {
        navPanel = new JPanel(null);
        navPanel.setPreferredSize(new Dimension(getWidth(), 100));
        navPanel.setBackground(Color.LIGHT_GRAY);

        prevButton = new JButton("Previous");
        nextButton = new JButton("Next");

        prevButton.setBounds(50, 10, 120, 30);
        nextButton.setBounds(550, 10, 120, 30);

        navPanel.add(prevButton); navPanel.add(nextButton);
        Style.buttons(prevButton, nextButton);
    }

    void setActionListeners()
    {
        prevButton.addActionListener(e -> card.show(displayPanel, panelNames.prev()));
        nextButton.addActionListener(e -> card.show(displayPanel, panelNames.next()));
        addTaskButton.addActionListener(e -> addTask(showDialogBox()));
    }

    protected void addTask(Task t)
    {
        if (t==null)
            return;

        String name = t.name, date = t.date.toString(), tag = t.tag;

        if (!panelNames.contains(date)) //condition when date doesn't exisit in prev records
        {
            DatePanel d = new DatePanel(LocalDate.parse(date));
            datePanelList.add(d);
            //panelNames.addToBack(date);  //replace with sorted addition.
            panelNames.insertSorted(date);
            //panelNames.printAll();
            //System.out.println();
            displayPanel.add(d, date);
            card.show(displayPanel, date);
            MainFrame.hash.hashTask(t);
            d.addTaskPanel(t);
            displayPanel.repaint();
        }

        else
        {
            int i =0;

            for (; i<panelNames.size; i++)
                if (panelNames.get(i).equals(date))
                    break;

            MainFrame.hash.hashTask(t);
            datePanelList.get(i).addTaskPanel(t);
            displayPanel.repaint();
        }
    }

    public Task showDialogBox()
    {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        JTextField taskName = new JTextField(15);
        JTextField taskDate = new JTextField(15);
        JRadioButton impRadio = new JRadioButton("Important");
        JRadioButton urgRadio = new JRadioButton("Urgent");
        JRadioButton norRadio = new JRadioButton("Normal");

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(impRadio);
        radioGroup.add(urgRadio);
        radioGroup.add(norRadio);


        inputPanel.add(new JLabel("Task Name: "));
        inputPanel.add(taskName);
        inputPanel.add(new JLabel("Date (YYYY-MM-DD): "));
        inputPanel.add(taskDate);
        inputPanel.add(new JLabel("Priority Tag: "));
        inputPanel.add(impRadio); inputPanel.add(urgRadio); inputPanel.add(norRadio);

        //#1
        JOptionPane.showMessageDialog(displayPanel, inputPanel, "Enter Task Details", JOptionPane.PLAIN_MESSAGE);

        String taskTag = (impRadio.isSelected()) ? impRadio.getText() : (urgRadio.isSelected()) ? urgRadio.getText() : (norRadio.isSelected()) ? norRadio.getText() : "";

        //condition when any of the field is not filled
        if (taskName.getText().isEmpty() || taskDate.getText().isEmpty() || radioGroup.getSelection()==null)
        {
            JOptionPane.showMessageDialog(displayPanel, "Kindly fill all required fields", "Unsufficient Details", JOptionPane.PLAIN_MESSAGE);
            return null;
        } 

        if (!taskDate.getText().matches("\\d{4}-\\d{2}-\\d{2}"))
        {
            JOptionPane.showMessageDialog(displayPanel, "Wrtie date in YYYY-MM-DD Format", "Invalid Date Formate", -1);
            JOptionPane.showMessageDialog(displayPanel, inputPanel, "Enter Task Details", JOptionPane.PLAIN_MESSAGE);
        }


        Task newTask = new Task(taskName.getText(), LocalDate.parse(taskDate.getText()), taskTag);

        return newTask;
    }
    

    //Interface Method for OpenEvent, so that event can be added to dashboard from OpenEvent Class 458-LOC
    public static void addEvent(Event e)
    {
        if (e==null)
            return;

        String name = e.eventTitle, date = e.openingDate.toString(), tag = null;

        if (!panelNames.contains(date)) //condition when date doesn't exisit in prev records
        {
            DatePanel d = new DatePanel(LocalDate.parse(date));
            datePanelList.add(d);
            //panelNames.addToBack(date);  //replace with sorted addition.
            panelNames.insertSorted(date);
            //panelNames.printAll();
            //System.out.println();
            displayPanel.add(d, date);
            card.show(displayPanel, date);
            d.addEventPanel(e, name);
            displayPanel.repaint();
        }

        else
        {
            int i =0;

            for (; i<panelNames.size; i++)
                if (panelNames.get(i).equals(date))
                    break;

            datePanelList.get(i).addEventPanel(e, e.eventTitle);
            displayPanel.repaint();     
        }
    }
    
}

   