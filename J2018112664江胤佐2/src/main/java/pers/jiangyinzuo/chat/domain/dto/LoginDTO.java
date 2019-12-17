package pers.jiangyinzuo.chat.domain.dto;

import pers.jiangyinzuo.chat.domain.mapper.FieldMapper;

import java.sql.Timestamp;

public class LoginDTO {
    @FieldMapper(name = "log_id")
    private Long logId;

    @FieldMapper(name = "user_id")
    private Long userId;

    @FieldMapper(name = "log_time")
    private Timestamp logTime = new Timestamp(System.currentTimeMillis());

    public LoginDTO() {}

    public LoginDTO(Long userId) {
        this.userId = userId;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getLogTime() {
        return logTime;
    }

    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
    }
}
