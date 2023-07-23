/**
 * @author Aiyana Arnobit
 * @author Alec Cheng
 * @author Briana Kuo
 * @author Ke Lyu
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class World extends JPanel{
    ProblemHelper level;
    int currentLevel = 1;
    Spider spider;

    public World(){
        int i = 0;
        int displacement = 100 + 10;
        int size = 0;

        level = new ProblemHelper();
        level.load(currentLevel++);
        size = (int) Math.sqrt(level.getProblem().size());

        spider = new Spider();

        for(int col = 0; col < size; col++){
            for(int row = 0; row < size; row++){
                level.getProblem().get(i).setX(10 + (displacement * row));
                level.getProblem().get(i++).setY(30 + (displacement * col));
            }
        }
        level.getProblem();
    }

    public void paintCell(Cell id, Color c) {
        spider.paintCell(id, c);
    }

    public void draw(Graphics g) {
        spider.draw(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Cell item : level.getProblem()){
            item.draw(g2d);
            if (item.getHasDiamond()){
                g2d.setColor(item.getColor());
                g2d.fillRect(item.getX() + 40, item.getY() + 40, 20, 20);
            }
            if(item.getHasSpider()){
                spider.draw(g);
                spider.setDirection(item.getSpiderDirection());
            }
        }
    }

    public void run() {

    }

    public void compare() {
        for (Cell cell : level.getProblem()){
            if(!cell.compare()){
                return ;
            }
        }
        level.load(currentLevel++);
    }


}
