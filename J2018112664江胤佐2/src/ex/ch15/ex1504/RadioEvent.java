package ex.ch15.ex1504;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * @author Jiang Yinzuo
 */
public class RadioEvent extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Label resp = new Label("");
        FlowPane flowPane = new FlowPane(10, 10);
        flowPane.setAlignment(Pos.CENTER);
        RadioButton radioButton = new RadioButton("java");
        RadioButton radioButton1 = new RadioButton("python");
        RadioButton radioButton2 = new RadioButton("c++");
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton.setToggleGroup(toggleGroup);
        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);
        toggleGroup.selectedToggleProperty().addListener((c, o, n) -> {
            RadioButton radioButton3 = (RadioButton) n;
            resp.setText("你选择的是:" + radioButton3.getText());
        });

        radioButton.setSelected(true);
        flowPane.getChildren().addAll(radioButton2, radioButton, radioButton1, resp);

        Scene scene = new Scene(flowPane, 250, 120);
        stage.setScene(scene);
        stage.setTitle("单选按钮属性绑定事件");
        stage.show();
    }
}
