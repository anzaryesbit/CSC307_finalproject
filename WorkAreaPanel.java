import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.LinkedList;



/**
 * @author Aiyana Arnobit
 * @author Alec Cheng
 * @author Briana Kuo
 * @author Ke Lyu
 */

public class WorkAreaPanel extends JPanel implements MouseListener, MouseMotionListener {
    
    private DataSource data = DataSource.getDataSource();
    private ConnectHelper ch = new ConnectHelper();
    private Color color;
    private String block;
    private char paintColor = 'r';
    private String[] spawnBlocks = {"true", "true", "true", "false", "false"};
    private boolean dragging;
    private boolean update = false;
    private int x1, y1, x2, y2;

    TrashCan trashCan;
    Block blockToDelete;
    
    public WorkAreaPanel() {
        addMouseListener(this);
        addMouseMotionListener(this);
        dragging = false;
        trashCan = new TrashCan();

    }

    public void paintBlock() {
        if (block.equals("Step")) {color = Color.RED;}
        if (block.equals("Turn")) {color = Color.BLUE;}
        if (block.equals("Paint")) {color = Color.ORANGE;}
        if (block.equals("Loop")) {color = Color.GREEN;}
        if (block.equals("Sequence")) {color = Color.PINK;}
    }

    // public void spawnBlocks() {
    //     for ()
    // }

    public void paintComponent(Graphics g) {
        setBackground(Color.LIGHT_GRAY);
        super.paintComponent(g);

        Block stepBlock = new Block(550, 100, "Step");
        this.block = stepBlock.getType();
        paintBlock();
        g.setColor(this.color);
        g.fillRect(stepBlock.getX(), stepBlock.getY(), 50, 25);
        g.setColor(Color.WHITE);
        g.drawString(stepBlock.getType(), stepBlock.getX()+13, stepBlock.getY()+15);

        Block turnBlock = new Block(550, 150, "Turn");
        this.block = turnBlock.getType();
        paintBlock();
        g.setColor(this.color);
        g.fillRect(turnBlock.getX(), turnBlock.getY(), 50, 25);
        g.setColor(Color.WHITE);
        g.drawString(turnBlock.getType(), turnBlock.getX()+13, turnBlock.getY()+15);

        Block paintBlock = new Block(550, 200, "Paint");
        this.block = paintBlock.getType();
        paintBlock();
        g.setColor(this.color);
        g.fillRect(paintBlock.getX(), paintBlock.getY(), 50, 25);
        g.setColor(Color.WHITE);
        g.drawString(paintBlock.getType(), paintBlock.getX()+12, paintBlock.getY()+15);

        g.setColor(Color.RED);
        g.fillOval(paintBlock.getX()+10, paintBlock.getY()+13, 8, 8);
        g.setColor(Color.GREEN);
        g.fillOval(paintBlock.getX()+20, paintBlock.getY()+13, 8, 8);
        g.setColor(Color.BLUE);
        g.fillOval(paintBlock.getX()+30, paintBlock.getY()+13, 8, 8);

        setPaintColor(g, paintColor);
        g.drawRect(paintBlock.getX(), paintBlock.getY(), 50, 25);

        // if (dragging) {
        //     paintBlock();
        //     g.setColor(this.color);
        //     g.fillRect(x2, y2, 100, 50);
        //     g.setColor(Color.WHITE);
        //     g.drawString(block, x2+38, y2+28);
        // }

        LinkedList<Block> blockList = data.getProgram();
        for (int i=0; i<blockList.size(); i++) {
            Block curr = blockList.get(i);

            this.block = curr.getType();
            paintBlock();
            g.setColor(this.color);
            g.fillRect(curr.getX(), curr.getY(), 50, 25);
            g.setColor(Color.WHITE);
            g.drawString(curr.getType(), curr.getX()+13, curr.getY()+15);

            if (curr.getType().equals("Paint")) {
                System.out.println("current block has paint color "+ curr.getPaintColor());
                setPaintColor(g, curr.getPaintColor());
                g.drawRect(curr.getX(), curr.getY(), 50, 25);
            }
        }

        trashCan.draw(g);

    }


    private Block getBlockAtPosition(int x, int y) {
        LinkedList<Block> blockList = data.getProgram();
        for (Block block : blockList) {
            if (x >= block.getX() && x <= block.getX() + 50 && y >= block.getY() && y <= block.getY() + 25) {
                return block;
            }
        }
        return null;
    }

    public void setPaintColor(Graphics g, char c) {
        switch(c) {
            case 'r':
                g.setColor(Color.RED); break;
            case 'g':
                g.setColor(Color.GREEN); break;
            case 'b':
                g.setColor(Color.BLUE); break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        dragging = true;
        x1 = e.getX();
        y1 = e.getY();

        System.out.println("x="+x1+" y="+y1);
        if ((x1>550) && (x1<600)) {
            if (y1>100 && y1<125) { block = "Step"; }
            else if (y1>150 && y1<175) { block = "Turn"; }
            else if (y1>200 && y1<225) { block = "Paint"; System.out.println("dragging Paint");}
            else { block = null; }
        }
        else { block = null; }

        if ((x1>data.getProgram().getFirst().getX() && x1<data.getProgram().getFirst().getX()+50)) {
            if (y1>data.getProgram().getFirst().getY() && y1<data.getProgram().getFirst().getY()+25) {
                update = true;
            }
        }
        // if(block != null) {
        //     //System.out.println(block);
        // }
    }

    private void checkPaintType(int x, int y) {
        if (y>213 && y<221) {
            if (x>560 && x<568) {paintColor = 'r';}
            if (x>570 && x<578) {paintColor = 'g';}
            if (x>580 && x<588) {paintColor = 'b';}
        }
        System.out.println("set paint color to "+ paintColor);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
        x2 = e.getX();
        y2 = e.getY();
        blockToDelete = getBlockAtPosition(x2, y2);
        if (x2 < 500) { ch.connect(block, paintColor, x2, y2); }
        else { checkPaintType(x2, y2); }

        if(update == true) {
            data.updatePosition(x2, y2);
            update = false;
        }

        if (blockToDelete != null && trashCan.isBlockOnTrashCan(blockToDelete)) {
            data.getProgram().remove(blockToDelete);
        }
        repaint();
    }

    @Override 
    public void mouseDragged(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}

}
