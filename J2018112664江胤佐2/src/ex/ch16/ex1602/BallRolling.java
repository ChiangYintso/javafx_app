package ex.ch16.ex1602;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class BallRolling extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Arc arc = new Arc(150, 50, 80, 80, 225, 90);
        arc.setFill(null);
        arc.setStroke(Color.WHITE);
        arc.setType(ArcType.OPEN);
        Arc arc1 = new Arc(150, 50, 90, 90, 225, 90);
        arc1.setFill(null);
        arc1.setStroke(Color.RED);
        arc1.setType(ArcType.OPEN);
        Circle c = new Circle(10);
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(3000));
        pt.setOrientation(PathTransition.OrientationType.NONE);
        pt.setCycleCount(Animation.INDEFINITE);
        pt.setNode(c);
        pt.setAutoReverse(true);
        pt.play();
        pane.getChildren().addAll(arc, arc1, c);
        Scene scene = new Scene(pane, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Ð¡Çò¹ö¶¯");
        stage.show();
    }
}
