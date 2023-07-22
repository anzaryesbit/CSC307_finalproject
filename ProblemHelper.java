import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Aiyana Arnobit
 * @author Alec Cheng
 * @author Briana Kuo
 * @author Ke Lyu
 */

public class ProblemHelper {
    private List<Cell> problem;

    public void load(int level) {
        try(BufferedReader reader = new BufferedReader(new FileReader("levels/"+level+".txt"))){
            String gridDataStr = reader.readLine();
            String[] gridDataArr = gridDataStr.split(", ");


            int id = 0;

            for (int i =0; i< gridDataArr.length;i++) {
                System.out.println(gridDataArr[i]);
                String currCellData = gridDataArr[i];

                Color color =Color.black;

                boolean hasDiamond=false;
                boolean hasSpider=false;
                double spiderDirection=Math.toRadians(90);


                id = i+1;
                if(currCellData.contains("r")){
                    color = Color.RED;
                    hasDiamond=true;
                } else if (currCellData.contains("b")) {
                    color = Color.BLUE;
                    hasDiamond=true;
                } else if (currCellData.contains("g")) {
                    color = Color.GREEN;
                    hasDiamond=true;
                }

                if(currCellData.contains("n")){
                    spiderDirection = Math.toRadians(90);
                    hasSpider=true;
                } else if (currCellData.contains("s")) {
                    spiderDirection = Math.toRadians(270);
                    hasSpider=true;
                } else if (currCellData.contains("w")) {
                    spiderDirection = Math.toRadians(180);
                    hasSpider=true;
                } else if (currCellData.contains("e")) {
                    spiderDirection = Math.toRadians(0);
                    hasSpider=true;
                }

                Cell cell = new Cell(hasDiamond,hasSpider, color,spiderDirection, id);

                problem.add(cell);
            }


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<Cell> getProblem(){
        return problem;
    }


    public void setProblem(int level) {
        this.problem = problem;
    }
}
