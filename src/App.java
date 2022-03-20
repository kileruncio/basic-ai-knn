import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        int k = Integer.parseInt(args[0]);
        Scanner strain = new Scanner(new FileInputStream(args[1]));
        Scanner stest = new Scanner(new FileInputStream(args[2]));     
        ArrayList<String[]> train = new ArrayList<>(); 
        ArrayList<Line> types = new ArrayList<>();
        
        while(strain.hasNextLine()){
            train.add(strain.nextLine().split(","));
        }
             
    }
}
