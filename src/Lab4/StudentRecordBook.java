package Lab4;

import java.util.ArrayList;

public class StudentRecordBook {
    public String FirstName;
    public String MiddleName;
    public String LastName;
    public int Num;
    public int Course;
    public ArrayList<String> Exams;
    public ArrayList<String> Credits;
    public double AverageScorePerSession;

    public StudentRecordBook(String firstName,
                             String middleName,
                             String lastName,
                             int num,
                             int course,
                             ArrayList<String> exams,
                             ArrayList<String> credits,
                             double averageScorePerSession) {
        FirstName = firstName;
        MiddleName = middleName;
        LastName = lastName;
        Num = num;
        Course = course;
        Exams = exams;
        Credits = credits;
        AverageScorePerSession = averageScorePerSession;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s курс %s | середня оцінка за семестр %s", FirstName, MiddleName, LastName, Course, AverageScorePerSession);
    }
}
