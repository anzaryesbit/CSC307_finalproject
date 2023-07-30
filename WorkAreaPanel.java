import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
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
    private Color color;
    private String block;
    private char paintColor = 'r';
    private ProblemHelper ph = new ProblemHelper();
    private String[] spawnBlocks;
    private boolean dragging;
    private boolean update = false;
    private int x1, y1, x2, y2;
    private int level = 1;

    TrashCan trashCan;
    Block blockToDelete;
    LoopBlock parentLoop;

    public WorkAreaPanel() {
        addMouseListener(this);
        addMouseMotionListener(this);
        dragging = false;
        trashCan = new TrashCan();
        ph.load(level);
        spawnBlocks = ph.getSpawnBlock();
    }

    public Color paintBlock(String block) {
        if (block.equals("Step")) {return Color.RED;}
        else if (block.equals("Turn")) {return Color.BLUE;}
        else if (block.equals("Paint")) {return Color.ORANGE;}
        else {return Color.GREEN;}
    }

    public void spawnBlock(Graphics g, Block block) {
        g.setColor(paintBlock(block.getType()));
        g.fillRect(block.getX(), block.getY(), 50, 25);
        g.setColor(Color.WHITE);
        g.drawString(block.getType(), block.getX()+13, block.getY()+15);
    }

    public void spawnLoopBlock(Graphics g, LoopBlock loopBlock) {
        LinkedList<ParentBlock> loop = loopBlock.getLoop();

        int x = loopBlock.getX();
        int y = loopBlock.getY();
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 75, 15);
        g.setColor(Color.WHITE);
        g.drawString("repeat", x+15, y+11);
        g.setColor(Color.GREEN);

        System.out.println("loop size " +loop.size());
        g.fillRect(x, y+15, 15, 25*loop.size());
        g.fillRect(x, y+15+(25*loop.size()), 75, 15);
        g.setColor(Color.WHITE);
        g.drawString("until wall", x+15, y+26+(25*loop.size()));
    }

    public void paintComponent(Graphics g) {
        setBackground(Color.LIGHT_GRAY);
        super.paintComponent(g);

        if(spawnBlocks[0].equals("true")) {
            Block stepBlock = new Block(550, 100, "Step");
            spawnBlock(g, stepBlock);
        }
        if(spawnBlocks[1].equals("true")) {
            Block turnBlock = new Block(550, 150, "Turn");
            spawnBlock(g, turnBlock);
        }
        if(spawnBlocks[2].equals("true")) {
            Block paintBlock = new Block(550, 200, "Paint");
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
            parentLoop = new LoopBlock(550, 250, "Loop");
            spawnLoopBlock(g, parentLoop);
        }

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
            spawnBlock(g, curr);

            if (curr.getType().equals("Paint")) {
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

    public Set<Block> getConnectedNeighbors(Block block){
        Set<Block> neighbors = new HashSet<>();

        int x = block.getX();
        int y = block.getY();

        for (Block otherBlock : data.getProgram()) {
            if (otherBlock == block) {
                continue;
            }

            int otherX = otherBlock.getX();
            int otherY = otherBlock.getY();

            if ((x == otherX && Math.abs(y - otherY) == 25) || (y == otherY && Math.abs(x - otherX) == 50)) {
                neighbors.add(otherBlock);
            }
        }

        return neighbors;
    }

    public void deleteConnectedBlocks(LinkedList<Block> queue){
        Set<Block> visited = new HashSet<>();


        while (!queue.isEmpty()){
            Block currentBlock = queue.removeFirst();
            visited.add(currentBlock);

            for(Block neighbor: getConnectedNeighbors(currentBlock)){
                if(!visited.contains(neighbor)){
                    queue.add(neighbor);
                }
            }
        }
        for (Block block : visited) {
            data.getProgram().remove(block);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
        x2 = e.getX();
        y2 = e.getY();
        blockToDelete = getBlockAtPosition(x2, y2);
        if (x2 < 500) { ch.connect(block, paintColor, x2, y2); }
        else { 
            checkPaintType(x2, y2); 
        }

        if(update == true) {
            data.updatePosition(x2, y2);
            update = false;
        }

        if (blockToDelete != null && trashCan.isBlockOnTrashCan(blockToDelete)) {
            LinkedList<Block> queue = new LinkedList<>();
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