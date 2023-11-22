package data_source;

import domain_model.Member;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private final File file = new File("memberData.csv");

    public ArrayList<Member> loadMemberData(){
        ArrayList<Member> loadData = new ArrayList<>();
        Scanner sc;
        try {
            sc = new Scanner(file, StandardCharsets.ISO_8859_1);
            sc.nextLine();
        } catch (FileNotFoundException e)
    }

//    public void saveMemberData(ArrayList<Member> membersData) throws FileNotFoundException {
//        PrintStream output = new PrintStream(new File())
//    }
}
