package pers.jiangyinzuo.chat.domain.entity;

import pers.jiangyinzuo.chat.domain.mapper.FieldMapper;
import pers.jiangyinzuo.chat.domain.mapper.TableMapper;

/**
 * @author Jiang Yinzuo
 */
@TableMapper("chat_notice")
public class Notice {

    public static final String ADD_FRIEND = "addFriend";
    public static final String GROUP_FOUNDED = "groupFounded";

    @FieldMapper(name = "notice_id")
    private Long noticeId;

    @FieldMapper(name = "notice_type")
    private String noticeType;

    @FieldMapper(name = "notice_data")
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

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getNoticeData() {
        return noticeData;
    }

    public void setNoticeData(String noticeData) {
        this.noticeData = noticeData;
    }

    public Long getSendToId() {
        return sendToId;
    }

    public void setSendToId(Long sendToId) {
        this.sendToId = sendToId;
    }

    public static final class Builder {
        private Long noticeId;
        private String noticeType;
        private String noticeData;
        private Long sendToId;

        public Builder() {
        }

        public Builder noticeId(Long val) {
            noticeId = val;
            return this;
        }

        public Builder noticeType(String val) {
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
