import data_source.FileHandler;
import domain_model.Database;
import domain_model.Member;
import domain_model.Result;
import domain_model.SwimmingDiscipline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {
    Database db;
    FileHandler fh = new FileHandler();
    File memberTestFile;
    File resultTestFile;
    ArrayList<Member> testMembers = new ArrayList<>();
    ArrayList<Result> testResults = new ArrayList<>();


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

        try (PrintStream out = new PrintStream(memberTestFile)){
            for (Member member: testMembers) {
                out.println(member.getMemberID() + ";" +
                        member.getName() + ";" +
                        member.getBirthday() + ";" +
                        member.getAddress() + ";" +
                        member.getEmail() + ";" +
                        member.isOnCompetitionTeam() + ";" +
                        member.isActive() + ";" +
                        member.getSubscriptionDate()
                );
            }
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }

        resultTestFile = new File (basePath + "resultTestFile.csv");

        Result r1 = new Result(2122,"Nikolaj", LocalDate.of(2012,1,24), SwimmingDiscipline.BACKSTROKE,25.8,null,0,LocalDate.of(2021,7,10));
        Result r2 = new Result(3132,"Marie", LocalDate.of(1965,4,30), SwimmingDiscipline.CRAWL,24.2,null,0,LocalDate.of(2022,5,12));
        Result r3 = new Result(4142,"Thea", LocalDate.of(1993,11,26), SwimmingDiscipline.BUTTERFLY,26.7,null,0,LocalDate.of(2023,8,1));
        Result r4 = new Result(5152,"Usman",LocalDate.of(2022,2,15), SwimmingDiscipline.BREASTSTROKE,21.2,null,0,LocalDate.of(2023,4,5));

        testResults.add(r1);
        testResults.add(r2);
        testResults.add(r3);
        testResults.add(r4);

        try (PrintStream output = new PrintStream(resultTestFile)) {
            for (Result resultat : testResults) {
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
        db = new Database();
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
void loadMembers(){
        int expectedSize = 4;
        int actualSize = fh.loadMemberData("testMember.csv").size();
        assertEquals(expectedSize,actualSize);
}

    @Test
    void deleteMember(){
        db.deleteMember("2122");
        int expectedSizeTestMembers = 3;
        int actualSizeTestMembers = testMembers.size();
        int expectedSizeTestResults = 3;
        int actualSizeTestResults = testResults.size();
        assertEquals(expectedSizeTestMembers, actualSizeTestMembers);
        assertEquals(expectedSizeTestResults, actualSizeTestResults);
    }

    @Test
    void addMember(){
        int startSize = testMembers.size();
        db.createMember(9987,"Jens", LocalDate.of(2012,1,24), "Aalborg", "NikoP@yahoo.com", true, true,LocalDate.of(2023,12,4));
        int expectedSize = startSize + 1;
        int actualSize = testMembers.size();
        assertEquals(expectedSize,actualSize);
    }







}
