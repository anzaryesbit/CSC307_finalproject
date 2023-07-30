import java.util.LinkedList;

public class LoopBlock extends ParentBlock {

    private LinkedList<ParentBlock> loopedBlocks = new LinkedList<>();

    LoopBlock(int x, int y, String type) {super(x, y, type);}

    public int getLoopSize() { return this.loopedBlocks.size(); }
    public LinkedList<ParentBlock> getLoop() { return this.loopedBlocks; }
    public void addToLoop(ParentBlock block) { loopedBlocks.add(block); }
    public void removeFirstFromLoop() { loopedBlocks.removeFirst(); }
    public void removeLastFromLoop() { loopedBlocks.removeLast(); }
}
