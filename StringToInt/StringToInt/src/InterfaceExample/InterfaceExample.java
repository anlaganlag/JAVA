package InterfaceExample;

interface WaterBottleInterface {

    String color = "Blue";

    void fillUp();
    void pourOut();
}

public class InterfaceExample implements WaterBottleInterface {
    public static void main(String[] args) {
        System.out.println(color);
        InterfaceExample ex = new InterfaceExample();
        ex.fillUp();
        ex.pourOut();
    }

    @Override
    public void fillUp() {
        System.out.println("满上!");
    }
    @Override
    public void pourOut(){
        System.out.println("清空");
    }
}