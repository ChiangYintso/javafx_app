package pers.jiangyinzuo.chat.client.javafx.controller.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.client.javafx.Main;
import pers.jiangyinzuo.chat.client.javafx.controller.NoticeBoardController;
import pers.jiangyinzuo.chat.client.javafx.controller.proxy.ControllerProxy;
import pers.jiangyinzuo.chat.client.state.UserState;
import pers.jiangyinzuo.chat.domain.entity.Notice;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.helper.JsonHelper;
import pers.jiangyinzuo.chat.service.FriendService;
import pers.jiangyinzuo.chat.service.NoticeService;
import pers.jiangyinzuo.chat.service.impl.FriendServiceImpl;
import pers.jiangyinzuo.chat.service.impl.NoticeServiceImpl;

/**
 * @author Jiang Yinzuo
 */
public class NoticeCmpController {

    @FXML
    private Text content;

    @FXML
    private Button agree;

    @FXML
    private Button reject;

    private static ObjectMapper objectMapper = new ObjectMapper();

    private NoticeService noticeService = new NoticeServiceImpl();

    private NoticeBoardController noticeBoardControllerCallBack;

    private Pane pane;

    /**
     * 消息实体类
     */
    private Notice notice;

    private FriendService friendService = new FriendServiceImpl();

    /**
     * 发件人ID
     */
    private Integer sendFromId;

    /**
     * 当前用户实体类
     */
    private User self = UserState.getSingleton().getUser();

    @FXML
    void onAgree(ActionEvent event) {
        // 同意加好友
        if (notice.getNoticeType().equals(JsonHelper.Option.ADD_FRIEND)) {

            // 数据库添加好友
            friendService.addFriend(sendFromId.longValue());

            // 更新好友列表
            ControllerProxy.getMainBoardController().loadTreeView();

            // 回送给发送人
            Main.getClientThreadPool().execute(() -> {
                Main.getTcpClient().sendMessage(JsonHelper.generateNotice(JsonHelper.Option.AGREE_TO_ADD_FRIEND, self.getUserId(), sendFromId.longValue()));
            });
        }
        handleNotice();
    }

    @FXML
    void onReject(ActionEvent event) {
        handleNotice();
    }

    private void handleNotice() {
        // 删除数据库中的记录
        noticeService.deleteNotice(notice.getNoticeId());

        // 移除UI上的面板
        noticeBoardControllerCallBack.remove(pane);
    }

    /**
     * 消息面板的回调方法接口
     */
    public interface NoticeBoardContract {
        /**
         * 移除此通知
         *
         * @param pane
         */
        void remove(Pane pane);
    }

    public interface MainBoardContract {
        /**
         * 重新加载好友列表
         */
        void loadTreeView();
    }

    public void init(Notice notice, NoticeBoardController noticeBoardControllerCallBack, Pane pane) {
        this.noticeBoardControllerCallBack = noticeBoardControllerCallBack;
        this.pane = pane;
        this.notice = notice;
        try {
            this.sendFromId = objectMapper.readTree(notice.getNoticeData()).get("sendFrom").asInt();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        switch (notice.getNoticeType()) {
            case JsonHelper.Option.ADD_FRIEND:
                content.setText("用户" + sendFromId + "请求加为好友");
                break;
            case JsonHelper.Option.AGREE_TO_ADD_FRIEND:
                content.setText("用户" + sendFromId + "同意加为好友");
                agree.setText("已读");
                reject.setVisible(false);
                break;
            default:
                break;
        }
    }
}
