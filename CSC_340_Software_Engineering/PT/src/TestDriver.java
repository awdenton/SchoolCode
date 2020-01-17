
import java.io.IOException;

public class TestDriver{
    
    public static void main(String[] args) throws IOException {
        ExerManage manager = new ExerManage();
        System.out.println(manager.exercises.size());
    }
}