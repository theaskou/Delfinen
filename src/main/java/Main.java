import domain_model.Controller;
import domain_model.Member;
import user_interface.UserInterface;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        UserInterface ui = new UserInterface(controller);
        ui.startProgram();

        Member testmember = new Member(1, "Marie Mu", 1992, 12, 17, "Mesterstien 12, 3.", "frk.langballe@gmail.com", false, true);
        System.out.println(testmember);

    }
}
