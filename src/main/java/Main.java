import data_source.FileHandler;
import domain_model.Controller;
import domain_model.Database;
import domain_model.Member;
import user_interface.UserInterface;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        FileHandler fh = new FileHandler();
        UserInterface ui = new UserInterface(controller);
        ui.startProgram();

    }
}
