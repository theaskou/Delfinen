package data_source;

import domain_model.Member;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

//public class FileHandler {
//    private final File file = new File("memberData.csv");
//
//    public ArrayList<Member> loadMemberData(){
//        ArrayList<Member> loadData = new ArrayList<>();
//        Scanner sc;
//        try {
//            sc = new Scanner(file, StandardCharsets.ISO_8859_1);
//            sc.nextLine();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e){
//            throw new RuntimeException(e);
//        } Member member = null;
//
//        while (sc.hasNext()) {
//            String linje = sc.nextLine();
//            String[] attributes = linje.split(";");
//            member = new Member(
//                    (Integer.parseInt(attributes[0])),
//                    attributes[1],
//                    (Integer.parseInt(attributes[2])),
//                    (Integer.parseInt(attributes[3])),
//                    (Integer.parseInt(attributes[4])),
//                    attributes[5],
//                    attributes[6],
//                    (Boolean.parseBoolean(attributes[7])),
//                    (Boolean.parseBoolean(attributes[8]))
//            );
//            loadData.add(member);
//        }
//        sc.close();
//        return loadData;
//    }

//    public void saveMemberData(ArrayList<Member> membersData) throws FileNotFoundException {
//        PrintStream output = new PrintStream(new File())
//    }
}
