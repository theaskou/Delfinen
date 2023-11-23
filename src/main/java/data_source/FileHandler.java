package data_source;

import domain_model.Member;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private final File file = new File("memberData.csv");

    public ArrayList<Member> loadMemberData(){
        ArrayList<Member> loadData = new ArrayList<>();
        Scanner sc;
        try {
            sc = new Scanner(file, StandardCharsets.ISO_8859_1);
           // sc.nextLine();
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

    public void saveMemberData(ArrayList<Member> membersData, File file) {

        try (PrintStream output = new PrintStream(file)) {
            for (Member member : membersData) {
                output.println(
                        + member.getMemberID() + ";"
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
}
