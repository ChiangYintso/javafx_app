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
            System.out.println("学校: " + School.this.name);
            System.out.println("姓名: " + this.name);
            System.out.println("年龄" + age);
        }
    }
    public void output() {
        Student stu = new Student("SWJTU", "张三", 23);
        stu.output();
    }
}

public class Inner {
    public static void main(String[] args) {
        System.out.println("通过外部类访问内部类成员");
        School a = new School();
        a.output();
        System.out.println("直接访问内部类");
        School.Student b = new School().new Student("SWJTU", "李四", 233);
        b.output();
    }
}
