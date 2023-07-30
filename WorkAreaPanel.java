import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


/**
 * @author Aiyana Arnobit
 * @author Alec Cheng
 * @author Briana Kuo
 * @author Ke Lyu
 */

public class WorkAreaPanel extends JPanel implements MouseListener, MouseMotionListener {

    private DataSource data = DataSource.getDataSource();
    private ConnectHelper ch = new ConnectHelper();
    private String block;
    private char paintColor = 'r';
    private ProblemHelper ph = new ProblemHelper();
    private String[] spawnBlocks;
    private boolean update = false;
    private int x1, y1, x2, y2;
    private int level = 1;

    TrashCan trashCan;
    ParentBlock blockToDelete;
    LoopBlock parentLoop;

    public WorkAreaPanel() {
        addMouseListener(this);
        addMouseMotionListener(this);
        trashCan = new TrashCan();
        setLayout(new BorderLayout());
        JButton resetButton = new JButton("Reset");
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Use FlowLayout with center alignment
        topPanel.setBackground(Color.LIGHT_GRAY); // Set the background color to grey
        topPanel.add(resetButton);
        add(topPanel, BorderLayout.NORTH);
        resetButton.addActionListener(e -> {
            DataSource dataSource = DataSource.getDataSource();
            dataSource.delete();
            repaint();
        });
        ph.load(level);
        spawnBlocks = ph.getSpawnBlock();
    }

    public Color paintBlock(String block) {
        if (block.equals("Step")) {return Color.RED;}
        else if (block.equals("Turn")) {return Color.BLUE;}
        else if (block.equals("Paint")) {return Color.ORANGE;}
        else {return Color.GREEN;}
    }

    public void spawnBlock(Graphics g, ParentBlock block) {
        g.setColor(paintBlock(block.getType()));
        g.fillRect(block.getX(), block.getY(), 50, 25);
        g.setColor(Color.WHITE);
        g.drawString(block.getType(), block.getX()+13, block.getY()+15);
    }

    public void spawnLoopBlock(Graphics g, LoopBlock loopBlock) {
        LinkedList<ParentBlock> loop = loopBlock.getLoop();
        System.out.println("spawning loop block size " + loop.size() + " at " + loopBlock.getX() + ", " + loopBlock.getY());

        int x = loopBlock.getX();
        int y = loopBlock.getY();
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 75, 15);
        g.setColor(Color.WHITE);
        g.drawString("Repeat", x+15, y+11);
        g.setColor(Color.GREEN);

        g.fillRect(x, y+15, 15, 25*loop.size());
        g.fillRect(x, y+15+(25*loop.size()), 75, 15);
        g.setColor(Color.WHITE);
        g.drawString("until wall", x+15, y+26+(25*loop.size()));

