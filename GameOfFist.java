import java.util.Scanner;

public class GameOfFist {
    public static void main(String[] args) {
        System.out.println("我们来玩石头剪刀布吧！！！请选择您要出的(请输入数字)");
        System.out.println(" 1=石头");
        System.out.println(" 2=剪子");
        System.out.println(" 3=布");

        // 用户输入:
        String pick = "";
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入您的选择：");
        pick = scanner.nextLine();
        // 计算机随机数 1, 2, 3:
        int randomN = (int) (Math.random() * 3) + 1;
        String combine = pick + Integer.toString(randomN);


        switch (combine) {
            case "11":
                System.out.println("石头vs石头,打平了!");
                break;
            case "12":
                System.out.println("石头vs剪刀,赢啦!");
                break;
            case "13":
                System.out.println("石头vs布,唉,输了!");
                break;
            case "21":
                System.out.println("剪刀vs石头,唉,输了!");
                break;
            case "22":
                System.out.println("剪刀vs剪刀,打平了!");
                break;
            case "23":
                System.out.println("剪刀vs布,赢啦!");
                break;
            case "31":
                System.out.println("布vs石头,赢啦!");
                break;
            case "32":
                System.out.println("布vs剪刀,唉,输了!");
                break;
            case "33":
                System.out.println("布vs布,打平了!");
                break;

            default:
                System.out.println("不要乱写数字啊，兄弟！！");
                break;
        }
    }
}