package pers.jiangyinzuo.chat.client.javafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pers.jiangyinzuo.chat.client.javafx.controller.components.NoticeCmpController;
import pers.jiangyinzuo.chat.client.javafx.controller.proxy.ControllerProxy;
import pers.jiangyinzuo.chat.client.state.UserState;
import pers.jiangyinzuo.chat.domain.entity.Notice;
import pers.jiangyinzuo.chat.service.NoticeService;
import pers.jiangyinzuo.chat.service.impl.NoticeServiceImpl;

import java.io.IOException;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class NoticeBoardController implements NoticeCmpController.NoticeBoardContract {

    @FXML
    private VBox noticeBox;

    private NoticeService noticeService = new NoticeServiceImpl();

    private List<Notice> noticeList;

    @FXML
    public void initialize() {
        noticeList = noticeService.queryNoticeByUserId(UserState.getSingleton().getUser().getUserId());
        System.out.println(noticeList.size());
        initNoticeBox();
    }

    /**
     * 初始化消息盒子
     */
    private void initNoticeBox() {
        try {
            for (Notice notice : noticeList) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../scenes/components/" + "NoticeCmp.fxml"));
                Pane pane = fxmlLoader.load();
                NoticeCmpController noticeCmpController = fxmlLoader.getController();
                noticeCmpController.init(notice, this, pane);
                this.noticeBox.getChildren().add(pane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Pane pane) {
        this.noticeBox.getChildren().remove(pane);
        ControllerProxy.getMainBoardController().decreaseNewNoticeCount();
    }
}
