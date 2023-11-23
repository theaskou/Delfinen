package user_interface;

import domain_model.Controller;

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

        }


        } while (userChoice != 2);

    }


}
