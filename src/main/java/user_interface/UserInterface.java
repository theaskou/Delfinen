package user_interface;

import domain_model.*;
import domain_model.comparator.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UserInterface {
    private final Controller controller;

    public UserInterface(Controller controller) {
        this.controller = controller;
    }

    Scanner keyboard = new Scanner(System.in);

    public void startProgram() {
        int userChoice = 0;
        System.out.println("VELKOMMEN TIL DELFINEN" + "\n" +
                "1. Forperson" + "\n" +
                "2. Træner" + "\n" +
                "3. Kassér");


        while (!keyboard.hasNextInt()) {
            wrongInputHandler();
        }
        userChoice = keyboard.nextInt();
        keyboard.nextLine();

        if (userChoice == 1) {
            //Forpersons menu
            do {
                System.out.println("Forpersons valgmuligheder: " + "\n" +
                        "1. Opret medlem\n" +
                        "2. Se liste af medlemmer\n" +
                   //     "3. Print ungdomshold" + "\n" +
                        "9. Afslut");

                userChoice =   intInputHandler();

                switch (userChoice) {
                    case 1:
                        createMember();
                        break;
                    case 2:
                        controller.printMembers();
                        break;
                    case 3:
                        System.out.println(controller.youthTeam());
                        break;
                    case 9:
                        System.out.println("Tak for nu!");
                        break;

                    default: System.out.println("Forkert input.");
                }

            } while (userChoice != 9);
        } else if (userChoice == 2) {
            //Trænerens menu
            do {
                System.out.println("Trænerens valgmuligheder: " + "\n" +
                        "1. Se top 5 lister" + "\n" +
                        "2. Registrer resultater" + "\n" +
                        "9. Afslut" + "\n");
                while (!keyboard.hasNextInt()) {
                    wrongInputHandler();
                }
                userChoice = keyboard.nextInt();
                keyboard.nextLine();

                switch (userChoice) {
                    case 1:
                        // Top 5 lister, først efter hold (junior/senior), og herefter i hver disciplin
                        //28/11 Har ikke taget højde for junior senior, kun lavet for butterfly
                        printTop5Results();
                        break;
                    case 2:
                        addResult();
                        break;

                    case 9:
                        System.out.println("Tak for nu!");
                        break;
                    default: System.out.println("Forkert input.");
                }

            } while (userChoice != 9);

        } else if (userChoice == 3) {
            //Kasserens menu
            do {
                System.out.println("Kasserens valgmuligheder: " + "\n" +
                        "1. Forventet samlet kontingent for nuværende år" + "\n" +
                        "2. Medlemmer i restance" + "\n" +
                        "9. Afslut" + "\n");

                while (!keyboard.hasNextInt()) {
                    wrongInputHandler();
                }
                userChoice = keyboard.nextInt();
                keyboard.nextLine();

                switch (userChoice) {
                    case 1:
                        //Forventet samlet kontingent for nuværende årc
                        System.out.println("Forventet samlet kontingentindkomst for indeværende år: " + controller.totalSubsription() + " kr.");
                        break;
                   // case 2:
                        //Liste af medlemmer i restance
                  //      break;

                }

            } while (userChoice != 9);

        }
    }

    public void createMember() {

        int medlemsID = controller.membersList().size() + 1001;

        System.out.println("Indtast navn: ");
        String name = keyboard.nextLine();

        //TODO Crash protection her

     // LocalDate birthday = LocalDate.parse(flipDateFormater(birthdayInput));
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthday = null;

        while (birthday == null) {
            System.out.println("Indtast fødselsdag i formatet dd-mm-yyyy: ");
            String birthdayInput = keyboard.nextLine();
            try {
                birthday = LocalDate.parse(birthdayInput, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Forkert indtastning, prøv igen");
            }
        }

        System.out.println("Indtast adresse: ");
        String address = keyboard.nextLine();

        System.out.println("Indtast email: ");
        String email = keyboard.nextLine();


        System.out.println("Er vedkommende på konkurrenceholdet? j/n: "); //TODO loop igennem ved forkert input
        boolean isCompetitionMember = true;
        while (true) {
            char competetorInput = keyboard.next().charAt(0);

            switch (competetorInput) {
                case 'j':
                    break;
                case 'n': isCompetitionMember = false;
                break;
                default:
                    System.out.println("Forkert input.");
                continue;
            }
            break;
        }

        System.out.println("Er vedkommende [a] aktiv eller [p] passiv medlem?: ");
        char isActive = keyboard.next().charAt(0);
        boolean isActiveMember = true;
        while (true){
            char isActive = keyboard.next().charAt(0);
        switch (isActive) {
            case 'a' -> {
            }
            case 'p' -> isActiveMember = false;
            default -> System.out.println("Forkert input.");

        }
        controller.createMember(medlemsID, name, birthday, address, email, isCompetitionMember, isActiveMember);

    }

    public void addResult() {
        //TODO save til en fil
        ArrayList<Member> competitionMembers = controller.getCompetetionMember();
        int count = 1;
        for (Member competitionMember : competitionMembers) {
            System.out.println(count++ + " , ID: " + competitionMember.getMemberID() + " ,Navn: " + competitionMember.getName());
        }
        System.out.println("Hvilket medlem vil du give et resultat?");
        int memberChoice = keyboard.nextInt();
        keyboard.nextLine();
        Member chosenMember = competitionMembers.get(memberChoice - 1);
        //Skal vi spørge her om det er til stævne vs. træning? Eller skal træneren have en seperat menupunkt til stævne?
        System.out.println("""
                Vælg disciplin:
                1. Rygcrawl
                2. Crawl
                3. Butterfly
                4. Brystsvømning""");
        int disciplin = keyboard.nextInt();
        keyboard.nextLine();
        SwimmingDiscipline chosenDisciplin = null;
        switch (disciplin){
            case 1:
                chosenDisciplin = SwimmingDiscipline.BACKSTROKE;
                break;
            case 2:
                chosenDisciplin = SwimmingDiscipline.CRAWL;
                break;
            case 3:
                chosenDisciplin = SwimmingDiscipline.BUTTERFLY;
                break;
            case 4:
                chosenDisciplin = SwimmingDiscipline.BREASTSTROKE;
                break;
            default: System.out.println("Forkert input.");
        }
        System.out.println("Indskriv resultat i sekunder");
        double resultat = keyboard.nextDouble();
        keyboard.nextLine();
        System.out.println("Hvilken dato blev tiden sat?");
        String datoInput = keyboard.nextLine();
        LocalDate dato = LocalDate.parse(datoInput);


        controller.createResult(chosenMember.getMemberID(), chosenMember.getName(), chosenMember.getBirthday(), chosenDisciplin, resultat, dato);
        System.out.println("Tiden er registreret!");

    }

    public void printTop5Results(){
        int disciplin;
        do {
            System.out.println("""
                    Vælg disciplin for Top 5 resultater:
                    1. Rygcrawl
                    2. Crawl
                    3. Butterfly
                    4. Brystsvømning
                    5. Afslut""");
            disciplin = keyboard.nextInt();
            keyboard.nextLine();

            switch (disciplin) {
                case 1:
                    //Rygcrawl
                    System.out.println("Top 5 på junior-holdet:");

                    ArrayList<Result> juniorList = controller.juniorTeamFilter();
                    ArrayList<Result> backStrokeJuniorResults = controller.juniorBackStrokeResultFilter(juniorList);

                    Collections.sort(backStrokeJuniorResults, new BackcrawlComparator());
                    for (int i = 0; i < backStrokeJuniorResults.size() && i <= 4; i++){
                        System.out.println(backStrokeJuniorResults.get(i));
                    }

                    System.out.println("Top 5 på senior-holdet:");
                    ArrayList<Result> seniorList = controller.seniorTeamFilter();
                    ArrayList<Result> backStrokeSeniorResults = controller.seniorBackStrokeResult(seniorList);
                    Collections.sort(backStrokeSeniorResults, new BackcrawlComparator());
                    for (int i = 0; i < backStrokeSeniorResults.size() && i <= 4; i++){
                        System.out.println(backStrokeSeniorResults.get(i));
                    }
                    break;
                case 2:
                    //Crawl
                    System.out.println("Top 5 på junior-holdet:");
                    ArrayList<Result> crawlJuniorResults = controller.crawlJuniorResultsFilter();
                    Collections.sort(crawlJuniorResults, new CrawlComparator());
                    for (int i = 0; i < crawlJuniorResults.size() && i <= 4; i++) {
                        System.out.println(crawlJuniorResults.get(i));
                    }
                    System.out.println("Top 5 på senior-holdet:");
                    ArrayList<Result> crawlSeniorResults = controller.crawlSeniorResultsFilter();
                    Collections.sort(crawlSeniorResults, new CrawlComparator());
                    for (int i = 0; i < crawlSeniorResults.size() && i <= 4; i++) {
                        System.out.println(crawlSeniorResults.get(i));
                    }
                    break;
                case 3:
                    //Butterfly
                    ArrayList<Result> butterflyResult = controller.butterflyResultFilter();
                    Collections.sort(butterflyResult, new ButterflyComparator());
                    for (int i = 0; i <= 4; i++) {
                        System.out.println(butterflyResult.get(i));
                    }
                    break;
                case 4:
                    //Brystsvømning
                    ArrayList<Result> breaststrokeResult = controller.butterflyResultFilter();
                    Collections.sort(breaststrokeResult, new BreaststrokeComparator());
                    for (int i = 0; i <= 4; i++){
                        System.out.println(breaststrokeResult.get(i));
                    }
                    break;
                case 5:
                    keyboard.close();
            }
        } while (disciplin != 5);
    }

    public void wrongInputHandler() {
        String text = keyboard.next();
        System.out.println("'" + text + "'" + " er ikke et tal. Prøv igen!");
    }

    public static String flipDateFormater(String input){
        String[] split = input.split("-");
        return split[2] + "-" + split[1] + "-" + split[0];
    }
    //UI noter til marie :)
    //1. "9" afslut tilbage til hovedmenu (forperson osv.)

}



