import application.Application;

public class App {
    public static void main(String[] args) throws Exception {

        try {
            Application.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
