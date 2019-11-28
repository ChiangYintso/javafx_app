package pers.jiangyinzuo.rollcall.domain.entity;

import pers.jiangyinzuo.rollcall.domain.mapper.FieldMapper;
import pers.jiangyinzuo.rollcall.domain.mapper.TableMapper;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Jiang Yinzuo
 */
@TableMapper("rollcall_rollcall_record")
public class RollCall implements Serializable {

	@FieldMapper(name = "rollcall_id")
    private Long rollCallId;

    /**
     * �ѵ���δ�����ٵ�����١�����
     */
    @FieldMapper(name = "presence")
    private String presence;

    @FieldMapper(name = "rollcall_type")
    private Integer rollCallType;

    @FieldMapper(name = "rollcall_time")
    private Timestamp rollCallTime;

    @FieldMapper(type = "reference", name = "class_id")
    private TeachingClass teachingClass;

    @FieldMapper(type = "reference", name = "student_id")
    private Student student;

    public RollCall() {
    }

    public RollCall(Long rollCallId, String presence, Integer rollCallType,
                    TeachingClass teachingClass, Student student) {
        this.rollCallId = rollCallId;
        this.presence = presence;
        this.rollCallType = rollCallType;

        this.teachingClass = teachingClass;
        this.student = student;
    }

    public RollCall(Long rollCallId) {
        this.rollCallId = rollCallId;
    }

    public RollCall copy() {
        return new RollCall(rollCallId, presence, rollCallType, teachingClass, student);
    }

    public Long getRollCallId() {
        return rollCallId;
    }

    public void setRollCallId(Long rollCallId) {
        this.rollCallId = rollCallId;
    }

    public String getPresence() {
        return presence;
    }

    public void setPresence(String presence) {
        this.presence = presence;
    }

    public String getRollCallType() {
        if (rollCallType == 2) {
            return "����";
        }
        return "����";
    }

    public void setRollCallType(Integer rollCallType) {
        this.rollCallType = rollCallType;
    }

    public Timestamp getRollCallTime() {
        return rollCallTime;
    }

    public void setRollCallTime(Timestamp rollCallTime) {
        this.rollCallTime = rollCallTime;
    }

    public TeachingClass getTeachingClass() {
        return teachingClass;
    }

    public void setTeachingClass(TeachingClass teachingClass) {
        this.teachingClass = teachingClass;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getRollCallInfo() {
        return this.rollCallType + " " + this.presence;
    }

    /**
     * ����rollCallId�ж�����ʵ�����Ƿ����
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return obj == this || obj instanceof RollCall && rollCallId != null && rollCallId.equals(((RollCall) obj).getRollCallId());
    }

    public Long getTeachingClassId() {
        if (teachingClass == null) {
            return null;
        }
        return teachingClass.getClassId();
    }

    public Long getStudentId() {
        return this.student.getStudentId();
    }
}
