package ex.ch13.ex1303;

class School {
    String name;
    public class Student {
        String name;
        int age;
        public Student(String schoolName, String studentName, int newAge) {
            School.this.name = schoolName;
            this.name = studentName;
            age = newAge;
        }

        public void output() {
            System.out.println("ѧУ: " + School.this.name);
            System.out.println("����: " + this.name);
            System.out.println("����" + age);
        }
    }
    public void output() {
        Student stu = new Student("SWJTU", "����", 23);
        stu.output();
    }
}

public class Inner {
    public static void main(String[] args) {
        System.out.println("ͨ���ⲿ������ڲ����Ա");
        School a = new School();
        a.output();
        System.out.println("ֱ�ӷ����ڲ���");
        School.Student b = new School().new Student("SWJTU", "����", 233);
        b.output();
    }
}
