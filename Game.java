import javax.swing.JFrame;
import java.awt.*;

/**
 * @author Aiyana Arnobit
 * @author Alec Cheng
 * @author Briana Kuo
 * @author Ke Lyu
 */

public class Game extends JFrame{
    public Game(){
        super("Spider Game");
        setLayout(new BorderLayout());

        WorldPanel worldPanel = new WorldPanel();
        WorkAreaPanel workAreaPanel = new WorkAreaPanel();

        add(worldPanel, BorderLayout.WEST);
        add(workAreaPanel, BorderLayout.CENTER);
    }
    public static void main (String[] args) {
        Game win = new Game();
        win.setSize(1400, 800);
        win.setLocationRelativeTo(null);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
    }
}
