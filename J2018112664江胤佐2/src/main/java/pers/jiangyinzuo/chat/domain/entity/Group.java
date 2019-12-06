package pers.jiangyinzuo.chat.domain.entity;

import pers.jiangyinzuo.chat.client.javafx.controller.components.SessionCardCmpController;
import pers.jiangyinzuo.chat.domain.mapper.FieldMapper;
import pers.jiangyinzuo.chat.domain.mapper.TableMapper;
import pers.jiangyinzuo.chat.domain.repository.GroupRepo;

/**
 * @author Jiang Yinzuo
 */
@TableMapper("chat_group")
public class Group implements SessionCardCmpController.Session {

	@FieldMapper(name = "group_id")
    private Long groupId;

    @FieldMapper(name = "group_name")
    private String groupName;

    @FieldMapper(type = "reference", name = "master_user_id", joinName = "user_id")
    private User master;

    @FieldMapper(name = "group_avatar")
    private String avatar;

    private GroupRepo groupRepo;

    public Group(Long groupId, String groupName, User master, String avatar) {
        this();
        this.groupId = groupId;
        this.groupName = groupName;
        this.master = master;
        this.avatar = avatar;
    }

    public Group() {
        this.groupRepo = new GroupRepo();
    }

    private Group(Builder builder) {
        this();
        setGroupId(builder.groupId);
        setGroupName(builder.groupName);
        setMaster(builder.master);
        setAvatar(builder.avatar);
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    @Override
    public String getName() {
        return getGroupName();
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public static final class Builder {
        private Long groupId;
        private String groupName;
        private User master;
        private String avatar;

        public Builder() {
        }

        public Builder groupId(Long val) {
            groupId = val;
            return this;
        }

        public Builder groupName(String val) {
            groupName = val;
            return this;
        }

        public Builder master(User val) {
            master = val;
            return this;
        }

        public Builder avatar(String val) {
            avatar = val;
            return this;
        }

        public Group build() {
            return new Group(this);
        }
    }
}
