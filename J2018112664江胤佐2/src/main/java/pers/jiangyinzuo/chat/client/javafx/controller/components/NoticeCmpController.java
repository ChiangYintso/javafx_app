package pers.jiangyinzuo.chat.client.javafx.controller.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.w3c.dom.Node;
import pers.jiangyinzuo.chat.client.javafx.controller.NoticeBoardController;
import pers.jiangyinzuo.chat.client.javafx.controller.proxy.ControllerProxy;
import pers.jiangyinzuo.chat.domain.entity.Notice;
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

    private Notice notice;

    private FriendService friendService = new FriendServiceImpl();

    private Integer sendFrom;

    @FXML
    void onAgree(ActionEvent event) {
        // 同意加好友
        if (notice.getNoticeType().equals(JsonHelper.Option.ADD_FRIEND)) {
            friendService.addFriend(sendFrom.longValue());
            ControllerProxy.getMainBoardController().loadTreeView();
        }
        handleNotice();
    }

    @FXML
    void onReject(ActionEvent event) {
        handleNotice();
    }

    private void handleNotice() {
        // TODO 删除数据库中的记录
        noticeBoardControllerCallBack.remove(pane);
    }

    public interface NoticeBoardContract {
        /**
         * 移除此通知
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
            this.sendFrom = objectMapper.readTree(notice.getNoticeData()).get("sendFrom").asInt();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        switch (notice.getNoticeType()) {
            case JsonHelper.Option
                    .ADD_FRIEND:
                content.setText("用户" + sendFrom + "请求加为好友");
            default:
                break;
        }
    }
}
