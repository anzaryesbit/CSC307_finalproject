import java.awt.*;
/**
 * @author Aiyana Arnobit
 * @author Alec Cheng
 * @author Briana Kuo
 * @author Ke Lyu
 */

public class Spider{
    private int x;
    private int y;
    private char direction;

    public void move() {
        switch (direction){
            case 'n':
                y+=20;
                break;
            case 'e':
                x+=20;
                break;
            case 's':
                y-=20;
                break;
            case 'w':
                x-=20;
                break;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x,y,18,18);
        switch (direction){
            case 'n':
            case 's':
                g.drawLine(x+8,y+5,x-8,y+5);
                g.drawLine(x+8,y-5,x-8,y-5);
                break;
            case 'e':
            case 'w':
                g.drawLine(x+5,y+8,x+5,y-8);
                g.drawLine(x-5,y+8,x-5,y-8);
                break;
        }
    }

    public void paintCell(Cell id, Color c) {
        id.setColor(c);
    }

    public int getX() {
        return this.x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public char getDirection() {
        return direction;
    }
    public void setDirection(char d) {
        this.direction = d;
    }
}