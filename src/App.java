import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception{
        int correct = 0;
        int all = 0;
        ArrayList<Line> tmpl = new ArrayList<>(); 
        int k = Integer.parseInt(args[0]);
        Scanner strain = new Scanner(new FileInputStream(args[1]));
        Scanner stest = new Scanner(new FileInputStream(args[2]));     
        ArrayList<Line> end = new ArrayList<>();
        HashMap<String, Line> types = new HashMap<String, Line>();
        ArrayList<Counter> counter = new ArrayList<>();

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

            types.forEach((s, u) -> tmpl.add(new Line(s, u.check(k, tmp)))); //types ma klucze (nazwy klas) i obiekty z listami n najblizszych odleglosci
            
            tmpl.forEach(a -> {
                for(double d : a.getValues())
                    end.add(new Line(a.getName(), d));
            });

            Collections.sort(end, Comparator.comparing(Line::getDistance));
            
            List<Line> finish = end.subList(0, k);

            types.forEach((a, b) -> counter.add(new Counter(a)));

            for(Counter c : counter)
                for(Line l : finish)
                    if(c.getType() == l.getName())
                        c.addOne();
                
            Collections.sort(counter, Comparator.comparing(Counter::getNumber));

            if(counter.size() > 1 && counter.get(0).getNumber() <= counter.get(1).getNumber()){
                if(counter.get(0).getType().equals(tmp[tmp.length-1])){
                    ++correct;
                    System.out.println("Zgadnieto typ");
                }else{
                    System.out.println("Blad");
                }
            }else System.out.println("asihjdfisudf");

        }

        System.out.println("Skutecznosc to: " + correct + "/" + all);
    }
}
