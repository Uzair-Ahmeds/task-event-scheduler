import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MainFrame 
{
    JFrame frame;
    Container container;
    CardLayout card;
    DashBoard dashBoard;
    OpenEvent openEvent;
    
    static HashMap hash;

    MainFrame()
    {
        frame = new JFrame();

        card = new CardLayout();
        dashBoard = new DashBoard();
        openEvent = new OpenEvent();
        hash = new HashMap();

        setContainer();
        container.add(dashBoard, "DashBoard");
        container.add(openEvent, "OpenEvent");

        card.show(container, "DashBoard");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(960,660);
        frame.setVisible(true);

        dashBoard.openEventButton.addActionListener(e -> card.show(container, "OpenEvent"));
        openEvent.homeButton.addActionListener(e -> card.show(container, "DashBoard"));

        dashBoard.impButton.addActionListener(e -> searchAction(hash.getImp()));
        dashBoard.urgentButton.addActionListener(e -> searchAction(hash.getUrg()));
        dashBoard.normalButton.addActionListener(e -> searchAction(hash.getNor()));
    }

    void setContainer()
    {
        container = frame.getContentPane();
        container.setLayout(card);
    }

    protected void searchAction(ArrayList<Task> tagList)
    {
        ArrayList<JPanel> taskPanels = new ArrayList<>();

        JFrame impFrame = new JFrame();
        impFrame.setLayout(null);
        int y = 10;

        for (Task t:tagList)
            taskPanels.add(new TaskPanel(t));

        for (int i=0; i<taskPanels.size(); i++)
        {
            taskPanels.get(i).setBounds(0, y, 400, 40);
            impFrame.add(taskPanels.get(i));
            y += 50;
        }

        impFrame.setSize(450, 600);
        impFrame.setVisible(true);
        impFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

}

class TaskPanel extends JPanel
{
    JLabel nameLabel;
    JLabel tagLabel;
    JLabel dateLabel;
 
    TaskPanel(Task t)
    {
        nameLabel = new JLabel(t.name);
        nameLabel.setBounds(10, 0, 150, 30);
        add(nameLabel);

        tagLabel = new JLabel(t.tag);
        tagLabel.setBounds(190, 0, 120, 30);
        add(tagLabel);

        dateLabel = new JLabel(t.date.toString());
        dateLabel.setBounds(300, 0, 120, 30);
        add(dateLabel);

        setLayout(null);

        Style.labelField(nameLabel, tagLabel, dateLabel);
    }
    
}

