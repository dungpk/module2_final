package base;

import model.Student;

public abstract class StudentAbstract {
    public abstract void display();
    public abstract void insertStudent(Student student);
    public abstract void removeStudent(String name);
    public abstract void  sortStudentIncrease();
    public abstract void  sortStudentReduce();
}
