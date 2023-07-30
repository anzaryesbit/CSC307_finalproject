import java.util.LinkedList;

public class LoopBlock extends ParentBlock {

    private LinkedList<ParentBlock> loopedBlocks = new LinkedList<>();

    LoopBlock(int x, int y, String type, int id) {super(x, y, type, id);}

    public int getLoopSize() { return this.loopedBlocks.size(); }
    public void setLoop(LinkedList<ParentBlock> loop) { this.loopedBlocks = loop; }
    public LinkedList<ParentBlock> getLoop() { return this.loopedBlocks; }
    public void addFirstToLoop(ParentBlock block) { loopedBlocks.addFirst(block); }
    public void addLastToLoop(ParentBlock block) {loopedBlocks.addLast(block);}
    public void removeFirstFromLoop() { loopedBlocks.removeFirst(); }
    public void removeLastFromLoop() { loopedBlocks.removeLast(); }

    public void moveSubList(boolean makeNew) {
        if (makeNew == true) {
            LinkedList<ParentBlock> adjustedLoop = new LinkedList<>();
            for (int i=0; i<loopedBlocks.size(); i++) {
                ParentBlock block = loopedBlocks.get(i);
                ParentBlock newBlock = new ParentBlock(super.x+15, super.y+15+(25*i), block.getType(), 0);
                if (block.getType().equals("Paint")) { ((Block)newBlock).setPaintColor(((Block)block).getPaintColor());}
                adjustedLoop.add(newBlock);
            }
            loopedBlocks = adjustedLoop;
        }
        else {
            for (int i=0; i<loopedBlocks.size(); i++) {
                ParentBlock block = loopedBlocks.get(i);
                block.setX(super.x+15);
                block.setY(super.y+15+(25*i));
            }
        }
    }

    @Override
    public int getBottom() {
        return (this.y+(loopedBlocks.size()*25)+30);
    }
}
