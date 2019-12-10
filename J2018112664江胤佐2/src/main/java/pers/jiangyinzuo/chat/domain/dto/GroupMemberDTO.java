package pers.jiangyinzuo.chat.domain.dto;

import pers.jiangyinzuo.chat.domain.mapper.FieldMapper;

/**
 * 群成员数据传输对象
 *
 * @author Jiang Yinzuo
 */
public class GroupMemberDTO {
    @FieldMapper(name = "chat_user.user_id")
    private Long userId;

    @FieldMapper(name = "chat_user.user_name")
    private String userName;

    @FieldMapper(name = "chat_user_group_relation.user_privilege")
    private Long privilege;

    public GroupMemberDTO() {}

    public GroupMemberDTO(Long userId, String userName, Long privilege) {
        this.userId = userId;
        this.userName = userName;
        this.privilege = privilege;
    }

    private GroupMemberDTO(Builder builder) {
        setUserId(builder.userId);
        setUserName(builder.userName);
        setPrivilege(builder.privilege);
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Long privilege) {
        this.privilege = privilege;
    }

    public static final class Builder {
        private Long userId;
        private String userName;
        private Long privilege;

        public Builder() {
        }

        public Builder userId(Long val) {
            userId = val;
            return this;
        }

        public Builder userName(String val) {
            userName = val;
            return this;
        }

        public Builder privilege(Long val) {
            privilege = val;
            return this;
        }

        public GroupMemberDTO build() {
            return new GroupMemberDTO(this);
        }
    }
}
