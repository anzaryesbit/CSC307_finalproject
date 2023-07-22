/**
 * @author Aiyana Arnobit
 * @author Alec Cheng
 * @author Briana Kuo
 * @author Ke Lyu
 */

import java.awt.*;

public class World {
    Cell[] cells = new Cell[25];
    public World(){
        int x = 10, y = 30, width = 20, count = 0;
        for (int col = 0; col < 5; col ++){
            for(int row = 0; row < 5; row++){
                cells[count++] = new Cell(count,x + (width * row), y + (width * col), "Black");
            }
        }
    }

    public void paintCell(Cell id, Color c) {
        id.setColor(c);
    }

    public void draw(Graphics g) {
        for(int i = 0; i < cells.length; i++){
            cells[i].draw(g);
        }
    }

    public void run() {

    }

    public void compare() {

    }

}
