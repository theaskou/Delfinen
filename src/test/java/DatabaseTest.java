import domain_model.Database;
import domain_model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serial;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseTest {
    Database db;
    File testfile;
    Scanner sc;

    @BeforeEach
    void setUp() {

        String basePath = System.getProperty("user.dir") + "/src/test/java/";
        memberTestFile = new File (basePath + "testMember.csv");

        Member m1 = new Member(2122,"Nikolaj", LocalDate.of(2012,1,24), "Aalborg", "NikoP@yahoo.com", true, true,LocalDate.of(2023,12,4));
        Member m2 = new Member(3132,"Marie", LocalDate.of(1965,4,30), "Düsseldorf", "Marie@yahoo.com", true, false,LocalDate.of(2023,12,4));
        Member m3 = new Member(4142,"Thea", LocalDate.of(1993,11,26), "Rio De Janiro", "Thea@yahoo.com", false, true,LocalDate.of(2023,12,4));
        Member m4 = new Member(5152,"Usman",LocalDate.of(2022,2,15), "Papa Ny Guinea", "Usman@yahoo.com", false, false,LocalDate.of(2023,12,4));

        testMembers.add(m1);
        testMembers.add(m2);
        testMembers.add(m3);
        testMembers.add(m4);

        try (PrintStream out = new PrintStream(testfile)){
            for (Member member: testMembers) {
                out.println(member.getMemberID() + ";" +
                        member.getName() + ";" +
                        member.getBirthday() + ";" +
                        member.getAddress() + ";" +
                        member.getEmail() + ";" +
                        member.isOnCompetitionTeam() + ";" +
                        member.isActive());
            }
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
        //db = new Database(testfile);
    }
    @Test
    void printYouthTeam(){
        ArrayList<Member> testMembers = new ArrayList<>();
        for (Member member: testMembers){
            if (LocalDate.now().minusYears(18).isBefore(member.getBirthday()) && member.isOnCompetitionTeam() == true){
                testMembers.add(member);
            }
        }
    }

    @Test
    void printSeniorTeam(){
        ArrayList<Member> testMembers = new ArrayList<>();
        for (Member member : testMembers) {
          if (LocalDate.now().minusYears(18).isAfter(member.getBirthday()) && member.isOnCompetitionTeam() == true);
        }
    }

    @Test
    void totalSubscription(){
        ArrayList<Member> testMembers = new ArrayList<>();
        int subscription = 0;
        int juniorSub = 1000;
        int adultSub = 1600;
        int elderSub = 1200;
        for (Member testerMember : testMembers){
            if (LocalDate.now().minusYears(18).isBefore(testerMember.getBirthday()) && testerMember.isActive() == true){
                subscription += juniorSub;
            }
            if (LocalDate.now().minusYears(18).isAfter(testerMember.getBirthday()) && LocalDate.now().minusYears(60).isBefore(testerMember.getBirthday()) && testerMember.isActive() == true) {
                subscription += adultSub;
            }
            if (LocalDate.now().minusYears(60).isAfter(testerMember.getBirthday()) && testerMember.isActive() == true){
                subscription +=elderSub;
            }
            if (testerMember.isActive() == false){
                subscription +=500;
            }
        }
    }






}
