import java.util.ArrayList;

public class Line {
    private ArrayList<Double[]> vectors;
    private String name;

    public Line(String name){
        this.name = name;
        this.vectors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void check(String[] n){
        if(n[n.length-1] == this.name){
            Double[] tmp = new Double[n.length-1];
            for(int i = 0; i < n.length-1; i++)
                tmp[i] = Double.parseDouble(n[i]);
            vectors.add(tmp);
        }
    }
}
