import javax.swing.*;
import java.awt.*;

public class Frame 
{
    JFrame frame;
    Container container;

    Frame()
    {
        frame = new JFrame();

        setContainer();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setVisible(true);
    }

    private void setContainer()
    {
        container = frame.getContentPane();

        CardLayout card = new CardLayout();
        container.setLayout(card);
    }
}
