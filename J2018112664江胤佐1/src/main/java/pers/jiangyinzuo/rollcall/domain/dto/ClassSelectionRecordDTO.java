package pers.jiangyinzuo.rollcall.domain.dto;

/**
 * @author Jiang Yinzuo
 */
public class ClassSelectionRecordDTO {
    private Long selectionId;
    private Long classId;
    private Long studentId;

    public ClassSelectionRecordDTO() {}

    private ClassSelectionRecordDTO(Builder builder) {
        setSelectionId(builder.selectionId);
        setClassId(builder.classId);
        setStudentId(builder.studentId);
    }


    public Long getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(Long selectionId) {
        this.selectionId = selectionId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public static final class Builder {
        private Long selectionId;
        private Long classId;
        private Long studentId;

        public Builder() {
        }

        public Builder selectionId(Long val) {
            selectionId = val;
            return this;
        }

        public Builder classId(Long val) {
            classId = val;
            return this;
        }

        public Builder studentId(Long val) {
            studentId = val;
            return this;
        }

        public ClassSelectionRecordDTO build() {
            return new ClassSelectionRecordDTO(this);
        }
    }
}
