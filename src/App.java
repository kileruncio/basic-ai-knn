import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception{
        int correct = 0;
        int all = 0;
        HashMap<String, List<Double>> tmpl = new HashMap<String, List<Double>>();
        int k = Integer.parseInt(args[0]);
        Scanner strain = new Scanner(new FileInputStream(args[1]));
        Scanner stest = new Scanner(new FileInputStream(args[2]));     
        ArrayList<String[]> tmpa = new ArrayList<>(); 
        HashMap<String, Line> types = new HashMap<String, Line>();

        while(strain.hasNextLine()){
            String[] tmp = strain.nextLine().split(",");

            if(types.containsKey(tmp[tmp.length-1]))
                types.get(tmp[tmp.length-1]).add(tmp);
            else{
                types.put(tmp[tmp.length-1], new Line(tmp[tmp.length-1]));
                types.get(tmp[tmp.length-1]).add(tmp);
            }
        }
             
        while(stest.hasNextLine()){
            all++;
            String[] tmp = stest.nextLine().split(",");

            types.forEach((s, u) -> tmpl.put(s, u.check(k, tmp)));
            
            // tmpl.so
        }
    }

    // public static void 
}
