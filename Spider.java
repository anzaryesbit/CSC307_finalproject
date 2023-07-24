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
        System.out.println("draw");
        System.out.println(x);
        System.out.println(y);
        System.out.println(direction);

        g.setColor(Color.WHITE);
        g.fillOval(x - 20, y - 20, 40, 40);
        g.setColor(Color.WHITE);

        switch (direction){
            case 'n':
            case 's':
                g.drawLine(x+30,y+5,x-30,y+5);
                g.drawLine(x+30,y+10,x-30,y+10);
                g.drawLine(x+30,y-10,x-30,y-10);
                g.drawLine(x+30,y-5,x-30,y-5);
                break;
            case 'e':
            case 'w':
                g.drawLine(x+10,y+30,x+10,y-30);
                g.drawLine(x-10,y+30,x-10,y-30);
                g.drawLine(x+5,y+30,x+5,y-30);
                g.drawLine(x-5,y+30,x-5,y-30);
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
