package ex.ch15.ex1503;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Jiang Yinzuo
 */
public class MyKeyEvent extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        StackPane stackPane = new StackPane();
        TextField tf = new TextField();
        tf.setOnKeyReleased(e -> tf.setText("释放按键:" + e.getText()));
        stackPane.getChildren().add(tf);

        Scene scene = new Scene(stackPane, 180, 60);
        stage.setScene(scene);
        stage.setTitle("键盘事件");
        stage.show();
    }
}
