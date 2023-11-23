package user_interface;

import domain_model.Controller;
import domain_model.Database;
import domain_model.Member;

import java.util.Scanner;

public class UserInterface {
    private final Controller controller;

    public UserInterface(Controller controller){
        this.controller = controller;
    }

    Scanner keyboard = new Scanner(System.in);

    public void startProgram(){
        int userChoice;
        System.out.println("VELKOMMEN TIL DELFINEN");
        do {
            System.out.println("Valgmuligheder: " + "\n" +
                                "1. Opret medlem" + "\n" +
                                "2. Se liste af medlemmer");
            while (!keyboard.hasNextInt()) {
                String text = keyboard.next();
                System.out.println("'" + text + "'" + " er ikke et tal. Prøv igen!");
            }
            userChoice = keyboard.nextInt();
            keyboard.nextLine();

        switch (userChoice){
            case 1:
            controller.createMember(1, "Marie Mu", 1992, 12, 17, "Mesterstien 12, 3.", "frk.langballe@gmail.com", false, true);
           //Skal vi lave metoder nedenunder
                break;
            case 2:
            controller.printMemberlist();
                break;

        }


        } while (userChoice != 2);

    }
}
