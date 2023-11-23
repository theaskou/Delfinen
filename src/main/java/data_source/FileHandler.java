package data_source;

import domain_model.Member;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    public ArrayList<Member> loadMemberData(File CSVPath){
        ArrayList<Member> members = new ArrayList<>();
        Scanner sc;
        try {
            sc = new Scanner(CSVPath, StandardCharsets.ISO_8859_1);
            sc.nextLine();
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
                    (Integer.parseInt(attributes[2])),
                    (Integer.parseInt(attributes[3])),
                    (Integer.parseInt(attributes[4])),
                    attributes[5],
                    attributes[6],
                    (Boolean.parseBoolean(attributes[7])),
                    (Boolean.parseBoolean(attributes[8]))
            );
            members.add(member);
        }
        sc.close();
        return members;
    }

    public void saveListOfMemberData(ArrayList<Member> members, File file){
        try(PrintStream output = new PrintStream(file)) {
            for (Member member: members) {
                output.println(member.getMemberID() + ";" +
                        member.getName() + ";" +
                        member.getBirthday() + ";" +
                        member.getAddress() + ";" +
                        member.getEmail() + ";" +
                        member.isOnCompetitionTeam() + ";" +
                        member.isActive());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
