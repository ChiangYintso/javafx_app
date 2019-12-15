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

    public static final String PRESENCE = "�ѵ�";
    public static final String ABSENT = "δ��";
    public static final String LATE = "�ٵ�";
    public static final String ASK_FOR_LEAVE = "���";
    public static final String LEAVE_EARLY = "����";
    public static final String AWARD = "����";
    public static final String PUNISH = "����";

	@FieldMapper(name = "rollcall_id")
    private Long rollCallId;

    /**
     * �ѵ���δ�����ٵ�����١�����
     */
    @FieldMapper(name = "presence")
    private String presence;

    @FieldMapper(name = "rollcall_type")
    private Long rollCallType;

    @FieldMapper(name = "rollcall_time")
    private Timestamp rollCallTime;

    @FieldMapper(type = "reference", name = "class_id")
    private TeachingClass teachingClass;

    @FieldMapper(type = "reference", name = "student_id")
    private Student student;

    public RollCall() {
    }

    public RollCall(Long rollCallId, String presence, Long rollCallType,
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

    private RollCall(Builder builder) {
        setRollCallId(builder.rollCallId);
        setPresence(builder.presence);
        setRollCallType(builder.rollCallType);
        setRollCallTime(builder.rollCallTime);
        setTeachingClass(builder.teachingClass);
        setStudent(builder.student);
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

    public String getRollCallTypeString() {
        if (rollCallType == 2) {
            return "����";
        }
        return "����";
    }

    public Long getRollCallTypeLong() {
        return rollCallType;
    }

    public void setRollCallType(Long rollCallType) {
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

    public static final class Builder {
        private Long rollCallId;
        private String presence;
        private Long rollCallType;
        private Timestamp rollCallTime;
        private TeachingClass teachingClass;
        private Student student;

        public Builder() {
        }

        public Builder rollCallId(Long val) {
            rollCallId = val;
            return this;
        }

        public Builder presence(String val) {
            presence = val;
            return this;
        }

        public Builder rollCallType(Long val) {
            rollCallType = val;
            return this;
        }

        public Builder rollCallTime(Timestamp val) {
            rollCallTime = val;
            return this;
        }

        public Builder teachingClass(TeachingClass val) {
            teachingClass = val;
            return this;
        }

        public Builder student(Student val) {
            student = val;
            return this;
        }

        public RollCall build() {
            return new RollCall(this);
        }
    }
}
