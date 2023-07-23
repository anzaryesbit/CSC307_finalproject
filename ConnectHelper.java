import javax.xml.crypto.Data;
import java.util.LinkedList;

/**
 * @author Aiyana Arnobit
 * @author Alec Cheng
 * @author Briana Kuo
 * @author Ke Lyu
 */

public class ConnectHelper {
    private DataSource ds = DataSource.getDataSource();

    public void connect(String block, int x, int y) {
        if(block != null) {
            LinkedList<Block> program = ds.getProgram();
            if (program.size() == 0) {
                Block newBlock = new Block(x, y, block);
                ds.addProgramBlock(newBlock);
                return;
            }
            if (x>program.getFirst().getX()-10 && x<program.getFirst().getX()+60) {
                if(y>program.getFirst().getY()-5 && y<program.getFirst().getY()+12) {
                    Block newBlock = new Block(program.getFirst().getX(), program.getFirst().getY()-25, block);
                    ds.addProgramBlockFirst(newBlock);
                }
                else if(y>program.getLast().getY()+13 && y<program.getLast().getY()+30) {
                    Block newBlock = new Block(program.getLast().getX(), program.getLast().getY()+25, block);
                    ds.addProgramBlockLast(newBlock);
                }
            }
            
        }
    }

}
