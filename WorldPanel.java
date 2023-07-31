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

public class WorldPanel extends JPanel implements ActionListener {
    private World world;
    private int level = 1;

    public WorldPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton runButton = new JButton("Run");
        runButton.addActionListener(this);
        add(runButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        add(resetButton);

        JButton directionButton = new JButton("Directions");
        directionButton.addActionListener(this);
        add(directionButton);

        setPreferredSize(new Dimension(600, getHeight()));
        world = new World(level);
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
//                workAreaPanel.reset();
            }
            else if ("Directions".equals(button.getText())){
                String directions = "Drag and drop the blocks to guide the spider in painting the correct squares.\n" +
                        "To select a color, click the mouse on the desired colored circle within the paint icon. \n" +
                        "To create loop blocks, drag desired block into the loop icon. These blocks can be removed by\n" +
                        " drag and droping the desired block outside the loop icon. ";
                JOptionPane.showMessageDialog(this, directions, "Directions", JOptionPane.INFORMATION_MESSAGE);
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
        if(world.compare()){
            int option = JOptionPane.showConfirmDialog(null, "Do you want to proceed to the next level?", "Next Level", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                changeLevel(++level);
                repaint();
            } else {
                System.out.println("Next level loading canceled.");
            }
        }
    }

    public void reset(){
        world = new World(level);
        System.out.println("resest");
        repaint();
    }

    public void changeLevel(int newLevel){
        this.level = newLevel;
        reset();
    }
}