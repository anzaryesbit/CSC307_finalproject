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


    public void connect(String block, char paintColor, int x, int y) {
        DataSource ds = DataSource.getDataSource();
        ParentBlock newBlock;
        if(block != null) {
            LinkedList<ParentBlock> program = ds.getProgram();
            if (program.size() == 0) {
                if (block.equals("Loop")) {
                    newBlock = new LoopBlock(x, y, block, 0); 
                    ((LoopBlock)newBlock).setLoop(ds.getParentLoop());
                }
                else {
                    newBlock = new Block(x, y, block, 0);
                    if(block.equals("Paint")) { ((Block)newBlock).setPaintColor(paintColor); }
                }
                ds.addProgramBlock(newBlock);
                return;
            }
            int tempX = program.getFirst().getX();
            int tempY = program.getFirst().getY();
            if (x>tempX-10 && x<tempX+60) {
                if(y>tempY-12 && y<tempY+12) {
                    if (block.equals("Loop")) { 
                        newBlock = new LoopBlock(x, y, block, 0); 
                        ((LoopBlock)newBlock).setLoop(ds.getParentLoop());
                    }
                    else {
                        newBlock = new Block(tempX, tempY-25, block, 0);
                        if(block.equals("Paint")) { ((Block)newBlock).setPaintColor(paintColor); }
                    }
                    ds.addProgramBlockFirst(newBlock);
                }
                else if(y>program.getLast().getBottom()-12 && y<program.getLast().getBottom()+12) {
                    if (block.equals("Loop")) { newBlock = new LoopBlock(tempX, program.getLast().getBottom(), block, 0); }
                    else {
                        newBlock = new Block(tempX, program.getLast().getBottom(), block, 0);
                        if(block.equals("Paint")) { ((Block)newBlock).setPaintColor(paintColor); }
                    }
                    ds.addProgramBlockLast(newBlock);
                }
            }
            
        }
    }

}
