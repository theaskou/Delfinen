import data_source.FileHandler;
import domain_model.Database;
import domain_model.Member;
import domain_model.Result;
import domain_model.SwimmingDiscipline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {
    Database db;
    FileHandler fh = new FileHandler();
    String memberTestFile;
    String resultTestFile;
    String basePath;


    @BeforeEach
    void setUp() throws IOException {

        basePath = System.getProperty("user.dir") + "/src/test/java/";
        memberTestFile = basePath + "testMember.csv";
        //Sletter den gamle member test fil.
        new File(memberTestFile).delete();
        //Laver en ny member test fil
        new File(memberTestFile).createNewFile();

        resultTestFile = basePath + "resultTestFile.csv";
        //Sletter den game test fil.
        new File(resultTestFile).delete();
        //Laver en ny testfil.
        new File(resultTestFile).createNewFile();
        db = new Database(memberTestFile, resultTestFile);


        //Creater test medlemmer vha create metoden i databasen, den metode gemmer automatisk til member fil
        db.createMember(2122,"Nikolaj", LocalDate.of(2012,1,24), "Aalborg", "NikoP@yahoo.com", true, true,LocalDate.of(2023,12,4));
        db.createMember(3132,"Marie", LocalDate.of(1965,4,30), "Düsseldorf", "Marie@yahoo.com", true, false,LocalDate.of(2023,12,4));
        db.createMember(4142,"Thea", LocalDate.of(1993,11,26), "Rio De Janiro", "Thea@yahoo.com", false, true,LocalDate.of(2023,12,4));
        db.createMember(5152,"Usman",LocalDate.of(2022,2,15), "Papa Ny Guinea", "Usman@yahoo.com", false, false,LocalDate.of(2023,12,4));

        //Creater test resultater vha af create result metoden i databasen, den metode gemmer automatisk til result fil.
        db.createResult(new Member(2122,"Nikolaj", LocalDate.of(2012,1,24), "Aalborg", "NikoP@yahoo.com", true, true,LocalDate.of(2023,12,4)), SwimmingDiscipline.BACKSTROKE,25.8,LocalDate.of(2021,7,10));
        db.createResult(new Member(3132,"Marie", LocalDate.of(1965,4,30), "Düsseldorf", "Marie@yahoo.com", true, false,LocalDate.of(2023,12,4)), SwimmingDiscipline.CRAWL,24.2,LocalDate.of(2022,5,12));
        db.createResult(new Member(4142,"Thea", LocalDate.of(1993,11,26), "Rio De Janiro", "Thea@yahoo.com", false, true,LocalDate.of(2023,12,4)), SwimmingDiscipline.BUTTERFLY,26.7,LocalDate.of(2023,8,1));
        db.createResult(new Member(5152,"Usman",LocalDate.of(2022,2,15), "Papa Ny Guinea", "Usman@yahoo.com", false, false,LocalDate.of(2023,12,4)), SwimmingDiscipline.BREASTSTROKE,21.2,LocalDate.of(2023,4,5));

    }


    @Test
    void loadMembers(){
        int expectedSize = 4;
        int actualSize = fh.loadMemberData(basePath + "testMember.csv").size();
        assertEquals(expectedSize,actualSize);
    }

    @Test
    void loadResults(){
        int expectedSize = 4;
        int actualSize = fh.loadResultData(basePath + "resultTestFile.csv").size();
        assertEquals(expectedSize,actualSize);
    }

    @Test
    void deleteMember(){
        db.deleteMember(2122);
        int expectedSizeTestMembers = 3;
        int actualSizeTestMembers = db.getMemberlist();
        int expectedSizeTestResults = 3;
        int actualSizeTestResults = db.getResultatlist();
        assertEquals(expectedSizeTestMembers, actualSizeTestMembers);
        assertEquals(expectedSizeTestResults, actualSizeTestResults);
    }

    @Test
    void addMember(){
        int startSize = db.getMemberlist();
        db.createMember(9987,"Jens", LocalDate.of(2012,1,24), "Aalborg", "NikoP@yahoo.com", true, true,LocalDate.of(2023,12,4));
        int expectedSize = startSize + 1;
        int actualSize = db.getMemberlist();
        assertEquals(expectedSize,actualSize);
    }

    @Test
    void calculateSubscription(){
        
    }

@Test
    void juniorlistFilter(){
        ArrayList<Result> juniorTeam = db.juniorTeamFilter();
        int expectedSize = 2;
        int actualSize = juniorTeam.size();
        assertEquals(expectedSize, actualSize);
}

@Test
void seniorlistFilter(){
        ArrayList<Result> seniorTeam = db.seniorTeamFilter();
        int expectedSize = 2;
        int actualSize = seniorTeam.size();
        assertEquals(expectedSize, actualSize);
}

@Test
    void crawlResultsFilter(){
        ArrayList<Result> crawlResults = db.crawlResultsFilter(db.seniorTeamFilter());
        int expectedSize = 1;
        int actualSize = crawlResults.size();
        assertEquals(expectedSize, actualSize);
}

}
