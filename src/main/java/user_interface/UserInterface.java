package user_interface;

import domain_model.Controller;

import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class UserInterface {
    private final Controller controller;

    public UserInterface(Controller controller){
        this.controller = controller;
    }

    Scanner keyboard = new Scanner(System.in);

    public void startProgram() {
        int userChoice = 0;
        System.out.println("VELKOMMEN TIL DELFINEN"+ "\n" +
                            "1. Forperson" + "\n" +
                            "2. Træner" + "\n" +
                            "3. Kassér");


        while (!keyboard.hasNextInt()){
            wrongInputHandler();
        }
        userChoice = keyboard.nextInt();
        keyboard.nextLine();

        if (userChoice == 1) {
            //Forpersons menu
            do {
                System.out.println("Forpersons valgmuligheder: " + "\n" +
                        "1. Opret medlem" + "\n" +
                        "2. Se liste af medlemmer" + "\n" +
                        "3. Print ungdomshold" + "\n" +
                        "9. Afslut" + "\n");
                while (!keyboard.hasNextInt()){
                    wrongInputHandler();
                }
                userChoice = keyboard.nextInt();
                keyboard.nextLine();

                switch (userChoice) {
                    case 1:
                        createMember();
                        break;
                    case 2:
                        controller.printMembers();
                        break;
                    case 3:
                        System.out.println(controller.printYouthTeam());
                        break;
                }

            } while (userChoice != 9);
        } else if (userChoice == 2) {
            //Trænerens menu
            do {
                System.out.println("Trænerens valgmuligheder: " + "\n" +
                        "1. Se top 5 lister" + "\n" +
                        "2. Registrer resultater" + "\n" +
                        "9. Afslut" + "\n");
                while (!keyboard.hasNextInt()){
                    wrongInputHandler();
                }
                userChoice = keyboard.nextInt();
                keyboard.nextLine();

                switch (userChoice){
                    case 1:
                        // Top 5 lister, først efter hold (junior/senior), og herefter i hver disciplin
                        break;
                    case 2:
                        // Registrer resultater. Skal det inddeles i hhv. træningsresultater og stævneresultater?
                        break;
                }


            } while (userChoice != 9);

        } else if (userChoice == 3) {
            //Kasserens menu
            do {
                System.out.println("Kasserens valgmuligheder: " + "\n" +
                        "1. Forventet samlet kontingent for nuværende år" + "\n" +
                        "2. Medlemmer i restance" + "\n" +
                        "9. Afslut" + "\n");

                while (!keyboard.hasNextInt()){
                    wrongInputHandler();
                }
                userChoice = keyboard.nextInt();
                keyboard.nextLine();

                switch (userChoice) {
                    case 1:
                        //Forventet samlet kontingent for nuværende årc
                        System.out.println(controller.totalSubsription());
                        break;
                    case 2:
                        //Liste af medlemmer i restance
                        break;

                }
            } while (userChoice != 9);

        }
    }

        public void createMember(){
            keyboard.nextLine();

                    Random random = new Random();
                    int medlemsID = random.nextInt(9999) + 1;

                    System.out.println("Indtast navn: ");
                    String name = keyboard.nextLine();

                    //TODO Crash protection her
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
                            resgisterDisciplin(); //TODO: Er det kun træneren der skal kunne registrere disciplin?
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

                    controller.createMember(medlemsID, name, birthday, address, email, isCompetitionMember, isActiveMember);

        }

        public void resgisterDisciplin() {
        //TODO: Skal kunne registrere flere discipliner
            System.out.println("Hvilken disciplin skal svømmeren registreres i?" + "\n" +
                    "1. Butterfly" + "\n" +
                    "2. Crawl" + "\n" +
                    "3. Rygcrawl" + "\n" +
                    "4. Brystsvømning");
            int disciplinInput = keyboard.nextInt();
            switch (disciplinInput) {
                case 1:
                    //TODO: set disciplin til butterfly
                    break;
                case 2:
                    //TODO: set disciplin til crawl
                    break;
                case 3:
                    //TODO: set disciplin til Rygcrawl
                    break;
                case 4:
                    //TODO: set disciplin til Brystsvømning
                    break;
                default:
                    System.out.println("invalid input");
            }
        }

        public void wrongInputHandler(){
                String text = keyboard.next();
                System.out.println("'" + text + "'" + " er ikke et tal. Prøv igen!");
        }
        //UI noter til marie :)
        //1. "9" afslut tilbage til hovedmenu (forperson osv.)

}



