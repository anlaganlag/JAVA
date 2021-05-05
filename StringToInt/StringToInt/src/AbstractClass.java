abstract class Dog {
    String breed;

    public void bark() {
        System.out.println("Bark");
    }

    public abstract void poop();
}

class chihuahua extends Dog {
    public void poop (){
        System.out.println("Dog pooped!");
    }

}

public class AbstractClass {
    public static void main(String[] args) {
        chihuahua d = new chihuahua();
        d.bark();
        d.poop();
    }

}
