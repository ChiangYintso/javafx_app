package pers.jiangyinzuo.chat.domain.entity;

import pers.jiangyinzuo.chat.client.javafx.controller.components.SessionCardCmpController;
import pers.jiangyinzuo.chat.domain.dto.GroupMemberDTO;
import pers.jiangyinzuo.chat.domain.mapper.FieldMapper;
import pers.jiangyinzuo.chat.domain.mapper.TableMapper;
import pers.jiangyinzuo.chat.domain.repository.GroupRepo;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Jiang Yinzuo
 */
@TableMapper("chat_group")
public class Group implements SessionCardCmpController.Session {

    private static String DEFAULT_AVATAR_URL;

    @FieldMapper(name = "group_id")
    private Long groupId;

    @FieldMapper(name = "group_name")
    private String groupName;

    @FieldMapper(type = "reference", name = "master_user_id", joinName = "user_id")
    private User master;

    @FieldMapper(name = "group_avatar")
    private String avatar;

    @FieldMapper(name = "group_intro")
    private String groupIntro;

    @FieldMapper(name = "is_blocked")
    private Boolean isBlocked;

    private GroupRepo groupRepo;

    public Group(Long groupId, String groupName, User master, String avatar, String groupIntro, Boolean isBlocked) {
        this();
        this.groupId = groupId;
        this.groupName = groupName;
        this.master = master;
        this.avatar = avatar;
        this.groupRepo.updateMemberMap(groupId);
        this.isBlocked = isBlocked;
    }

    public Group() {
        this.groupRepo = new GroupRepo();
        DEFAULT_AVATAR_URL = "file:" + URLDecoder.decode(User.class.getClassLoader().getResource("avatar.png").getPath(), StandardCharsets.UTF_8);
    }

    private Group(Builder builder) {
        setGroupId(builder.groupId);
        setGroupName(builder.groupName);
        setMaster(builder.master);
        setAvatar(builder.avatar);
        setGroupIntro(builder.groupIntro);
        isBlocked = builder.isBlocked;
    }

    @Override
    public Boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public String getGroupIntro() {
        return groupIntro;
    }

    public void setGroupIntro(String groupIntro) {
        this.groupIntro = groupIntro;
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

    public User getMessageSendFrom(Long userId) {
        return this.groupRepo.getMemberMap(groupId, false).get(userId);
    }

    @Override
    public String getSessionName() {
        return getGroupName();
    }

    @Override
    public String getAvatar() {
        return avatar == null || "".equals(avatar) ? DEFAULT_AVATAR_URL : avatar;
    }

    @Override
    public Long getId() {
        return getGroupId();
    }

    @Override
    public String getStatus() {
        return isBlocked ? "·â½ûÖÐ" : "";
    }

    public Map<Long, User> getGroupMemberMap() {
        return groupRepo.getMemberMap(groupId, true);
    }

    public List<GroupMemberDTO> getGroupMemberList() {
        return groupRepo.getGroupMemberDTOList(groupId);
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public void changeBlockStat() {
        this.isBlocked = !isBlocked;
    }

    @Override
    public SessionCardCmpController.Session copy() {
        return new Group(groupId, groupName, master, avatar, groupIntro, isBlocked);
    }

    public static final class Builder {
        private Long groupId;
        private String groupName;
        private User master;
        private String avatar;
        private String groupIntro;
        private Boolean isBlocked;

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

        public Builder groupIntro(String val) {
            groupIntro = val;
            return this;
        }

        public Builder isBlocked(Boolean val) {
            isBlocked = val;
            return this;
        }

        public Group build() {
            return new Group(this);
        }
    }
}
