import domain_model.Controller;
import domain_model.Member;
import user_interface.UserInterface;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        UserInterface ui = new UserInterface(controller);
        ui.startProgram();

    }
}
