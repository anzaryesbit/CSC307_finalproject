import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * @author Aiyana Arnobit
 * @author Alec Cheng
 * @author Briana Kuo
 * @author Ke Lyu
 */

public class Game extends JFrame {
    public Game() {
        super("Spider Game");
        setLayout(new BorderLayout());
        WorldPanel worldPanel = new WorldPanel();
        WorkAreaPanel workAreaPanel = new WorkAreaPanel();
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Level: 1");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitleColor(Color.BLACK);
        CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(new EmptyBorder(10, 0, 0, 0), titledBorder);
        workAreaPanel.setBorder(compoundBorder);
        add(worldPanel, BorderLayout.WEST);
        add(workAreaPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        for (int i = 1; i <= 15; i++) {
            JButton button = new JButton("Level " + i);
            button.setPreferredSize(new Dimension(80, 30));
            int finalI = i;
            button.addActionListener(e -> {
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
