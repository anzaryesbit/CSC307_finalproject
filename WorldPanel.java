import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * @author Aiyana Arnobit
 * @author Alec Cheng
 * @author Briana Kuo
 * @author Ke Lyu
 */

public class WorldPanel extends JPanel implements ActionListener {
    private World world;

    public WorldPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton runButton = new JButton("Run");
        runButton.addActionListener(this);
        add(runButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        add(resetButton);

        setPreferredSize(new Dimension(600, getHeight()));
        world = new World();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if ("Run".equals(button.getText())) {
                System.out.println("Run button clicked");
                run();
            }
            else if ("Reset".equals(button.getText())) {
                System.out.println("Reset button clicked");
                reset();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        world.draw(g);
    }

    public void run(){
        while(world.run()){

            repaint();
        }
        System.out.println("run finished");
        world.compare();
    }

    public void reset(){
        world = new World();
        repaint();
    }


}