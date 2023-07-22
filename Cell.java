import java.awt.*;

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
    private int x;
    private int y;
    private String color;
    private Color shownColor = Color.BLACK;

    public Cell(int id, int x, int y, String color){
        this.id = id;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public boolean getHasDiamond() {
        return this.hasDiamond;
    }
    public void setHasDiamond(boolean diamond) {
        this.hasDiamond = diamond;
    }

    public void toggleHasDiamond(){
        this.hasDiamond = !this.hasDiamond;
    }

    public boolean getHasSpider() {
        return this.hasSpider;
    }
    public void setHasSpider(boolean spider) {
        this.hasSpider = spider;
    }

    public void toggleSpider(){
        this.hasSpider = !this.hasSpider;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void draw(Graphics g) {
        g.setColor(this.shownColor);
        g.fillRect(this.x, this.y, 20, 20);
    }

    public void setColor(Color color){
        this.shownColor = color;
    }

}
