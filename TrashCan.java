import java.awt.*;

public class TrashCan {
    public TrashCan(){

    }

    public void draw(Graphics g){
        int width = 30;
        int height = 50;
        int x = 40;
        int y = 670;
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




}
