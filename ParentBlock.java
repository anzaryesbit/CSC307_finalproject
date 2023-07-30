public class ParentBlock {
    protected int x;
    protected int y;
    private String type;    
    private int id;

    public ParentBlock(int x, int y, String type, int id) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.id = id;
    }


    public void setX(int x) { this.x = x; }
    public int getX() { return this.x; }

    public void setY(int y) { this.y = y; }
    public int getY() { return this.y; }

    public String getType() { return this.type; }

    public int getId() { return id; }

    public void setId(int id) { this.id=id; }

    public int getBottom() { return (this.y+25); };
}
