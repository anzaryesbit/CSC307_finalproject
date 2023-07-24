/**
 * @author Aiyana Arnobit
 * @author Alec Cheng
 * @author Briana Kuo
 * @author Ke Lyu
 */

public class Block extends ParentBlock {
    char paintColor = 0;

    Block(int x, int y, String type) {
        super(x, y, type);
    }

    public void setPaintColor(char c) { this.paintColor = c; }
    public char getPaintColor() { return this.paintColor; }
}
