import java.util.LinkedList;
import java.util.ArrayList;

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
        program.add(block);
    }

    public void addProgramBlockFirst(Block block){
        program.addFirst(block);
    }

    public void addProgramBlockLast(Block block) {
        program.addLast(block);
    }

    public LinkedList<Block> getProgram() {
        return program;
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
