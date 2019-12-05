package pers.jiangyinzuo.chat.domain.entity;

import pers.jiangyinzuo.chat.domain.mapper.FieldMapper;
import pers.jiangyinzuo.chat.domain.mapper.TableMapper;

/**
 * @author Jiang Yinzuo
 */
@TableMapper("chat_notice")
public class Notice {
    @FieldMapper(name = "notice_id")
    private Long noticeId;

    @FieldMapper(name = "notice_type")
    private Integer noticeType;

    @FieldMapper(name = "notice_json")
    private String noticeData;

    @FieldMapper(name = "send_to_id")
    private Long sendToId;

    public Notice() {}

    private Notice(Builder builder) {
        setNoticeId(builder.noticeId);
        noticeType = builder.noticeType;
        noticeData = builder.noticeData;
        sendToId = builder.sendToId;
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public static final class Builder {
        private Long noticeId;
        private Integer noticeType;
        private String noticeData;
        private Long sendToId;

        public Builder() {
        }

        public Builder noticeId(Long val) {
            noticeId = val;
            return this;
        }

        public Builder noticeType(Integer val) {
            noticeType = val;
            return this;
        }

        public Builder noticeData(String val) {
            noticeData = val;
            return this;
        }

        public Builder sendToId(Long val) {
            sendToId = val;
            return this;
        }

        public Notice build() {
            return new Notice(this);
        }
    }
}
