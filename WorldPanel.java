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


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorldPanel extends JPanel implements ActionListener {
    private World world;

    public WorldPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton runButton = new JButton("Run");
        runButton.addActionListener(this);
        add(runButton);

        setPreferredSize(new Dimension(600, getHeight()));
        world = new World();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if ("Run".equals(button.getText())) {
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