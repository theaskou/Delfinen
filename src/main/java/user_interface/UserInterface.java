package user_interface;

import domain_model.Controller;
import domain_model.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
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
            System.out.println("Valgmuligheder: " + "/n" +
                                "1. Opret medlem");
            while (!keyboard.hasNextInt()) {
                String text = keyboard.next();
                System.out.println("'" + text + "'" + " er ikke et tal. Prøv igen!");
            }
            userChoice = keyboard.nextInt();
            keyboard.nextLine();

        switch (userChoice){
            case 1:
           //Skal vi lave metoder nedenunder?

                System.out.println("Indtast navn: ");
                String name = keyboard.nextLine();

                System.out.println("Indtast fødselsdag i formatet yyyy-mm-dd: ");
                String birthdayInput = keyboard.nextLine();
                LocalDate birthday = LocalDate.parse(birthdayInput);

                System.out.println("Indtast adresse: ");
                String address = keyboard.nextLine();

                System.out.println("Indtast email: ");
                String email = keyboard.nextLine();

                System.out.println("Er vedkommende på konkurrenceholdet? j/n: ");
                char competetorInput = keyboard.next().charAt(0);
                boolean isCompetitionMember = true;
                switch (competetorInput) {
                    case 'j' -> {
                    }
                    case 'n' -> isCompetitionMember = false;
                    default -> System.out.println("Invalid input.");
                }

                System.out.println("Er vedkommende [a] aktiv eller [p] passiv medlem?: ");
                char isActive = keyboard.next().charAt(0);
                boolean isActiveMember = true;
                switch (isActive) {
                    case 'a' -> {
                    }
                    case 'p' -> isActiveMember = false;
                    default -> System.out.println("Invalid input.");

                }

                controller.createMember(medlemsID,name,birthday,address,email,isCompetitionMember,isActiveMember);

                System.out.println(controller.viewMembers());
        }


        } while (userChoice != 2);

    }

}
