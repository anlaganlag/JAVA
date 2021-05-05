import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CurrentTime {
    public static void main(String[] args) {

        Date currentDate = new Date();
        System.out.println(currentDate);

        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println(dateFormat.format(currentDate));
        System.out.println(timeFormat.format(currentDate));
    }

}
