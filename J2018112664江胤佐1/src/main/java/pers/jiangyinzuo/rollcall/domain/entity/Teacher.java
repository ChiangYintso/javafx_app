package pers.jiangyinzuo.rollcall.domain.entity;

import pers.jiangyinzuo.rollcall.domain.mapper.FieldMapper;
import pers.jiangyinzuo.rollcall.domain.mapper.TableMapper;
import pers.jiangyinzuo.rollcall.domain.repository.TeachingClassRepo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
@TableMapper("rollcall_teacher")
public class Teacher implements Serializable {

    @FieldMapper(name = "teacher_id")
    private Long teacherId;

    @FieldMapper(name = "teacher_name")
    private String teacherName;

    @FieldMapper(name = "department")
    private String department;

    @FieldMapper(name = "gender")
    private Boolean gender;

    @FieldMapper(name = "password")
    private String password;

    @FieldMapper(name = "title")
    private String title;

    private TeachingClassRepo teachingClassRepo;

    public Teacher() {};

    public Teacher(Long teacherId, String teacherName, String department, Boolean gender, String pwd, String title) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.department = department;
        this.gender = gender;
        this.password = pwd;
        this.title = title;
    }

    private Teacher(Builder builder) {
        setTeacherId(builder.teacherId);
        setTeacherName(builder.teacherName);
        setDepartment(builder.department);
        setGender(builder.gender);
        setPassword(builder.password);
        setTitle(builder.title);
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

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
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

    public String welcome() {
        return "ÄãºÃ, " + this.teacherId + " " + this.teacherName + this.title;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof Teacher && teacherId != null && teacherId.equals(((Teacher) obj).getTeacherId());
    }

    public boolean equals(Long teacherId) {
        return teacherId.equals(this.teacherId);
    }

    public static final class Builder {
        private Long teacherId;
        private String teacherName;
        private String department;
        private Boolean gender;
        private String password;
        private String title;

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

        public Builder gender(Boolean val) {
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

        public Teacher build() {
            return new Teacher(this);
        }
    }
}
