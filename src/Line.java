import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Line {
    private List<Double> values;
    private String name;
    private double distance;

    public Line(String name, double distance){
        this.name = name;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }

    public List<Double> getValues() {
        return values;
    }
    
    public static double calculateDistance(double d1, double d2){
        return Math.pow(d1-d2, 2);
    }

    public static boolean guess(int k, List<Line> lines, String answer){
        List<Line> tmpList = new ArrayList<>();
        String end = new String();
        int maxCount = 0;
        int count = 0;

        for(int i = 0; i < k; i++) 
            tmpList.add(lines.get(i));

        for(int i = 0; i < tmpList.size(); i++){
            for(int q = 0; q < tmpList.size(); q++)
                if(tmpList.get(i).getName().equals(tmpList.get(q).getName()))
                    count++;
            
            if(count > maxCount) end = tmpList.get(i).getName();
            count = 0;
        }
        return end.equals(answer);
    }

    public static void check(int k, Scanner train, String test) throws Exception{
        List<Line> tmpList = new ArrayList<>();
        double value = 0;
        int correct = 0;
        int all = 0;

        while(train.hasNext()){
            String[] data = train.nextLine().split(",");
            Scanner tests = new Scanner(new FileInputStream(test));
            all++;
            
            while(tests.hasNext()){
                String[] tmp = tests.nextLine().split(",");

                for(int i = 0; i < data.length-1; i++)
                    value += calculateDistance(Double.parseDouble(data[i]), Double.parseDouble(tmp[i]));

                tmpList.add(new Line(tmp[tmp.length-1], Math.sqrt(value)));
                value = 0;
            }

            tmpList.sort(Comparator.comparing(Line::getDistance));

            if(guess(k, tmpList, data[data.length-1])){
                correct++;
                System.out.println("Zgadnieto typ");
            }else System.out.println("Nie udalo sie");

            tmpList.clear();
        }

        System.out.println("Skutecznosc to: " + correct + "/" + all);
    }
}
