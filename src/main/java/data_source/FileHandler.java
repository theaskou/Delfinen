package data_source;

import domain_model.Member;
import domain_model.Result;
import domain_model.SwimmingDiscipline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

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
            String line = sc.nextLine();
            String[] attributes = line.split(";");
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


    public ArrayList<Result> loadResultData(String CSVPathResultData){
        ArrayList<Result> loadResultData = new ArrayList<>();
        Scanner sc;
        File file = new File(CSVPathResultData);
        try {
            sc = new Scanner(file, StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Result resultat = null;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] attributes = line.split(";");
            resultat = new Result(
                    (Integer.parseInt(attributes[0])),
                    attributes[1],
                    LocalDate.parse(attributes[2]),
                    SwimmingDiscipline.valueOf(attributes[3]),
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

    public void saveResultatData(ArrayList<Result> resultatData, String CSVPathResultData) {
        File file = new File(CSVPathResultData);
        try (PrintStream output = new PrintStream(file)) {
            for (Result resultat : resultatData) {
                output.println(
                        +resultat.getMemberID() + ";"
                                + resultat.getName() + ";"
                                + resultat.getBirthday() + ";"
                                + resultat.getSwimmingDiscipline() + ";"
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
