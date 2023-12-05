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

        System.out.println("VELKOMMEN TIL DELFINEN" + "\n" +
                "1. Forperson" + "\n" +
                "2. Træner" + "\n" +
                "3. Kassér");

        int userChoice = intInputHandler();

        if (userChoice == 1) {
            //Forpersons menu
            do {
                System.out.println("Forpersons valgmuligheder: " + "\n" +
                        "1. Opret medlem\n" +
                        "2. Se liste af medlemmer\n" +
                        "9. Afslut");

                userChoice =   intInputHandler();

                switch (userChoice) {
                    case 1:
                        createMember();
                        break;
                    case 2:
                        controller.printMembers();
                        break;

                    case 9:
                        System.out.println("Tak for nu!");
                        break;

                    default: System.out.println("Forkert input.");
                }

            } while (userChoice != 9);
            goodbyeDolphin();

        } else if (userChoice == 2) {
            //Trænerens menu
            do {
                System.out.println("Trænerens valgmuligheder: " + "\n" +
                        "1. Se top 5 lister" + "\n" +
                        "2. Registrer resultater" + "\n" +
                        "3. Registrer stævne resultater" + "\n" +
                        "9. Afslut");

                userChoice =   intInputHandler();

                switch (userChoice) {
                    case 1:
                        printTop5Results();
                        break;
                    case 2:
                        addResult();
                        break;
                    case 3:
                        // Registrer stævne resultater
                        break;
                    case 9:
                        System.out.println("Tak for nu!");
                        break;
                    default: System.out.println("Forkert input.");
                }

            } while (userChoice != 9);
            goodbyeDolphin();

        } else if (userChoice == 3) {
            //Kasserens menu
            do {
                System.out.println("Kasserens valgmuligheder: " + "\n" +
                        "1. Forventet samlet kontingent for nuværende år" + "\n" +
                        "2. Medlemmer i restance" + "\n" +
                        "9. Afslut");

                userChoice =   intInputHandler();

                switch (userChoice) {
                    case 1:
                        //Forventet samlet kontingent for nuværende år
                        System.out.println("Forventet samlet kontingentindkomst for indeværende år: " + controller.totalSubsription() + " kr.");
                        break;
                    case 2:
                        //Restance liste
                        printRestanceList();
                        break;
                    case 9:
                        System.out.println("Tak for nu!");
                        break;
                    default: System.out.println("Forkert input.");
                }

            } while (userChoice != 9);
            goodbyeDolphin();
        }
    }


    //Hjælpe metoder under her
    public void createMember() {

        int medlemsID = controller.membersList().size() + 1001;

        System.out.println("Indtast navn: ");
        keyboard.nextLine();
        String name = keyboard.nextLine();


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


        System.out.println("Er vedkommende på konkurrenceholdet? j/n: ");
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
        boolean isActiveMember = true;
        while (true) {
            char isActive = keyboard.next().charAt(0);
            switch (isActive) {
                case 'a':
                    break;
                case 'p':
                    isActiveMember = false;
                    break;
                default:
                    System.out.println("Forkert input.");
                    continue;
            }
            break;
        }

        LocalDate subscriptionDate = LocalDate.now();

        controller.createMember(medlemsID, name, birthday, address, email, isCompetitionMember, isActiveMember,subscriptionDate);

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

        int disciplin = intInputHandler();

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
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dato = null;
        while (dato == null) {
            System.out.println("Indtast datoen tiden blev sat i formattet: dd-mm-yyyy: ");
            String datoInput = keyboard.nextLine();
            try {
                dato = LocalDate.parse(datoInput, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Forkert indtastning, prøv igen");
            }
        }

        ResultCompareMessage resultCompareMessage = controller.createResult(chosenMember, chosenDisciplin, resultat, dato);

        switch (resultCompareMessage){
            case NEW_BEST_RESULT -> System.out.println("Tiden er opdateret");
            case NOT_BEST_RESULT -> System.out.println("Det er desværre ikke den bedste tid");
            case NOT_FOUND -> System.out.println("Hvad skal vi skrive her? test test test");
        }

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
                    5. Tilbage""");
            disciplin =   intInputHandler();

            switch (disciplin) {
                case 1:
                    //Rygcrawl
                    System.out.println("Top 5 på junior-holdet rygcrawl: \n" + "\u2500".repeat(83));
                    ArrayList<Result> juniorList = controller.juniorTeamFilter();
                    ArrayList<Result> backStrokeJuniorResults = controller.backStrokeResultFilter(juniorList);
                    Collections.sort(backStrokeJuniorResults, new BackcrawlComparator());
                    for (int i = 0; i < backStrokeJuniorResults.size() && i <= 4; i++){
                        System.out.println(backStrokeJuniorResults.get(i));
                    }

                    System.out.println("\nTop 5 på senior-holdet rygcrawl: \n" + "\u2500".repeat(83));
                    ArrayList<Result> seniorList = controller.seniorTeamFilter();
                    ArrayList<Result> backStrokeSeniorResults = controller.backStrokeResultFilter(seniorList);
                    Collections.sort(backStrokeSeniorResults, new BackcrawlComparator());
                    for (int i = 0; i < backStrokeSeniorResults.size() && i <= 4; i++){
                        System.out.println(backStrokeSeniorResults.get(i));
                    }
                    break;
                case 2:
                    //Crawl
                    System.out.println("Top 5 på junior-holdet crawl:\n" + "\u2500".repeat(83));
                    juniorList = controller.juniorTeamFilter();
                    ArrayList<Result> crawlJuniorResults = controller.crawlResultsFilter(juniorList);
                    Collections.sort(crawlJuniorResults, new CrawlComparator());
                    for (int i = 0; i < crawlJuniorResults.size() && i <= 4; i++) {
                        System.out.println(crawlJuniorResults.get(i));
                    }
                    System.out.println("\n 5 på senior-holdet for crawl:\n" + "\u2500".repeat(83));
                    seniorList = controller.seniorTeamFilter();
                    ArrayList<Result> crawlSeniorResults = controller.crawlResultsFilter(seniorList);
                    Collections.sort(crawlSeniorResults, new CrawlComparator());
                    for (int i = 0; i < crawlSeniorResults.size() && i <= 4; i++) {
                        System.out.println(crawlSeniorResults.get(i));
                    }
                    break;
                case 3:
                    //Butterfly
                    ArrayList<Result> bestResultForEachSwimmer = new ArrayList<>();
                    System.out.println("Top 5 på junior-holdet for butterfly:\n" + "\u2500".repeat(83));
                    juniorList = controller.juniorTeamFilter();
                    ArrayList<Result> butterflyJuniorResult = controller.butterflyResultFilter(juniorList);
                    Collections.sort(butterflyJuniorResult, new ButterflyComparator());
                    for (Result junior: butterflyJuniorResult) {
                        if(!bestResultForEachSwimmer.contains(junior.getMemberID())) {
                            bestResultForEachSwimmer.add(junior);
                        }

                    }

                    for (int i = 0; i < bestResultForEachSwimmer.size() && i <= 4; i++) {
                        System.out.println(bestResultForEachSwimmer.get(i));
                    }
                    System.out.println("\nTop 5 på senior-holdet for butterfly:\n" + "\u2500".repeat(83));
                    seniorList = controller.seniorTeamFilter();
                    ArrayList<Result> butterflySeniorResult = controller.butterflyResultFilter(seniorList);
                    Collections.sort(butterflySeniorResult, new ButterflyComparator());
                    for (int i = 0; i < butterflySeniorResult.size() && i <= 4; i++) {
                        System.out.println(butterflySeniorResult.get(i));
                    }
                    break;
                case 4:
                    //Brystsvømning
                    System.out.println("Top 5 på junior-holdet: brystsvømning\n" + "\u2500".repeat(83));
                    juniorList = controller.juniorTeamFilter();
                    ArrayList<Result> breaststrokeJuniorResult = controller.breaststrokeResultFilter(juniorList);
                    Collections.sort(breaststrokeJuniorResult, new BreaststrokeComparator());
                    for (int i = 0; i < breaststrokeJuniorResult.size() && i <= 4; i++){
                        System.out.println(breaststrokeJuniorResult.get(i));
                    }
                    System.out.println("\n" + "Top 5 på senior-holdet for brystsvømning:\n" + "\u2500".repeat(83));
                    seniorList = controller.seniorTeamFilter();
                    ArrayList<Result> breaststrokeSeniorResult = controller.breaststrokeResultFilter(seniorList);
                    Collections.sort(breaststrokeSeniorResult, new BreaststrokeComparator());
                    for (int i = 0; i < breaststrokeSeniorResult.size() && i <= 4; i++){
                        System.out.println(breaststrokeSeniorResult.get(i));
                    }

                case 5:
                    break;
                default: System.out.println("Forkert input.");
            }
        } while (disciplin != 5);
    }

    public void printRestanceList(){
        ArrayList<Member> restanceList = controller.restanceList();
        for (Member member : restanceList){
            System.out.println(member);
        }
    }


    public int intInputHandler() {
        while (!keyboard.hasNextInt()) {
            String text = keyboard.next();
            System.out.println("'" + text + "'" + " er ikke et tal. Prøv igen!");
            keyboard.nextLine();
        }
        int userChoice = keyboard.nextInt();
        return userChoice;
    }

    public static String flipDateFormater(String input){
        String[] split = input.split("-");
        return split[2] + "-" + split[1] + "-" + split[0];
    }

    public void goodbyeDolphin(){
        System.out.println(" .-*-..-*-..-*-..-*-..-*-..-*-..-*-..-*-..-*-..-*-..-*-.");
        System.out.println("|---------------------[ PÅ GENSYN ]--------------------|");
        System.out.println("|                                                      |");
        System.out.println("|                                ..                    |");
        System.out.println("|                            ,oaAY                     |");
        System.out.println("|                         ,d8888Y                      |");
        System.out.println("|                      ,d8888888Ao,                    |");
        System.out.println("|                ,ad8888888888888888bao,               |");
        System.out.println("|            ,d88888888888888888888888888b,            |");
        System.out.println("|         ,d8888888888888888888888888888888b,          |");
        System.out.println("|       ,888888888888888888888888888888888888b         |");
        System.out.println("|      d88888888888888888888888888888888888888b        |");
        System.out.println("|    ,888888888888/'                   `8888888b       |");
        System.out.println("|    l8888888888aaaadY                   `888888       |");
        System.out.println("|   888888V/ `98888'                      *8888p       |");
        System.out.println("|   8888Y'                                a888888b     |");
        System.out.println("|   d8Y''                                d8Y/' `*YA    |");
        System.out.println("|   *'                                   9Y'     `Y    |");
        System.out.println("|                                                      |");
        System.out.println("|                                                      |");
        System.out.println(" '-.-''-.-''-.-''-.-''-.-''-.-''-.-''-.-''-.-''-.-''-.-");
    }

}



