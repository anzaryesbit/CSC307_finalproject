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
    private WorkAreaPanel workAreaPanel;
    private int level = 1;

    public WorldPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton runButton = new JButton("Run");
        runButton.addActionListener(this);
        add(runButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        add(resetButton);

        setPreferredSize(new Dimension(600, getHeight()));
        world = new World(level);
        workAreaPanel = new WorkAreaPanel();
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
                workAreaPanel.reset();


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
                // Load the next level
                changeLevel(++level);
                workAreaPanel.reset();
                repaint(); // Repaint the panel to show the new level
            } else {
                System.out.println("Next level loading canceled.");
            }
        }
    }

    public void reset(){
        world = new World(level);

//        repaint();
    }

    public void changeLevel(int newLevel){
        this.level = newLevel;
        reset();
    }

    public void setWorkAreaPanel(WorkAreaPanel workAreaPanel) {
        this.workAreaPanel = workAreaPanel;
    }

}