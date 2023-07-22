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
    World world = new World();

    public WorldPanel(){
        setLayout(new BorderLayout());
        JPanel gameView = new JPanel();
        gameView.setPreferredSize(new Dimension(500, getHeight()));
        gameView.setLayout(new GridLayout(5, 1));
        gameView.setBackground(Color.LIGHT_GRAY);
        add(gameView, BorderLayout.WEST);

        JButton run = new JButton("Run");
        run.addActionListener(this);
        gameView.add(run);

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
