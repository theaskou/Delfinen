package user_interface;

import domain_model.*;
import domain_model.comparator.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

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
                        "3. Rediger medlem\n" +
                        "4. Slet medlem\n" +
                        "9. Afslut");

                userChoice = intInputHandler();

                switch (userChoice) {
                    case 1:
                        createMember();
                        break;
                    case 2:
                        controller.printMembers();
                        break;
                    case 3:
                        editMember();
                        break;
                    case 4:
                        deleteMember();
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
                        "3. Registrer stævne" + "\n" +
                        "4. Print stævneresultater" + "\n" +
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
                        addCompetition();
                        break;
                    case 4:
                        controller.printCompetitions();
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

        int medlemsID = giveUniqueID(controller.membersList());

        System.out.println("Indtast navn: ");
        keyboard.nextLine();
        String name = keyboard.nextLine().trim();

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
        String address = keyboard.nextLine().trim();

        String email;
        while(true){
            System.out.println("Indtast email: ");
            email = keyboard.nextLine();
            if (email.contains("@")){
                break;
            } else {
                System.out.println("Ugyldig email, indtast igen");
            }
        }

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

    public void deleteMember() {
        for (Member member : controller.membersList()) {
            System.out.println(member.getMemberID() + " " + member.getName());
        }
        System.out.println("Hvilket medlem vil du slette? Indtast medlemmets ID (4 cifre): ");
        int userChoice = intInputHandler();
        boolean isDeleted = controller.deleteMember(userChoice);
        if (isDeleted) {
            System.out.println("Medlem " + userChoice + " er slettet.");
        } else {
            System.out.println("Medlemsnummer eksisterer ikke.");
        }
    }

    public void addResult() {
        ArrayList<Member> competitionMembers = controller.getCompetetionMember();
        int count = 1;
        System.out.println("Hvilket medlem vil du give et resultat?");
        for (Member competitionMember : competitionMembers) {
            System.out.println(count++ + " , ID: " + competitionMember.getMemberID() + " ,Navn: " + competitionMember.getName());
        }

        int memberChoice = keyboard.nextInt();
        keyboard.nextLine();
        Member chosenMember = competitionMembers.get(memberChoice - 1);

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
            case FIRST_TIME_RESULT -> System.out.println("Tiden er indtastet");
        }
    }

    public void editMember(){
        ArrayList<Member> members = controller.membersList();
        Member memberToEdit;
        int count = 1;
        if (members.size() > 1) {
            System.out.println("Hvilket medlem ønsker du at redigere?");
            for (Member member : members) {
                System.out.println(count++ + " " +
                        member.getName());
            }
            int memberNumberOnList = keyboard.nextInt();
            keyboard.nextLine();
                memberToEdit = members.get(memberNumberOnList - 1);
        } else {
            memberToEdit = members.get(0);
        }
        if (!members.isEmpty()){
            System.out.println("Rediger information. Tryk enter for at fortsætte");
            String newValue;
            System.out.println("Navn: " + memberToEdit.getName());
            newValue = keyboard.nextLine();
            if (!newValue.isEmpty()){
                memberToEdit.setName(newValue);
            }

            System.out.println("Adresse: " + memberToEdit.getAddress());
            newValue = keyboard.nextLine();
            if (!newValue.isEmpty()){
                memberToEdit.setAddress(newValue);
            }

            System.out.println("Email: " + memberToEdit.getEmail());
            newValue = keyboard.nextLine();
            if (!newValue.isEmpty()){
                memberToEdit.setEmail(newValue);
            }

            System.out.println("Konkurrencesvømmer [j/n]: " + memberToEdit.isOnCompetitionTeam(memberToEdit.isOnCompetitionTeam()));
            newValue = keyboard.nextLine();
            if (newValue.equals("j")){
                memberToEdit.setOnCompetitionTeam(true);
            } else if (newValue.equals("n")) {
                memberToEdit.setOnCompetitionTeam(false);
            }

            System.out.println("Er aktiv [j/n]: " + memberToEdit.isActive(memberToEdit.isActive()));
            newValue = keyboard.nextLine();
            if (newValue.equals("j")){
                memberToEdit.setActive(true);

            } else if (newValue.equals("n")) {
                memberToEdit.setActive(false);
            }

            controller.saveMemberData();
            System.out.println(memberToEdit.getName() + " er opdateret");
        }
    }

    public void addCompetition(){
        ArrayList<Member> competitionMembers = controller.getCompetetionMember();
        int count = 1;
        System.out.println("Hvilket medlem vil du registrere et stævne på?");
        for (Member competitionMember : competitionMembers) {
            System.out.println(count++ + ", ID: " + competitionMember.getMemberID() + ", Navn: " + competitionMember.getName());
        }
        int memberChoice = keyboard.nextInt();
        keyboard.nextLine();
        Member chosenMember = competitionMembers.get(memberChoice - 1);

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
        System.out.println("Indtast navnet på stævnet: ");
        String competitionName = keyboard.nextLine();

        System.out.println("Indtast placering til stævnet: ");
        int rank = keyboard.nextInt();
        keyboard.nextLine();

        controller.createCompetitionResult(chosenMember, chosenDisciplin, resultat, competitionName, rank, dato);
        System.out.println("Stævnet er registreret!");
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
            disciplin = intInputHandler();

            switch (disciplin) {
                case 1:
                    //Rygcrawl
                    System.out.println("Top 5 på junior-holdet rygcrawl: \n" + "\u2500".repeat(83));
                    ArrayList<Result> juniorList = controller.juniorTeamFilter();
                    ArrayList<Result> backStrokeJuniorResults = controller.backStrokeResultFilter(juniorList);
                    Collections.sort(backStrokeJuniorResults, new BackcrawlComparator());
                    uniqueMemberID(backStrokeJuniorResults);

                    System.out.println("\nTop 5 på senior-holdet rygcrawl: \n" + "\u2500".repeat(83));
                    ArrayList<Result> seniorList = controller.seniorTeamFilter();
                    ArrayList<Result> backStrokeSeniorResults = controller.backStrokeResultFilter(seniorList);
                    Collections.sort(backStrokeSeniorResults, new BackcrawlComparator());
                    uniqueMemberID(backStrokeSeniorResults);
                    break;
                case 2:
                    //Crawl
                    System.out.println("Top 5 på junior-holdet crawl:\n" + "\u2500".repeat(83));
                    juniorList = controller.juniorTeamFilter();
                    ArrayList<Result> crawlJuniorResults = controller.crawlResultsFilter(juniorList);
                    Collections.sort(crawlJuniorResults, new CrawlComparator());
                    uniqueMemberID(crawlJuniorResults);

                    System.out.println("\n 5 på senior-holdet for crawl:\n" + "\u2500".repeat(83));
                    seniorList = controller.seniorTeamFilter();
                    ArrayList<Result> crawlSeniorResults = controller.crawlResultsFilter(seniorList);
                    Collections.sort(crawlSeniorResults, new CrawlComparator());
                    uniqueMemberID(crawlSeniorResults);
                    break;
                case 3:
                    //Butterfly
                    ArrayList<Result> bestResultForEachSwimmer = new ArrayList<>();
                    System.out.println("Top 5 på junior-holdet for butterfly:\n" + "\u2500".repeat(83));
                    juniorList = controller.juniorTeamFilter();
                    ArrayList<Result> butterflyJuniorResult = controller.butterflyResultFilter(juniorList);
                    Collections.sort(butterflyJuniorResult, new ButterflyComparator());
                    uniqueMemberID(butterflyJuniorResult);

                    System.out.println("\nTop 5 på senior-holdet for butterfly:\n" + "\u2500".repeat(83));
                    seniorList = controller.seniorTeamFilter();
                    ArrayList<Result> butterflySeniorResult = controller.butterflyResultFilter(seniorList);
                    Collections.sort(butterflySeniorResult, new ButterflyComparator());
                    uniqueMemberID(butterflySeniorResult);
                    break;
                case 4:
                    //Brystsvømning
                    System.out.println("Top 5 på junior-holdet: brystsvømning\n" + "\u2500".repeat(83));
                    juniorList = controller.juniorTeamFilter();
                    ArrayList<Result> breaststrokeJuniorResult = controller.breaststrokeResultFilter(juniorList);
                    Collections.sort(breaststrokeJuniorResult, new BreaststrokeComparator());
                    uniqueMemberID(breaststrokeJuniorResult);

                    System.out.println("\n" + "Top 5 på senior-holdet for brystsvømning:\n" + "\u2500".repeat(83));
                    seniorList = controller.seniorTeamFilter();
                    ArrayList<Result> breaststrokeSeniorResult = controller.breaststrokeResultFilter(seniorList);
                    Collections.sort(breaststrokeSeniorResult, new BreaststrokeComparator());
                    uniqueMemberID(breaststrokeSeniorResult);
                    break;

                case 5:
                    break;
                default: System.out.println("Forkert input.");
            }
        } while (disciplin != 5);
    }

    public void printRestanceList(){
        ArrayList<Member> restanceList = controller.restanceList();
        if (!restanceList.isEmpty()) {
            for (Member member : restanceList) {
                System.out.println(member);
            }
            } else {
                System.out.println("Ingen medlemmer i restance");
            }
    }
    public void uniqueMemberID(ArrayList<Result> filteredResultList){
        Set<Integer> uniqueMemberIDS = new HashSet<>();
        int count = 0;

        for (Result result : filteredResultList) {
            int memberID = result.getMemberID();
            if (uniqueMemberIDS.add(memberID)) {
                System.out.println(result);
                count++;
            }
            if (count == 5){
                break;
            }
        }
    }

    public int giveUniqueID (ArrayList <Member> members) {
        int uniqueID = 1000;
        Set<Integer> uniqueIDs = new HashSet<>();
        for (Member member : members) {
            uniqueID = member.getMemberID();
            if (uniqueIDs.add(uniqueID++)) {
                //uniqueID++;
            }
        }
        return uniqueID;
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



