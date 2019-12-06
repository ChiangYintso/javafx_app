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
     * ��Ϣʵ����
     */
    private Notice notice;

    private FriendService friendService = new FriendServiceImpl();

    /**
     * ������ID
     */
    private Integer sendFromId;

    /**
     * ��ǰ�û�ʵ����
     */
    private User self = UserState.getSingleton().getUser();

    @FXML
    void onAgree(ActionEvent event) {
        // ͬ��Ӻ���
        if (notice.getNoticeType().equals(JsonHelper.Option.ADD_FRIEND)) {

            // ���ݿ���Ӻ���
            friendService.addFriend(sendFromId.longValue());

            // ���º����б�
            ControllerProxy.getMainBoardController().loadTreeView();

            // ���͸�������
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
        // ɾ�����ݿ��еļ�¼
        noticeService.deleteNotice(notice.getNoticeId());

        // �Ƴ�UI�ϵ����
        noticeBoardControllerCallBack.remove(pane);
    }

    /**
     * ��Ϣ���Ļص������ӿ�
     */
    public interface NoticeBoardContract {
        /**
         * �Ƴ���֪ͨ
         *
         * @param pane
         */
        void remove(Pane pane);
    }

    public interface MainBoardContract {
        /**
         * ���¼��غ����б�
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
                content.setText("�û�" + sendFromId + "�����Ϊ����");
                break;
            case JsonHelper.Option.AGREE_TO_ADD_FRIEND:
                content.setText("�û�" + sendFromId + "ͬ���Ϊ����");
                agree.setText("�Ѷ�");
                reject.setVisible(false);
                break;
            default:
                break;
        }
    }
}
