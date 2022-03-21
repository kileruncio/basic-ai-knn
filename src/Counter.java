public class Counter {
    private String type;
    private int number;

    public Counter(String type){
        this.type = type;
        this.number = 0;
    }

    public String getType() {
        return type;
    }

    public void addOne(){
        this.number++;
    }

    public int getNumber() {
        return number;
    }
}
