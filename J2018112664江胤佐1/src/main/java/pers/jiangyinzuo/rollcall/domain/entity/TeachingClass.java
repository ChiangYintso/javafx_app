package pers.jiangyinzuo.rollcall.domain.entity;

import pers.jiangyinzuo.rollcall.domain.mapper.FieldMapper;
import pers.jiangyinzuo.rollcall.domain.mapper.TableMapper;
import pers.jiangyinzuo.rollcall.domain.repository.StudentRepo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
@TableMapper("rollcall_teaching_class")
public class TeachingClass implements Serializable {
    @FieldMapper(name = "class_id")
    private Long classId;

    @FieldMapper(name = "class_name")
    private String className;

    @FieldMapper(name = "semester")
    private Integer semester;

    @FieldMapper(name = "course_code")
    private Long courseCode;

    @FieldMapper(name = "credit")
    private Integer credit;

    @FieldMapper(name = "class_intro")
    private String intro;

    @FieldMapper(name = "weeks")
    private String weeks;

    @FieldMapper(name = "class_session")
    private Integer session;

    @FieldMapper(name = "classroom")
    private String classroom;

    @FieldMapper(type = "reference", name = "teacher_id", joinName = "teacher_id")
    private Teacher teacher;

    private StudentRepo studentRepo;

    private TeachingClass(Builder builder) {
        setClassId(builder.classId);
        setClassName(builder.className);
        setSemester(builder.semester);
        setCourseCode(builder.courseCode);
        setCredit(builder.credit);
        setIntro(builder.intro);
        setWeeks(builder.weeks);
        setSession(builder.session);
        setClassroom(builder.classroom);
        setTeacher(builder.teacher);
        studentRepo = builder.studentRepo;
    }

    public Long getClassId() {
        return classId;
    }

    public TeachingClass(Long classId, String className, Integer semester, Long courseCode, Integer credit,
                         String intro, String week, Integer session, Teacher teacher) {
        this.classId = classId;
        this.className = className;
        this.semester = semester;
        this.courseCode = courseCode;
        this.credit = credit;
        this.intro = intro;
        this.teacher = teacher;
        this.weeks = week;
        this.session = session;
    }

    /**
     * 空参构造函数用于反射创建对象
     */
    public TeachingClass() {
    }

    public List<Student> getStudentList() {
        return studentRepo.getTeachingClassList(classId);
    }

    public TeachingClass(Long classId) {
        this.classId = classId;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public Integer getSession() {
        return session;
    }

    public void setSession(Integer session) {
        this.session = session;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Long getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(Long courseCode) {
        this.courseCode = courseCode;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Teacher getTeacherId() {
        return teacher;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "TeachingClass [classId=" + classId + ", className=" + className + ", semester=" + semester
                + ", courseCode=" + courseCode + ", credit=" + credit + ", intro=" + intro + ", teacher=" + teacher.getTeacherName()
                + "]";
    }

    public String getTeachingClassInfo() {
        return "课程id: " + this.classId + ", 课程名称: " + this.className + ", 开课学期: " + this.semester + ", 学分: "
                + this.credit + " 教师: " + this.teacher.getTeacherName() + "课程简介: " + this.intro;
    }

    public String getSchedule() {
        return this.className + " 周" + this.session / 10 + "第" + this.session % 10 + "讲";
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || obj instanceof TeachingClass && classId != null && classId.equals(((TeachingClass) obj).getClassId());
    }

    /**
     * 判断某学生是否选择了这门课
     *
     * @param studentId 学生学号
     * @return 是否选择了这门课
     */
    public boolean isSelectedThisClass(Long studentId) {
        // TODO: complement the StudentRepo
        return true;
//        if (studentList != null && studentId != null) {
//            for (Student student : studentList) {
//                if (studentId.equals(student.getStudentId())) {
//                    return true;
//                }
//            }
//        }
//        return false;
    }

    public static Boolean isTeachThisClass(TeachingClass cls, Long teacherId) {
        return cls.getTeacherId().equals(teacherId);
    }

    public static final class Builder {
        private Long classId;
        private String className;
        private Integer semester;
        private Long courseCode;
        private Integer credit;
        private String intro;
        private String weeks;
        private Integer session;
        private String classroom;
        private Teacher teacher;
        private StudentRepo studentRepo;

        public Builder() {
        }

        public Builder classId(Long val) {
            classId = val;
            return this;
        }

        public Builder className(String val) {
            className = val;
            return this;
        }

        public Builder semester(Integer val) {
            semester = val;
            return this;
        }

        public Builder courseCode(Long val) {
            courseCode = val;
            return this;
        }

        public Builder credit(Integer val) {
            credit = val;
            return this;
        }

        public Builder classroom(String val) {
            classroom = val;
            return this;
        }

        public Builder intro(String val) {
            intro = val;
            return this;
        }

        public Builder weeks(String val) {
            weeks = val;
            return this;
        }

        public Builder session(Integer val) {
            session = val;
            return this;
        }

        public Builder teacher(Teacher val) {
            teacher = val;
            return this;
        }

        public Builder studentRepo(StudentRepo val) {
            studentRepo = val;
            return this;
        }

        public TeachingClass build() {
            return new TeachingClass(this);
        }
    }
}
