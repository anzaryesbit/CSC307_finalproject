import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * @author Aiyana Arnobit
 * @author Alec Cheng
 * @author Briana Kuo
 * @author Ke Lyu
 */

public class WorldPanel extends JPanel implements ActionListener{
    World world;

    public WorldPanel(){
        JButton runButton = new JButton("Run");
        runButton.addActionListener(this);

        setPreferredSize(new Dimension(600, getHeight()));
        world = new World();

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().getClass().getName().equals("javax.swing.JButton")) {
            if (((JButton) e.getSource()).getText().equals("Run")) {
                System.out.println("Run button clicked");
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        world.draw(g);
    }
}
