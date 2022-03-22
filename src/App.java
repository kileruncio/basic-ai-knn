import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception{
        if(args.length > 0)
            Line.check(Integer.parseInt(args[0]), new Scanner(new FileInputStream(args[1])), args[2]);
        else{
            System.out.print("\nPodaj wektor do porownania, jesli chcesz zakonczyc wpisz -1: ");
            Scanner s = new Scanner(System.in);
            String in = s.next();
            System.out.println();
            while(Integer.parseInt(in) != -1){
                System.out.print("Podaj wartosc k: ");
                byHand(s.nextInt(), in);
                in = s.next();
            }
        }
    }

    public static void byHand(int k, String vector){
        System.out.println();
        String[] data = vector.split(",");
        List<Line> lines = new ArrayList<>();

        try{
            Scanner s = new Scanner(new FileInputStream("bin/train-set.csv"));
            double value = 0;

            while(s.hasNext()){
                String[] tmp = s.nextLine().split(",");

                for(int i = 0; i < tmp.length-1; i++)
                    value += Line.calculateDistance(Double.parseDouble(data[i]), Double.parseDouble(tmp[i]));
                lines.add(new Line(tmp[tmp.length-1], Math.sqrt(value)));
                value = 0;
            }

            lines.sort(Comparator.comparing(Line::getDistance));
            System.out.println("Ten obiekt nalezy do grupy: " + guessAnsqwer(k, lines));
            lines.clear();

            System.out.print("\nPodaj wektor do porownania, jesli chcesz zakonczyc wpisz -1: ");
        }catch(FileNotFoundException fnfe){
            System.out.println("Zbior testowy musi byc w pliku o nazwie train-set.csv i znajdowac sie obok pliku App.class");
        }
    }

    public static String guessAnsqwer(int k, List<Line> lines){
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
        return end;
    }
}
