public class ParentBlock {
    private int x;
    private int y;
    private String type;

    public ParentBlock(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }


    public void setX(int x) { this.x = x; }
    public int getX() { return this.x; }

    public void setY(int y) { this.y = y; }
    public int getY() { return this.y; }

    public String getType() { return this.type; }
}
