package ex.ch15.ex1502;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

/**
 * @author Jiang Yinzuo
 */
public class MyMouseEvent extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = new BorderPane();
        Text tf = new Text();
        Circle c = new Circle(50, Color.RED);
        c.setOnMouseEntered(e -> tf.setText("鼠标进入"));
        c.setOnMouseExited(e -> tf.setText("鼠标离开"));
        c.setOnMousePressed(e -> tf.setText("鼠标按下"));
        borderPane.setCenter(c);

        borderPane.setBottom(tf);

        Scene scene = new Scene(borderPane, 200, 150);
        stage.setTitle("鼠标事件");
        stage.setScene(scene);
        stage.show();
    }
}
