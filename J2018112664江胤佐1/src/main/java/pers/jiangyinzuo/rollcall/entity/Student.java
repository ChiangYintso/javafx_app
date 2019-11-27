package pers.jiangyinzuo.rollcall.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class Student implements Serializable {

	private Long studentId;
	private Byte gender;
	private String studentName;
	private String password;
	private String major;
	private List<TeachingClass> teachingClassList;
	private List<RollCall> rollCallList;

	public Student(Long studentId, Byte gender, String studentName, String password, String major) {
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
		setTeachingClassList(builder.teachingClassList);
		setRollCallList(builder.rollCallList);
	}


	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Byte getGender() {
		return gender;
	}

	public String getGenderStr() {
		if (gender == 1) {
			return "ÄÐ";
		} else {
			return "Å®";
		}
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getPwd() {
		return password;
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

	public List<TeachingClass> getTeachingClassList() {
		return teachingClassList;
	}

	public void setTeachingClassList(List<TeachingClass> teachingClassList) {
		this.teachingClassList = teachingClassList;
	}

	public List<RollCall> getRollCallList() {
		return rollCallList;
	}

	public void setRollCallList(List<RollCall> rollCallList) {
		this.rollCallList = rollCallList;
	}

	public String welcome() {
		return "ÄãºÃ, " + this.studentId + " " + this.studentName;
	}

	@Override
	public boolean equals(Object obj) {
		return obj == this || obj instanceof Student && studentId != null && studentId.equals(((Student) obj).getStudentId());
	}

	public static final class Builder {
		private Long studentId;
		private Byte gender;
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

		public Builder gender(Byte val) {
			gender = val;
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

		public Builder teachingClassList(List<TeachingClass> val) {
			teachingClassList = val;
			return this;
		}

		public Builder rollCallList(List<RollCall> val) {
			rollCallList = val;
			return this;
		}

		public Student build() {
			return new Student(this);
		}
	}
}
