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
                y+=100;
                break;
            case 'e':
                x+=100;
                break;
            case 's':
                y-=100;
                break;
            case 'w':
                x-=100;
                break;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        switch (direction){
            case 'n':
                g.fillOval(x - 12, y - 15 , 25, 25);
                g.fillOval(x - 20, y , 40, 40);
                g.drawLine(x+30,y+30,x-30,y+30);
                g.drawLine(x+30,y+22,x-30,y+22);
                g.drawLine(x+30,y+15,x-30,y+15);
                g.drawLine(x+30,y+8,x-30,y+8);
                break;
            case 's':

                g.fillOval(x - 12, y + 10, 25, 25);
                g.fillOval(x - 20, y - 20, 40, 40);
                g.drawLine(x + 30, y - 10, x - 30, y - 10);
                g.drawLine(x + 30, y - 2, x - 30, y - 2);
                g.drawLine(x + 30, y + 5, x - 30, y + 5);
                g.drawLine(x + 30, y +12, x - 30, y  +12);
                break;
            case 'e':

                g.fillOval(x ,y - 2, 25, 25);
                g.fillOval(x - 30, y - 10, 40, 40);
                g.drawLine(x - 20, y + 40, x - 20, y - 20);
                g.drawLine(x - 12, y + 40, x - 12, y - 20);
                g.drawLine(x - 5, y + 40, x - 5, y - 20);
                g.drawLine(x +2, y + 40, x +2, y - 20);
                break;
            case 'w':
                g.fillOval(x - 25, y - 2, 25, 25);
                g.fillOval(x-10, y - 10, 40, 40);
                g.drawLine(x + 20, y + 40, x + 20, y - 20);
                g.drawLine(x + 12, y + 40, x + 12, y - 20);
                g.drawLine(x + 5, y + 40, x + 5, y - 20);
                g.drawLine(x -2, y + 40, x -2, y - 20);
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
