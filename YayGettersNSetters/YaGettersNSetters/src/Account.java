public class Account {
    String name;
    int age;

    public static void main(String[] args) throws Exception {
        Account a = new Account();


        a.printDetail();
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }


    public void printDetail() {
        System.out.print(name + ", " + age);
    }
}