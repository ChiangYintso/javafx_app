package pers.jiangyinzuo.rollcall.service;

public interface UserService {
	Integer getId();

	void setId(Integer id);

	String getPwd();

	void setPwd(String pwd);

	Short getIsStudent();

	void setIsStudent(Short isStudent);
}
