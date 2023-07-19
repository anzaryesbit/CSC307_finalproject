import java.util.LinkedList;

/**
 * @author Aiyana Arnobit
 * @author Alec Cheng
 * @author Briana Kuo
 * @author Ke Lyu
 */

//this class is to store blocks and levels

public class DataSource {
    private static DataSource instance;
    private LinkedList<ParentBlock> program;

    private DataSource(){}

    public static DataSource getProblem(){
        if (instance == null){
            instance = new DataSource();
        }
        return instance;
    }

    public void delete(){
        program.clear();
    }

    public void addProgramBlock(ParentBlock block){
        program.add(block);
    }

    public LinkedList<ParentBlock> getProgram() {
        return program;
    }
}
