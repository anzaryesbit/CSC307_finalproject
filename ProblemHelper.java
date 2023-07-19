/**
 * @author Aiyana Arnobit
 * @author Alec Cheng
 * @author Briana Kuo
 * @author Ke Lyu
 */

public class ProblemHelper {
    private int level;

    public void load(int level) {

    }

    public void getProblem(){
        DataSource.getProblem();
    }

    public int getLevel() {
        return this.level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
}
