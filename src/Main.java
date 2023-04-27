import manager.StudentManager;
import model.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final String PATH_STRING = "student.csv";
    static Scanner scanner = new Scanner(System.in);
    static StudentManager studentManager = new StudentManager();
    static final int DEFAULT_VALUE = -1;
    static final int EXIT = 0;
    static final int DISPLAY_LIST = 1;
    static final int ADD = 2;
    static final int UPDATE = 3;
    static final int REMOVE = 4;
    static final int SORT = 5;
    static final int READ = 6;
    static final int WRITE = 7;
    public static void main(String[] args) {

        int choice = DEFAULT_VALUE;
        while (choice != EXIT) {
            displayHomePage();
            Scanner mainScanner = new Scanner(System.in);
            System.out.println("Nhập lựa chọn:");
            try {
                choice = Integer.parseInt(mainScanner.nextLine());
            } catch (Exception  e) {
                System.out.println("Lựa chọn không hợp lệ");
            }

            switch (choice) {
                case DISPLAY_LIST:
                    displayStudent();
                    break;
                case ADD:
                    addStudent();
                    break;
                case UPDATE:
                    updateStudent();
                    break;
                case REMOVE:
                    setRemoveStudent();
                    break;
                case SORT:
                     sortStudent();

                case READ:
                    readListStudentToFile(PATH_STRING);

                    break;
                case WRITE:
                    writeListStudentToFile(PATH_STRING,studentManager.listStudent);
                    break;
                case EXIT:
                    break;
                default:
                    System.out.println("Lựa chọn nhập không hợp lệ ");
                    break;
            }
        }
        displayHomePage();
    }

    static void displayHomePage(){
        System.out.println("----CHƯƠNG TRÌNH QUẢN LÝ SINH VIÊN----");
        System.out.println("Chọn chức năng theo số: ");
        System.out.println("1.Xem danh sách sinh viên");
        System.out.println("2.Thêm mới");
        System.out.println("3.Cập nhật");
        System.out.println("4.Xóa");
        System.out.println("5.Sắp xếp");
        System.out.println("6.Đọc từ file");
        System.out.println("7.Ghi vào file");
        System.out.println("0.Thoát");
    }

    static void displayStudent(){
        System.out.println(studentManager.listStudent);
    }
    static void addStudent(){
        System.out.println("Nhập id: ");
        String id = scanner.nextLine();
        while(studentManager.getIndexByID(id) !=-1){
            System.out.println("Nhập lại id: ");
            id = scanner.nextLine();
        }
        System.out.println("Nhập tên: ");
        String name = scanner.nextLine();
        System.out.println("Nhập tuổi: ");
        int age;
        try{
             age = Integer.parseInt(scanner.nextLine());
        }catch(Exception  e){
            age =0;
            System.out.println("Nhập tuổi không thành công");
        }

        System.out.println("Nhập địa chỉ:");
        String address = scanner.nextLine();

        System.out.println("Nhập giới tính: ");
        String gender = scanner.nextLine();

        System.out.println("Nhập điểm trung bình:");
        double score;
        try{
             score = Double.parseDouble(scanner.nextLine());
             while(score<0||score>10){
                 System.out.println("Nhập lại");
                 score = score = Double.parseDouble(scanner.nextLine());
             }
        }catch(Exception  e){
            System.out.println("Nhập điểm không thành công");
            score =0;
        }
        Student student = new Student(id,name,age,gender,address,score);
        studentManager.insertStudent(student);
    }

    static void updateStudent(){
        System.out.println("Nhập id người dùng");
        String id = scanner.nextLine();
        if(studentManager.getIndexByID(id)==-1){
            System.out.println("Không tìm thấy học sinh");
            String input = "a";
            while(input != null){
                System.out.println("Thoát?");
                input = scanner.nextLine();
            }
        }else{
            System.out.println("Nhập tên: ");
            String name = scanner.nextLine();
            System.out.println("Nhập tuổi: ");
            int age;
            try{
                age = Integer.parseInt(scanner.nextLine());
            }catch(Exception  e){
                age =0;
                System.out.println("Nhập tuổi không thành công");
            }
            System.out.println("Nhập địa chỉ:");
            String address = scanner.nextLine();

            System.out.println("Nhập giới tính: ");
            String gender = scanner.nextLine();

            System.out.println("Nhập điểm trung bình:");
            double score;
            try{
                score = Double.parseDouble(scanner.nextLine());
                while(score<0||score>10){
                    System.out.println("Nhập lại");
                    score = score = Double.parseDouble(scanner.nextLine());
                }
            }catch(Exception  e){
                System.out.println("Nhập điểm không thành công");
                score =0;
            }
            Student student = new Student(id,name,age,gender,address,score);
            studentManager.insertStudent(student);
        }
    }
    static void setRemoveStudent(){
        System.out.println("Nhập id người muốn xóa:");
        String id = scanner.nextLine();
        studentManager.removeStudent(id);
    }

    static void sortStudent(){
        int choiceSort = DEFAULT_VALUE;
        final int REDUCE = 2;
        final int INCREASE = 1;
        while(choiceSort!=EXIT){
            System.out.println("-----Sắp xếp nhân viên theo điểm trung bình");
            System.out.println("Chọn chức năng để tiếp tục:");
            System.out.println("1.Sắp xếp điểm trung bình tăng dần");
            System.out.println("2.Sắp xếp điểm trung bình giảm dần");
            System.out.println("0.Thoát");
            System.out.println("Chọn chức năng");
            choiceSort = Integer.parseInt(scanner.nextLine());
            switch(choiceSort){
                case INCREASE:
                    studentManager.sortStudentIncrease();
                    break;
                case REDUCE:
                    studentManager.sortStudentReduce();
                    break;
                case EXIT:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }

    static public void writeListStudentToFile(String filePath, List<Student> listStudent) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Student student : listStudent) {
                String[] data = { student.getId(), student.getName(),
                        Integer.toString(student.getAge()), student.getGender(), student.getAddress(),
                        Double.toString(student.getScoreAverage()) };
                writer.write(String.join(",", data));
                writer.write("\n");
            }
            System.out.println("Đã ghi danh sách sinh viên vào file " + filePath + "!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static public void readListStudentToFile(String filePath) {
        String delimiter = ",";
        List<Student> danhSachSinhVienMoi = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Đoạn code dưới đây là để cảnh báo người dùng trước khi cập nhật bộ nhớ
            System.out.println("Chú ý: Tất cả dữ liệu sinh viên hiện có trong bộ nhớ sẽ bị xóa nếu bạn tiếp tục!");
            System.out.print("Bạn có chắc chắn muốn tiếp tục (y/n)? ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (!input.equalsIgnoreCase("y")) {
                System.out.println("Hủy thao tác đọc file.");
                return;
            }
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(delimiter);
                Student sinhVien = new Student(data[0], data[1], Integer.parseInt(data[2]),
                        data[3], data[4], Double.parseDouble(data[5]));
                danhSachSinhVienMoi.add(sinhVien);
            }
            System.out.println("Đã đọc danh sách sinh viên từ file CSV!");
            System.out.println("Đang cập nhật lại bộ nhớ...");
            studentManager.listStudent.clear();
            studentManager.listStudent.addAll(danhSachSinhVienMoi);
            // cập nhaạp lại bộ nhớ , ghi ra file
            String outputFile = PATH_STRING;
            FileWriter writer = new FileWriter(outputFile);
            for (Student sinhVien : studentManager.listStudent) {
                String[] data = {
                        sinhVien.getId(), sinhVien.getName(),
                        Integer.toString(sinhVien.getAge()), sinhVien.getGender(),
                        sinhVien.getAddress(), Double.toString(sinhVien.getScoreAverage()) };
                writer.write(String.join(",", data));
                writer.write("\n");
            }
            writer.close();
            System.out.println("Đã cập nhật lại danh sách sinh viên và ghi ra file CSV!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}