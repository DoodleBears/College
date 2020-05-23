package StudentSheets;


public class Person {
    private String firstName;
    private String lastName;
    private String city;
    private String birthday;
    private Gender gender;

    //constructor
    public Person (String firstName, String lastName, String city, String birthday, Gender gender){
        this.birthday = birthday;
        this.city = city;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    enum Gender { MALE, FEMALE};

    //get() and set()
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() { return firstName + "-" + lastName;}

    public void PersonPrintAll() {
        System.out.printf("[%s, %s, %s, %s, ",this.getFirstName(), this.getLastName(), this.getCity(), this.getBirthday());
        System.out.print(this.getGender() + ", ");
    }
}
