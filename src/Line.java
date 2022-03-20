import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Line {
    private ArrayList<Double[]> vectors;
    private ArrayList<Double> tmp;
    private String name;
    // private int strike;

    public Line(String name){
        this.name = name;
        // this.strike = 0;
        this.vectors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void add(String[] n){      
        Double[] tmp = new Double[n.length-1];
        for(int i = 0; i < n.length-1; i++)
            tmp[i] = Double.parseDouble(n[i]);
        vectors.add(tmp);
    }

    public List<Double> check(int n, String[] data){
        tmp = new ArrayList<Double>();
        int tmpn = 0;
        double tmpe = 0;

        for(Double[] a : vectors){
            for(double q : a){
                tmpe += Math.pow(q-Double.parseDouble(data[tmpn]), 2);
                tmpn++;
            }
            tmp.add(Math.pow(tmpe, 1/2));
            tmpe = 0;
            tmpn = 0;
        }

        Collections.sort(tmp);

        return tmp.subList(0, n);

    }
}
