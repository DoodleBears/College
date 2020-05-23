package StudentSheets;

import java.util.*;

public class Student {
    private Person info;
    private ScoreSheet scores;

    private int id;

    public Student(String firstName, String lastName, String city, String birthday, Person.Gender gender, int math, int english, int chinese, int id){
        info = new Person(firstName, lastName, city, birthday, gender);
        scores = new ScoreSheet(math, english, chinese);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Person getInfo() {
        return info;
    }

    public ScoreSheet getScores() {
        return scores;
    }

    public void StudentPrintAll(){
        this.info.PersonPrintAll();
        this.scores.ScoresPrintAll();
        System.out.printf(" %s]\n",this.getId());
    }
}
