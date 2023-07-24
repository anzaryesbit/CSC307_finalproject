/**
 * @author Aiyana Arnobit
 * @author Alec Cheng
 * @author Briana Kuo
 * @author Ke Lyu
 */

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class World extends JPanel{
    ProblemHelper level;
    DataSource ds;
    int currentLevel = 1;
    int programStep = 0;
    int size;
    Spider spider;

    public World(){
        int i = 0;
        int displacement = 100 + 10;
        ds = DataSource.getDataSource();

        level = new ProblemHelper();
        level.load(currentLevel);
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
        Graphics2D g2d = (Graphics2D) g;
        for (Cell item : level.getProblem()){
            item.draw(g2d);
            if (item.getHasDiamond()){
                g2d.setColor(item.getColor());
                g2d.fillRect(item.getX() + 40, item.getY() + 40, 20, 20);
            }
            if(item.getHasSpider()){
                spider.setDirection(item.getSpiderDirection());
                spider.draw(g);
            }
        }
    }

    public Color changeToColor(char c) {
        switch (c) {
            case 'r' -> {
                return Color.RED;
            }
            case 'g' -> {
                return Color.GREEN;
            }
            case 'b' -> {
                return Color.BLUE;
            }
            default -> {
            }
        }
        return null;
    }

    public boolean run() {
        if (programStep == ds.getProgram().size()){
            return false;
        }
        Block program = ds.getProgram().get(programStep++);
        if(Objects.equals(program.getType(), "Paint")){
            for(Cell item : level.getProblem()){
                if (item.getHasSpider()){
                    paintCell(item, changeToColor(program.getPaintColor()));
                    return true;
                }
            }
        }
        else if (Objects.equals(program.getType(), "Move")) {
            int i = 0;
            for (Cell item : level.getProblem()) {
                if (item.getHasSpider()) {
                    item.toggleSpider();
                    switch (spider.getDirection()) {
                        case 'n' -> {
                            level.getProblem().get(i - size).toggleSpider();
                            spider.setY(spider.getY() - 100);
                        }
                        case 'e' -> {
                            level.getProblem().get(i + 1).toggleSpider();
                            spider.setX(spider.getX() + 100);
                        }
                        case 's' -> {
                            level.getProblem().get(i + size).toggleSpider();
                            spider.setY(spider.getY() + 100);
                        }
                        default -> {
                            level.getProblem().get(i - 1).toggleSpider();
                            spider.setX(spider.getX() - 100);
                        }
                    }
                    i++;
                }
            }
        }
        else{
            switch (spider.getDirection()) {
                case 'n' -> spider.setDirection('e');
                case 'e' -> spider.setDirection('s');
                case 's' -> spider.setDirection('w');
                default -> spider.setDirection('n');
            }
        }
        return true;
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
