package StudentSheets;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Main {
    public static void main(String[] args) {

        List<Student> students= Arrays.asList(
                new Student("MuFeng", "Yang", "Xiamen", "2000/03/31", Person.Gender.MALE, 80, 85, 90, 407262031 ),
                new Student("Doodle", "Bear", "Xiamen", "2000/05/06", Person.Gender.MALE, 85, 80, 83, 407262591 ),
                new Student("Sir", "HUI", "Xiamen", "2000/03/31", Person.Gender.MALE, 50, 90, 90, 407262031 ),
                new Student("Maggie", "Wu", "Xiamen", "2000/06/05", Person.Gender.FEMALE, 70, 55, 45, 402262005 ),
                new Student("233", "Doodle", "Xiamen", "2000/03/31", Person.Gender.MALE, 55, 90, 90, 407262031 ),
                new Student("Darren", "Li", "Taipei", "2000/04/06", Person.Gender.MALE, 90, 93, 80, 407262024 ),
                new Student("Chien", "Zhang", "Xinpei", "2000/03/31", Person.Gender.MALE, 90, 90, 50, 407262017 ),
                new Student("juedui", "jinye", "JiangSu", "2001/04/02", Person.Gender.MALE, 70, 40, 90, 408262019 ),
                new Student("DeLong", "Ge", "Xiamen", "2000/01/06", Person.Gender.MALE, 60, 60, 30, 407262048 ),
                new Student("Nousphe", "Yang", "Xiamen", "2000/04/01", Person.Gender.MALE, 80, 78, 82, 405262023 )
        );

        System.out.println("Q1: (1) 學生資料按照學號升序印出");
        students.stream()
                .sorted(Comparator.comparing(Student::getId))
                .forEach(Student::StudentPrintAll);

        System.out.println("------------------------------------\nQ1: (2) 按照出生日降序(年紀小到大)印出學生姓名(FirstName, Last Name)");
        students.stream()
                .sorted(Comparator.comparing(student -> student.getInfo().getBirthday()))
                .forEach(student -> System.out.printf("(%s, %s)\n",student.getInfo().getFirstName(), student.getInfo().getLastName()));

        System.out.println("------------------------------------\nQ2: 把學生居住城市轉存在Set中並輸出城市名稱");
        Set<String> cities = students.stream()
                .map(student -> student.getInfo().getCity())
                .collect(Collectors.toSet());

        System.out.println(cities);

        System.out.println("------------------------------------\nQ3: 按照學生居住城市別，輸出居住在該城市之學生姓名 (FirstName, Last Name)。要求: 使用Grouping");

        students.stream()
                .map(Student::getInfo)
                .collect(Collectors.groupingBy(Person::getCity, mapping(Person::getFullName, toList())))
                .forEach((k, v) -> System.out.println(k + " " + v));

        System.out.println("------------------------------------\nQ4: 使用 Partitioning 將學生分成 MALE 與 FEMALE兩群組並輸出學生姓名");

        students.stream()
                .map(Student::getInfo)
                .collect(Collectors.partitioningBy(person -> person.getGender().equals(Person.Gender.MALE)  , mapping(Person::getFullName , toList())))
                .forEach((k, v) -> System.out.println(k + " " + v));

        System.out.println("------------------------------------\nQ5: 請輸出每個考科的平均分數、最高分與最低分");

        System.out.println("Math: [Average:" +
                students.stream()
                        .mapToDouble(student -> student.getScores().getMath())
                        .sum()
                        /
                        students.stream()
                                .count()
                + ", Max:" +
                students.stream()
                        .mapToInt(student -> student.getScores().getMath())
                        .reduce(Integer::max)
                        .getAsInt()
                + ", Min:" +
                students.stream()
                        .mapToInt(student -> student.getScores().getMath())
                        .reduce(Integer::min)
                        .getAsInt()
                + "]"
        );

        System.out.println("English: [Average:" +
                students.stream()
                        .mapToDouble(student -> student.getScores().getEnglish())
                        .sum()
                        /
                        students.stream()
                                .count()
                + ", Max:" +
                students.stream()
                        .mapToInt(student -> student.getScores().getEnglish())
                        .reduce(Integer::max)
                        .getAsInt()
                + ", Min:" +
                students.stream()
                        .mapToInt(student -> student.getScores().getEnglish())
                        .reduce(Integer::min)
                        .getAsInt()
                + "]"
        );

        System.out.println("Chinese: [Average:" +
                students.stream()
                        .mapToDouble(student -> student.getScores().getChinese())
                        .sum()
                        /
                        students.stream()
                                .count()
                + ", Max:" +
                students.stream()
                        .mapToInt(student -> student.getScores().getChinese())
                        .reduce(Integer::max)
                        .getAsInt()
                + ", Min:" +
                students.stream()
                        .mapToInt(student -> student.getScores().getChinese())
                        .reduce(Integer::min)
                        .getAsInt()
                + "]"
        );

        System.out.println("------------------------------------\nQ6: 根據考科別，輸出該科不及格同學之學號與姓名");

        System.out.print("MathFailed: ");
        students.stream()
                .filter(student -> student.getScores().getMath() < 60)
                .collect(toList())
                .forEach((Student s) -> System.out.printf("[%s, %s] ",s.getId(), s.getInfo().getFullName() ));
        System.out.println();

        System.out.print("EnglishFailed: ");
        students.stream()
                .filter(student -> student.getScores().getEnglish() < 60)
                .collect(toList())
                .forEach((Student s) -> System.out.printf("[%s, %s] ",s.getId(), s.getInfo().getFullName() ));
        System.out.println();

        System.out.print("ChineseFailed : ");
        students.stream()
                .filter(student -> student.getScores().getChinese() < 60)
                .collect(toList())
                .forEach((Student s) -> System.out.printf("[%s, %s] ",s.getId(), s.getInfo().getFullName() ));
        System.out.println();

        System.out.println("------------------------------------\nQ7: 將學生資料 [學號，姓名，三科平均分數] 按照三科平均分數降序(分數高到低) 輸出。");

        students.stream()
                .sorted(Comparator.comparing((Student s) -> s.getScores().getAverage()).reversed())
                .collect(toList())
                .forEach(s -> System.out.printf("[%s, %s, %f]\n",s.getId(), s.getInfo().getFullName(), s.getScores().getAverage() ));


    }

}
