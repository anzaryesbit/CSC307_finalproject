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
    @Override
    public int getBottom() {
        return (this.y+(loopedBlocks.size()*25)+30);
    }
}
