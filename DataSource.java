import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Set;

/**
 * @author Aiyana Arnobit
 * @author Alec Cheng
 * @author Briana Kuo
 * @author Ke Lyu
 */

//this class is to store blocks and levels

public class DataSource {
    private static DataSource instance;
    private LinkedList<ParentBlock> program = new LinkedList<>();
    private LinkedList<ParentBlock> parentLoop = new LinkedList<>();

    private int blockIdCounter =1;

    private DataSource(){}

    public static DataSource getDataSource(){
        if (instance == null){
            instance = new DataSource();
        }
        return instance;
    }

    public void delete(){
        program.clear();
    }


    public void addProgramBlock(ParentBlock block) {
        block.setId(blockIdCounter);
        blockIdCounter++;
        program.add(block);
    }

    public void addProgramBlockFirst(ParentBlock block){
        block.setId(blockIdCounter);
        blockIdCounter++;
        program.addFirst(block);
    }

    public void addProgramBlockLast(ParentBlock block) {
        block.setId(blockIdCounter);
        blockIdCounter++;
        program.addLast(block);
    }

    public LinkedList<ParentBlock> getProgram() {
        return program;
    }

    public void updatePosition(int x, int y) {
        for (int i=0; i<program.size(); i++) {
            ParentBlock block = program.get(i);
            block.setX(x);
            block.setY(y+(25*i));
        }
    }

    public void addLoopBlock(ParentBlock block) { parentLoop.add(block); }

    public void addLoopBlockFirst(ParentBlock block) { parentLoop.addFirst(block); }

    public void addLoopBlockLast(ParentBlock block) { parentLoop.addLast(block); }

    public LinkedList<ParentBlock> getParentLoop() { return parentLoop; }

    public void setParentLoop(LinkedList<ParentBlock> parentLoop) { this.parentLoop = parentLoop; }
}
