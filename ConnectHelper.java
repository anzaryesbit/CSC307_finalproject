import javax.xml.crypto.Data;
import java.util.LinkedList;
import java.awt.*;

/**
 * @author Aiyana Arnobit
 * @author Alec Cheng
 * @author Briana Kuo
 * @author Ke Lyu
 */

public class ConnectHelper {
    private DataSource ds = DataSource.getDataSource();

    public void connect(String block, char paintColor, int x, int y) {
        if(block != null) {
            LinkedList<Block> program = ds.getProgram();
            if (program.size() == 0) {
                if (block.equals("Loop")) {
                    LoopBlock loopBlock = new LoopBlock(x, y, block);
                }
                else {
                    Block newBlock = new Block(x, y, block);
                    if(block.equals("Paint")) { 
                        newBlock.setPaintColor(paintColor); 
                    }
                    ds.addProgramBlock(newBlock);
                }
                return;
            }
            if (x>program.getFirst().getX()-10 && x<program.getFirst().getX()+60) {
                if(y>program.getFirst().getY()-5 && y<program.getFirst().getY()+12) {
                    Block newBlock = new Block(program.getFirst().getX(), program.getFirst().getY()-25, block);
                    if(block.equals("Paint")) { newBlock.setPaintColor(paintColor); }
                    ds.addProgramBlockFirst(newBlock);
                }
                else if(y>program.getLast().getY()+13 && y<program.getLast().getY()+30) {
                    Block newBlock = new Block(program.getLast().getX(), program.getLast().getY()+25, block);
                    if(block.equals("Paint")) { newBlock.setPaintColor(paintColor); }
                    ds.addProgramBlockLast(newBlock);
                }
            }
            
        }
    }

}
