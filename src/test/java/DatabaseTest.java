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
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseTest {
    Database db;
    File testfile;
    Scanner sc;

    @BeforeEach
    void setUp() {
        testfile = new File ("src/test/java/TestMember.csv");
        ArrayList<Member> testMembers = new ArrayList<>();

        Member m1 = new Member(2122,"Nikolaj", LocalDate.of(2012, Month.valueOf("September"), 13), "Aalborg", "NikoP@yahoo.com", true, true);
        Member m2 = new Member(3132,"Marie", LocalDate.of(1877, Month.valueOf("November"), 13), "Düsseldorf", "Marie@yahoo.com", true, false);
        Member m3 = new Member(4142,"Thea", LocalDate.of(1992, Month.valueOf("Februar"), 13), "Rio De Janiro", "Thea@yahoo.com", false, true);
        Member m4 = new Member(5152,"Usman", LocalDate.of(2011, Month.valueOf("Juni"), 13), "Papa Ny Guinea", "Usman@yahoo.com", false, false);

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
        db = new Database(testfile);
    }






}
