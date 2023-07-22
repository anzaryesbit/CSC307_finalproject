import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

            for (Cell item : problem) {
                System.out.println(item.getId() + " ^^^^^^ " +item.hasDiamond());
            }
//            {}
//            {}
//            {}
//            {}
//            {}
//            {}
//            {r}
//            {}
//            {b}
//            {}
//            {}
//            {g}
//            {}
//            {}
//            {}
//            {}
//            {n}
//            {}
//            {}
//            {}
//            {}
//            {}
//            {}
//            {}
//            {},
//            1 ^^^^^^ false
//            2 ^^^^^^ false
//            3 ^^^^^^ false
//            4 ^^^^^^ false
//            5 ^^^^^^ false
//            6 ^^^^^^ false
//            7 ^^^^^^ true
//            8 ^^^^^^ false
//            9 ^^^^^^ true
//            10 ^^^^^^ false
//            11 ^^^^^^ false
//            12 ^^^^^^ true
//            13 ^^^^^^ false
//            14 ^^^^^^ false
//            15 ^^^^^^ false
//            16 ^^^^^^ false
//            17 ^^^^^^ false
//            18 ^^^^^^ false
//            19 ^^^^^^ false
//            20 ^^^^^^ false
//            21 ^^^^^^ false
//            22 ^^^^^^ false
//            23 ^^^^^^ false
//            24 ^^^^^^ false
//            25 ^^^^^^ false


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void getProblem(){
        DataSource.getProblem();
    }


    public void setProblem(int level) {
        this.problem = problem;
    }
}
