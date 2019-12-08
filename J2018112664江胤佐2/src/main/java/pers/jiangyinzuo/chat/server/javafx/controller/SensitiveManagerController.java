package pers.jiangyinzuo.chat.server.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pers.jiangyinzuo.chat.common.javafx.CustomAlertBoard;
import pers.jiangyinzuo.chat.common.javafx.util.FxmlCmpLoaderUtil;
import pers.jiangyinzuo.chat.server.javafx.controller.components.SensitiveWordsCmpController;
import pers.jiangyinzuo.chat.service.MessageService;
import pers.jiangyinzuo.chat.service.impl.MessageServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class SensitiveManagerController implements SensitiveWordsCmpController.Parent {

    @FXML
    private TextField newSensitiveWord;

    @FXML
    private Button addSensitiveWordBtn;

    @FXML
    private VBox wordList;

    private MessageService messageService = new MessageServiceImpl();

    public List<String> getSensitiveWordList() {
        return sensitiveWordList;
    }

    public void replaceSensitiveWord(String oldWord, String newWord) {
        Collections.replaceAll(sensitiveWordList, oldWord, newWord);
    }

    private List<String> sensitiveWordList = new ArrayList<>();

    @FXML
    void addSensitiveWord(ActionEvent event) {
        if (newSensitiveWord.getText().isBlank()) {
            CustomAlertBoard.showAlert("敏感词不能为空");
        } else if (sensitiveWordList.contains(newSensitiveWord.getText())) {
            CustomAlertBoard.showAlert("已经拥有该敏感词");
        } else {
            addPane(newSensitiveWord.getText());
            sensitiveWordList.add(newSensitiveWord.getText());
            messageService.addSensitiveWord(newSensitiveWord.getText());
            newSensitiveWord.setText("");
        }
    }

    @FXML
    void initialize() {
        sensitiveWordList = messageService.querySensitiveWords();
        for (String word : sensitiveWordList) {
            addPane(word);
        }
    }

    private void addPane(String word) {
        FxmlCmpLoaderUtil<Pane, SensitiveWordsCmpController> fxmlCmpLoaderUtil
                = new FxmlCmpLoaderUtil<>("server",
                "SensitiveWordCmp.fxml", word, this);
        Pane pane = fxmlCmpLoaderUtil.getPane();
        fxmlCmpLoaderUtil.getController().setSelfPane(pane);
        wordList.getChildren().add(pane);
    }

    @Override
    public void removeWordPane(Pane pane) {
        wordList.getChildren().remove(pane);
    }

}
