package pers.jiangyinzuo.rollcall.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class Teacher implements Serializable {
    private Long teacherId;
    private String teacherName;
    private String department;
    private String gender;
    private String password;
    private String title;
    private List<TeachingClass> teachingClassList;

    public Teacher(Long teacherId, String teacherName, String department, String gender, String pwd, String title,
                   List<TeachingClass> teachingClassList) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.department = department;
        this.gender = gender;
        this.password = pwd;
        this.title = title;
        this.teachingClassList = teachingClassList;
    }

    private Teacher(Builder builder) {
        setTeacherId(builder.teacherId);
        setTeacherName(builder.teacherName);
        setDepartment(builder.department);
        setGender(builder.gender);
        setPassword(builder.password);
        setTitle(builder.title);
        setTeachingClassList(builder.teachingClassList);
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TeachingClass> getTeachingClassList() {
        return teachingClassList;
    }

    public void setTeachingClassList(List<TeachingClass> teachingClassList) {
        this.teachingClassList = teachingClassList;
    }

    public String welcome() {
        return "ÄãºÃ, " + this.teacherId + " " + this.teacherName + this.title;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof Teacher && teacherId != null && teacherId.equals(((Teacher) obj).getTeacherId());
    }

    public static final class Builder {
        private Long teacherId;
        private String teacherName;
        private String department;
        private String gender;
        private String password;
        private String title;
        private List<TeachingClass> teachingClassList;

        public Builder() {
        }

        public Builder teacherId(Long val) {
            teacherId = val;
            return this;
        }

        public Builder teacherName(String val) {
            teacherName = val;
            return this;
        }

        public Builder department(String val) {
            department = val;
            return this;
        }

        public Builder gender(String val) {
            gender = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder teachingClassList(List<TeachingClass> val) {
            teachingClassList = val;
            return this;
        }

        public Teacher build() {
            return new Teacher(this);
        }
    }
}
