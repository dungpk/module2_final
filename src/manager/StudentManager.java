package manager;

import base.StudentAbstract;
import model.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StudentManager extends StudentAbstract implements Serializable {
    public List<Student> listStudent = new ArrayList<>();
    ;

    public StudentManager() {
        listStudent.add(new Student("123456", "phung khac dung", 25,
                "Nam", "Hà Nội", 9.6));
        listStudent.add(new Student("123456", "tran trung hieu", 22,
                "Nam", "Hà Nội", 8.6));

        listStudent.add(new Student("123456", "tran trung hieu", 23,
                "Nam", "Hà Nội", 7.6));
    }

    @Override
    public void display() {

    }

    @Override
    public void insertStudent(Student student) {
        int index = getIndexByID(student.getId());
        if (index >= 0) {
            listStudent.set(index, student);
        } else {
            listStudent.add(student);
        }
    }
    @Override
    public void removeStudent(String id) {
        Scanner scanner =  new Scanner(System.in);
        int index = getIndexByID(id);
        if (index >= 0) {
            System.out.println("Bạn muốn xóa?");
            System.out.println("1.Y");
            System.out.println("2.NO");
            String confirm = scanner.nextLine();
            if(confirm.equals("y")){
                listStudent.remove(index);
                System.out.println("Xoa thanh cong");
            }else{
                System.out.println("Ban da huy viec xoa");
            }

        } else {
            System.out.println("Không tìm thấy học sinh cần xóa!");
        }
    }

    @Override
    public void sortStudentReduce() {
        List<Student> sortedList =  listStudent.stream()
                .sorted(Comparator.comparing(Student::getScoreAverage))
                .collect(Collectors.toList());
        for(int indexStudent=listStudent.size()-1;indexStudent>=0;indexStudent--){
            System.out.println(sortedList.get(indexStudent));
        }
    }
    @Override
    public void sortStudentIncrease() {
        List<Student> sortedList =  listStudent.stream()
                .sorted(Comparator.comparing(Student::getScoreAverage))
                .collect(Collectors.toList());
        for (Student pr : sortedList) {
            System.out.println(pr);
        }
    }
    public int getIndexByID(String id) {
        int index = -1;
        for (Student student : listStudent) {
            if (student.getId().equals(id)) {
                return listStudent.indexOf(student);
            }
        }
        return index;
    }
}
