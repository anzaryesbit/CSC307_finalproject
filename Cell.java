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
    private char spiderDirection;
    private Color color = Color.BLACK;
    private Color shownColor = Color.BLACK;

    public Cell(boolean hasDiamond, boolean hasSpider, Color color, char spiderDirection, int id){
        this.hasDiamond = hasDiamond;
        this.hasSpider = hasSpider;
        this.color = color;
        this.spiderDirection = spiderDirection;
        this.id = id;
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

    public char getSpiderDirection() {
        return spiderDirection;
    }

    public boolean getHasSpider() {
        return this.hasSpider;
    }
    public void setHasSpider(boolean spider) {
        this.hasSpider = spider;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getX(){
        return this.x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getY(){
        return this.y;
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
        g.fillRect(this.x, this.y, 100, 100);
    }

    public void setColor(Color color){
        this.shownColor = color;
    }

    public Color getColor(){
        return this.color;
    }

    public boolean compare(){
        return this.color == this.shownColor;
    }

}
