import javax.swing.*;
import java.awt.*;

public class Style 
{
    //styling methods.
    static void heading(JLabel...labels)
    {
        for (JLabel x:labels)
        {
            x.setFont(new Font("Segoe UI", Font.PLAIN, 36)); 
            x.setForeground(Color.BLACK);
    
        }
    }

    static void labelField(JComponent...labelFields)
    {
        for (JComponent x:labelFields)
        {
            x.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            x.setForeground(Color.BLACK);

            if (x instanceof JTextField)
            {
                x.setBackground(Color.LIGHT_GRAY);
                x.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
            }    
        }
    }


    static void buttons(JButton...buttons)
    {
        for (JButton x:buttons)
        {
            x.setFont(new Font("SimSun", Font.PLAIN, 14));             
            x.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            x.setBackground(Color.BLACK);
            x.setForeground(Color.white);
        }
    }
    
}
