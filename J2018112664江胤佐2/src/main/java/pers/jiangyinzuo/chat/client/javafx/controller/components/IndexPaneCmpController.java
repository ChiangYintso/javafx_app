package pers.jiangyinzuo.chat.client.javafx.controller.components;


import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 * @author Jiang Yinzuo
 */

public class IndexPaneCmpController {

    @FXML
    private Text indexText;

    public void init(String text) {
        indexText.setText(text);
    }
}
