package data_source;

import domain_model.Member;
import domain_model.Resultat;
import domain_model.Svømmediscipliner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    // private final File file = new File("memberData.csv");
    // private String CSVPath;
    // private String CSVPathResultData;

    public ArrayList<Member> loadMemberData(String CSVPath) {
        ArrayList<Member> loadData = new ArrayList<>();
        Scanner sc;
        File file = new File(CSVPath);
        try {
            sc = new Scanner(file, StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Member member = null;
        while (sc.hasNext()) {
            String linje = sc.nextLine();
            String[] attributes = linje.split(";");
            member = new Member(
                    (Integer.parseInt(attributes[0])),
                    attributes[1],
                    (LocalDate.parse(attributes[2])),
                    attributes[3],
                    attributes[4],
                    (Boolean.parseBoolean(attributes[5])),
                    (Boolean.parseBoolean(attributes[6]))
            );
            loadData.add(member);
        }


        sc.close();
        return loadData;
    }

    public void saveMemberData(ArrayList<Member> membersData, String CSVPath) {
        File file = new File(CSVPath);
        try (PrintStream output = new PrintStream(file)) {
            for (Member member : membersData) {
                output.println(
                        +member.getMemberID() + ";"
                                + member.getName() + ";"
                                + member.getBirthday() + ";"
                                + member.getAddress() + ";"
                                + member.getEmail() + ";"
                                + member.isOnCompetitionTeam() + ";"
                                + member.isActive()
                );
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Resultat> loadResultData(String CSVPathResultData){
        ArrayList<Resultat> loadResultData = new ArrayList<>();
        Scanner sc;
        File file = new File(CSVPathResultData);
        try {
            sc = new Scanner(file, StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Resultat resultat = null;
        while (sc.hasNext()) {
            String linje = sc.nextLine();
            String[] attributes = linje.split(";");
            resultat = new Resultat(
                    (Integer.parseInt(attributes[0])),
                    attributes[1],
                    LocalDate.parse(attributes[2]),
                    Svømmediscipliner.valueOf(attributes[3]),
                    (Double.parseDouble(attributes[4])),
                    attributes[5],
                    (Integer.parseInt(attributes[6])),
                    LocalDate.parse(attributes[7])
                    );

        loadResultData.add(resultat);
        }
        sc.close();
        return loadResultData;
}

    public void saveResultatData(ArrayList<Resultat> resultatData, String CSVPathResultData) {
        File file = new File(CSVPathResultData);
        try (PrintStream output = new PrintStream(file)) {
            for (Resultat resultat : resultatData) {
                output.println(
                        +resultat.getMemberID() + ";"
                                + resultat.getName() + ";"
                                + resultat.getBirthday() + ";"
                                + resultat.getSvømmediscipliner() + ";"
                                + resultat.getBestTime() + ";"
                                + resultat.getCompetition() + ";"
                                + resultat.getRank() + ";"
                                + resultat.getDate()
                );
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



}
