import java.awt.*;
import java.util.LinkedList;

public class TrashCan {
    private DataSource dataSource;
    int width;
    int height;
    int x;
    int y;
    public TrashCan(){
        dataSource = DataSource.getDataSource();
        width = 30;
        height = 50;
        x = 40;
        y = 670;
    }


    public void draw(Graphics g){
        // lid
        g.setColor(Color.BLACK);
        g.fillRect(x - 5, y - 5, width + 10, 10);

        // trashcan body
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);

        // vertical lines
        g.setColor(Color.BLACK);
        int lineSpacing = width / 6;
        for (int i = 1; i <= 5; i++) {
            int lineX = x + i * lineSpacing;
            g.drawLine(lineX, y, lineX, y + height);
        }
    }


    public boolean isBlockOnTrashCan(Block block){
        int blockX = block.getX();
        int blockY = block.getY();
        int blockSize = 25;

        boolean XCollision= blockX+ blockSize >x && blockX < x + width;
        boolean YCollision = blockY + blockSize > y && blockY <y + height;

        return XCollision&& YCollision;

    }


}