import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Aiyana Arnobit
 * @author Alec Cheng
 * @author Briana Kuo
 * @author Ke Lyu
 */

public class Game extends JFrame {
    private AtomicInteger currentLevel = new AtomicInteger(1);
    private WorldPanel worldPanel;
    private WorkAreaPanel workAreaPanel;
    public Game() {
        super("Spider Game");
        setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        worldPanel = new WorldPanel();
        workAreaPanel = new WorkAreaPanel();
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Level workspace");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitleColor(Color.BLACK);
        CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(new EmptyBorder(10, 0, 0, 0), titledBorder);
        workAreaPanel.setBorder(compoundBorder);
        add(worldPanel, BorderLayout.WEST);
        add(workAreaPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createRigidArea(new Dimension(50, 0)));

        //reset level button here
        JButton resetLevel = new JButton("Reset Level");
        resetLevel.setPreferredSize(new Dimension(80, 30));
        resetLevel.addActionListener(e -> {
            worldPanel.changeLevel(currentLevel.get());
            //clear work panel as well somewhere here
            DataSource data = DataSource.getDataSource();
            data.clearProgramBlock();
            repaint();
        });
        buttonPanel.add(resetLevel);
        for (int i = 1; i <= 15; i++) {
            JButton button = new JButton("Level " + i);
            button.setPreferredSize(new Dimension(80, 30));
            int finalI = i;
            button.addActionListener(e -> {
                currentLevel.set(finalI);
                worldPanel.changeLevel(finalI);
            });
            buttonPanel.add(button);
        }
        buttonPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        add(buttonPanel, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        Game win = new Game();
        win.setSize(1400, 800);
        win.setLocationRelativeTo(null);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
    }
}
