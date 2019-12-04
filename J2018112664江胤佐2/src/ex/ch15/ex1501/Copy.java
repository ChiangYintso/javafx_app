package ex.ch15.ex1501;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * @author Jiang Yinzuo
 */
public class Copy extends Application {

    Button b = new Button("复制");
    TextField L = new TextField();
    TextField R = new TextField();

    @Override
    public void start(Stage stage) throws Exception {
        L.setPrefColumnCount(10);
        R.setPrefColumnCount(10);
        FlowPane fp = new FlowPane(5, 5);
        fp.getChildren().addAll(L, R, b);
        b.setOnAction(e -> L.setText(R.getText()));

        Scene scene = new Scene(fp);
        stage.setTitle("动作事件");
        stage.setScene(scene);
        stage.show();
    }
}
