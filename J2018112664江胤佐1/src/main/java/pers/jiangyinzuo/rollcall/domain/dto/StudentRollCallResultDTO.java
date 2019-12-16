package pers.jiangyinzuo.rollcall.domain.dto;

import pers.jiangyinzuo.rollcall.domain.mapper.FieldMapper;

public class StudentRollCallResultDTO implements Comparable<StudentRollCallResultDTO> {
    @FieldMapper(name = "student_id")
    private Long studentId;

    @FieldMapper(name = "student_name")
    private String studentName;

    @FieldMapper(name = "arrived")
    private Long presenceCount;

    @FieldMapper(name = "absent")
    private Long absentCount;

    @FieldMapper(name = "late")
    private Long lateCount;

    @FieldMapper(name = "ask_for_leave")
    private Long askForLeaveCount;

    @FieldMapper(name = "leave_early")
    private Long leaveEarlyCount;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getPresenceCount() {
        return presenceCount;
    }

    public void setPresenceCount(Long presenceCount) {
        this.presenceCount = presenceCount;
    }

    public Long getAbsentCount() {
        return absentCount;
    }

    public void setAbsentCount(Long absentCount) {
        this.absentCount = absentCount;
    }

    public Long getLateCount() {
        return lateCount;
    }

    public void setLateCount(Long lateCount) {
        this.lateCount = lateCount;
    }

    public Long getAskForLeaveCount() {
        return askForLeaveCount;
    }

    public void setAskForLeaveCount(Long askForLeaveCount) {
        this.askForLeaveCount = askForLeaveCount;
    }

    public Long getLeaveEarlyCount() {
        return leaveEarlyCount;
    }

    public void setLeaveEarlyCount(Long leaveEarlyCount) {
        this.leaveEarlyCount = leaveEarlyCount;
    }

    public Long getAbnormalCount() {
        return absentCount + lateCount + leaveEarlyCount;
    }

    @Override
    public int compareTo(StudentRollCallResultDTO o) {
        return (int)(o.getAbnormalCount() - getAbnormalCount());
    }
}
