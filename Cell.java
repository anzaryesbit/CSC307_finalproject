/**
 * @author Aiyana Arnobit
 * @author Alec Cheng
 * @author Briana Kuo
 * @author Ke Lyu
 */

public class Cell {
    private boolean hasDiamond = false;
    private boolean hasSpider = false;
    private int id;

    public boolean getHasDiamond() {
        return this.hasDiamond;
    }
    public void setHasDiamond(boolean diamond) {
        this.hasDiamond = diamond;
    }

    public boolean getHasSpider() {
        return this.hasSpider;
    }
    public void setHasSpider(boolean spider) {
        this.hasSpider = spider;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
