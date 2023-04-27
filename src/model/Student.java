package model;
public class Student {
    private String id;
    private String name;
    private int age;
    private String gender;
    String address;
     private double scoreAverage;

    @Override
    public String toString() {
        return  "id: " + id + "\t"+
                "name: " + name + "\t" +
                "age: " + age + "\t" +
                "gender: " + gender + "\t" +
                "address: " + address + "\t" +
                "scoreAverage: " + scoreAverage +
                "\n";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getScoreAverage() {
        return scoreAverage;
    }

    public void setScoreAverage(double scoreAverage) {
        this.scoreAverage = scoreAverage;
    }

    public Student(String id, String name, int age, String gender, String address, double scoreAverage) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.scoreAverage = scoreAverage;
    }

    public Student() {
    }

}
