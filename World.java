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
    private ProblemHelper level;
    private int programStep = 0;
    private int size;
    private Spider spider;

    public World(int newLevel){
        int i = 0;
        int displacement = 100 + 10;

        level = new ProblemHelper();
        level.load(newLevel);
        size = (int) Math.sqrt(level.getProblem().size());

        spider = new Spider();

        for(int col = 0; col < size; col++){
            for(int row = 0; row < size; row++){
                level.getProblem().get(i).setX(10 + (displacement * row));
                level.getProblem().get(i++).setY(30 + (displacement * col));
            }
        }
    }

    public void paintCell(Cell id, Color c) {
        spider.paintCell(id, c);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int index = 0;
        for (Cell item : level.getProblem()){

            item.draw(g2d);
            if (item.getHasDiamond()){
                g2d.setColor(item.getColor());
                g2d.fillRect(item.getX() + 40, item.getY() + 40, 20, 20);
            }
            if(item.getHasSpider()){
                char temp = spider.getDirection();
                if (temp != '\u0000'){
                    System.out.println(spider.getDirection());
                }
                else{
                    spider.setDirection(item.getSpiderDirection());
                }
                int numRows = 5;
                int numCols = 5;
                int row = (index/numRows) ;
                int col = index % numCols ;
                spider.setX((col*110)+60);
                spider.setY((row*110)+70);
                System.out.print("x: " +spider.getX());
                System.out.println("y: " +spider.getY());
                spider.draw(g);
            }
            index++;
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

    public boolean checkLeaveGrid(int i, char dir){
        return switch (dir) {
            case 'n' -> i - size > 0;
            case 'e' -> (i + 1) % 5 != 0;
            case 's' -> i + size < (size * size + 1);
            default -> i % 5 != 0;
        };
    }

    public boolean run() {

        if (programStep == DataSource.getDataSource().getProgram().size()){
            System.out.println("returning false");
            return false;
        }
        Block program = DataSource.getDataSource().getProgram().get(programStep++);
        if(Objects.equals(program.getType(), "Paint")){
            System.out.println("paint ack");
            for(Cell item : level.getProblem()){
                if (item.getHasSpider()){
                    paintCell(item, changeToColor(program.getPaintColor()));
                }
            }
        }
        else if (Objects.equals(program.getType(), "Step")) {
            System.out.println("step ack");
            int i = 0;
            for (Cell item : level.getProblem()) {
                if (item.getHasSpider()) {
                    if(!checkLeaveGrid(i, spider.getDirection())){
                        JOptionPane.showConfirmDialog(null, "The spider will leave the grid!", "Warning", JOptionPane.DEFAULT_OPTION);
                        return false;
                    }
                    item.toggleSpider();
                    switch (spider.getDirection()) {
                        case 'n' -> {
                            System.out.println("i: "+ i + " Size: " + size);
                            level.getProblem().get(i - size).toggleSpider();
                            spider.move();
                            return true;
                        }
                        case 'e' -> {
                            System.out.println("i: "+ i);
                            level.getProblem().get(i + 1).toggleSpider();
                            spider.move();
                            return true;
                        }
                        case 's' -> {
                            level.getProblem().get(i + size).toggleSpider();
                            spider.move();
                            return true;
                        }
                        default -> {
                            level.getProblem().get(i - 1).toggleSpider();
                            spider.move();
                            return true;
                        }
                    }
                }
                i++;
            }
        }
        else {
            System.out.println("turn ack");
            switch (spider.getDirection()) {
                case 'n' -> spider.setDirection('e');
                case 'e' -> spider.setDirection('s');
                case 's' -> spider.setDirection('w');
                default -> spider.setDirection('n');
            }
            System.out.println(spider.getDirection());
        }
        return true;
    }

    public boolean compare() {
        for (Cell cell : level.getProblem()) {
            if (!cell.compare()) {
                return false;
            }
        }
        return true;
    }


}