        for (ParentBlock blockInLoop : loop) {
            System.out.println(blockInLoop.getType());
            if (blockInLoop.getType().equals("Loop")) { spawnLoopBlock(g, (LoopBlock) blockInLoop); }
            else { 
                spawnBlock(g, blockInLoop); 
                if (blockInLoop.getType().equals("Paint")) {
                    setPaintColor(g, ((Block)blockInLoop).getPaintColor());
                    g.drawRect(blockInLoop.getX(), blockInLoop.getY(), 50, 25);
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        setBackground(Color.LIGHT_GRAY);
        super.paintComponent(g);

        if(spawnBlocks[0].equals("true")) {
            Block stepBlock = new Block(550, 100, "Step", 0);
            spawnBlock(g, stepBlock);
        }
        if(spawnBlocks[1].equals("true")) {
            Block turnBlock = new Block(550, 150, "Turn", 0);
            spawnBlock(g, turnBlock);
        }
        if(spawnBlocks[2].equals("true")) {
            Block paintBlock = new Block(550, 200, "Paint", 0);
            spawnBlock(g, paintBlock);

            g.setColor(Color.RED);
            g.fillOval(paintBlock.getX()+10, paintBlock.getY()+13, 8, 8);
            g.setColor(Color.GREEN);
            g.fillOval(paintBlock.getX()+20, paintBlock.getY()+13, 8, 8);
            g.setColor(Color.BLUE);
            g.fillOval(paintBlock.getX()+30, paintBlock.getY()+13, 8, 8);

            setPaintColor(g, paintColor);
            g.drawRect(paintBlock.getX(), paintBlock.getY(), 50, 25);
        }
        if (spawnBlocks[3].equals("true")) {
            if (parentLoop == null) {
                parentLoop = new LoopBlock(550, 250, "Loop", 0);
            }
            spawnLoopBlock(g, parentLoop);
        }

        LinkedList<ParentBlock> blockList = data.getProgram();
        for (int i=0; i<blockList.size(); i++) {
            ParentBlock curr = blockList.get(i);

            if (curr.getType().equals("Loop")) { spawnLoopBlock(g, (LoopBlock) curr); }
            else {
                spawnBlock(g, curr);

                if (curr.getType().equals("Paint")) {
                    setPaintColor(g, ((Block)curr).getPaintColor());
                    g.drawRect(curr.getX(), curr.getY(), 50, 25);
                }
            }
        }
        trashCan.draw(g);
    }


    private ParentBlock getBlockAtPosition(int x, int y) {
        LinkedList<ParentBlock> blockList = data.getProgram();
        for (ParentBlock block : blockList) {
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
        x1 = e.getX();
        y1 = e.getY();

        if ((x1>550) && (x1<600)) {
            if (y1>100 && y1<125) { block = "Step"; }
            else if (y1>150 && y1<175) { block = "Turn"; }
            else if (y1>200 && y1<225) { block = "Paint"; }
            else if (y1>250 && y1<280+(25*parentLoop.getLoopSize())) { block = "Loop"; }
            else { block = null; }
        }
        else { block = null; }

        if ((x1>data.getProgram().getFirst().getX() && x1<data.getProgram().getFirst().getX()+50)) {
            if (y1>data.getProgram().getFirst().getY() && y1<data.getProgram().getFirst().getY()+25) {
                update = true;
            }
        }
    }

    private void checkPaintType(int x, int y) {
        if (y>213 && y<221) {
            if (x>560 && x<568) {paintColor = 'r';}
            if (x>570 && x<578) {paintColor = 'g';}
            if (x>580 && x<588) {paintColor = 'b';}
        }
    }

    public Set<ParentBlock> getConnectedNeighbors(ParentBlock block){
        Set<ParentBlock> neighbors = new HashSet<>();

        int x = block.getX();
        int y = block.getY();

        for (ParentBlock otherBlock : data.getProgram()) {
            if (otherBlock == block) { continue; }

            int otherX = otherBlock.getX();
            int otherY = otherBlock.getY();

            if ((x == otherX && Math.abs(y - otherY) == 25) || (y == otherY && Math.abs(x - otherX) == 50)) {
                neighbors.add(otherBlock);
            }
        }
        return neighbors;
    }

    public void deleteConnectedBlocks(LinkedList<ParentBlock> queue){
        Set<ParentBlock> visited = new HashSet<>();


        while (!queue.isEmpty()){
            ParentBlock currentBlock = queue.removeFirst();
            visited.add(currentBlock);

            for(ParentBlock neighbor: getConnectedNeighbors(currentBlock)){
                if(!visited.contains(neighbor)){
                    queue.add(neighbor);
                }
            }
        }
        for (ParentBlock block : visited) {
            data.getProgram().remove(block);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        ParentBlock blockToAdd;
        blockToDelete = getBlockAtPosition(x2, y2);
        if (x2 < 500) { ch.connect(block, paintColor, x2, y2); }
        else if (x2>565 && x2<625) {
            if (y2>parentLoop.getY()+5 && y2<parentLoop.getBottom()-5) {
                blockToAdd = new Block(565, parentLoop.getBottom()-15, block, 0);
                if(block.equals("Paint")) { ((Block)blockToAdd).setPaintColor(paintColor); }
                parentLoop.addLastToLoop(blockToAdd);
                data.setParentLoop(parentLoop.getLoop());
            }
        }
        checkPaintType(x2, y2);

        if(update == true) {
            data.updatePosition(x2, y2);
            update = false;
        }

        if (blockToDelete != null && trashCan.isBlockOnTrashCan(blockToDelete)) {
            LinkedList<ParentBlock> queue = new LinkedList<>();
            queue.add(blockToDelete);
            deleteConnectedBlocks(queue);
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