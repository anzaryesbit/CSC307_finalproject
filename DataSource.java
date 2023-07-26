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
    private LinkedList<Block> program = new LinkedList<>();
    //private ArrayList<Block> blocks = new ArrayList<>();

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


    public void addProgramBlock(Block block) {
        block.setId(blockIdCounter);
        blockIdCounter++;
        program.add(block);
    }

    public void addProgramBlockFirst(Block block){
        block.setId(blockIdCounter);
        blockIdCounter++;
        program.addFirst(block);
    }

    public void addProgramBlockLast(Block block) {
        block.setId(blockIdCounter);
        blockIdCounter++;
        program.addLast(block);
    }

    public LinkedList<Block> getProgram() {
        return program;
    }

    public void updatePosition(int x, int y) {
        System.out.println(program.size());
        for (int i=0; i<program.size(); i++) {
            Block block = program.get(i);
            block.setX(x);
            block.setY(y+(25*i));
        }
    }
    
    // public ArrayList<Block> getBlocks() {
    //     return blocks;
    // }

    // public void addBlock(Block block) {
    //     blocks.add(block);
    // }

    // public Block getBlockAt(int i) {
    //     return blocks.get(i);
    // }
}
