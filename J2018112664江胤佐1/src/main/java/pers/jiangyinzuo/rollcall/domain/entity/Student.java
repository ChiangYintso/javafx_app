package pers.jiangyinzuo.rollcall.domain.entity;

import pers.jiangyinzuo.rollcall.domain.mapper.FieldMapper;
import pers.jiangyinzuo.rollcall.domain.mapper.TableMapper;
import pers.jiangyinzuo.rollcall.domain.repository.RollCallRepo;
import pers.jiangyinzuo.rollcall.domain.repository.TeachingClassRepo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
@TableMapper("rollcall_student")
public class Student implements Serializable {

	@FieldMapper(name = "student_id")
	private Long studentId;

	@FieldMapper(name = "gender")
	private Boolean gender;

	@FieldMapper(name = "student_name")
	private String studentName;

	@FieldMapper(name = "password")
	private String password;

	@FieldMapper(name = "major")
	private String major;

	private TeachingClassRepo teachingClassRepo;
	private RollCallRepo rollCallRepo;

	public Student(Long studentId, Boolean gender, String studentName, String password, String major) {
		this.studentId = studentId;
		this.gender = gender;
		this.studentName = studentName;
		this.password = password;
		this.major = major;
	}

	private Student(Builder builder) {
		setStudentId(builder.studentId);
		setGender(builder.gender);
		setStudentName(builder.studentName);
		password = builder.password;
		setMajor(builder.major);
	}

	/**
	 * 空参构造方法用于反射创建实例对象
	 */
	public Student() {};

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Boolean getGender() {
		return gender;
	}

	public String getGenderStr() {
		if (gender) {
			return "男";
		} else {
			return "女";
		}
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getPwd() {
		return password == null ? "123456" : password;
	}

	public void setPwd(String pwd) {
		this.password = pwd;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}


	public String welcome() {
		return "你好, " + this.studentId + " " + this.studentName;
	}

	@Override
	public boolean equals(Object obj) {
		return obj == this || obj instanceof Student && studentId != null && studentId.equals(((Student) obj).getStudentId());
	}

	public static final class Builder {
		private Long studentId;
		private Boolean gender;
		private String studentName;
		private String password;
		private String major;
		private List<TeachingClass> teachingClassList;
		private List<RollCall> rollCallList;

		public Builder() {
		}

		public Builder studentId(Long val) {
			studentId = val;
			return this;
		}

		public Builder gender(Boolean val) {
			gender = val;
			return this;
		}


		public Builder gender(String val) {
			gender = "男".equals(val);
			return this;
		}

		public Builder studentName(String val) {
			studentName = val;
			return this;
		}

		public Builder password(String val) {
			password = val;
			return this;
		}

		public Builder major(String val) {
			major = val;
			return this;
		}

		public Student build() {
			return new Student(this);
		}
	}
}
