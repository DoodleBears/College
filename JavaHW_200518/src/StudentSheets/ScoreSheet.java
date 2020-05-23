package StudentSheets;

import java.util.*;

public class ScoreSheet {
    private int math;
    private int english;
    private int chinese;

    private double average;

    public ScoreSheet(int math, int english, int chinese) {
        this.math = math;
        this.english = english;
        this.chinese = chinese;
        this.average = (math + english + chinese) / 3.0;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getMath() {
        return math;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getEnglish() {
        return english;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public int getChinese() {
        return chinese;
    }

    public double getAverage() {
        return average;
    }

    public void ScoresPrintAll() {
        System.out.printf("%d, %d, %d,",this.getMath(), this.getEnglish(), this.getChinese());
    }
}
