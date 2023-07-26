/**
 * @author Aiyana Arnobit
 * @author Alec Cheng
 * @author Briana Kuo
 * @author Ke Lyu
 */

public class Block extends ParentBlock {
    char paintColor = 0;
    private int id;

    Block(int x, int y, String type) {super(x, y, type);}

    Block(int x, int y, String type, int id){
        this(x, y, type);
        this.id = id;
    }

    public void setPaintColor(char c) { this.paintColor = c; }
    public char getPaintColor() { return this.paintColor; }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }


}
