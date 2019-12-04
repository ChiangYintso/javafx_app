package ex.ch16.ex1603;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Jiang Yinzuo
 */
public class MyFan extends Application {

    private class FanPane extends Pane {
        private Circle c;
        private Arc[] blades = new Arc[4];
        private double increment = 1;
        FanPane(double radius) {
            setMinHeight(400);
            setMinWidth(400);
            c = new Circle(200, 200, radius, Color.TRANSPARENT);
            c.setStroke(Color.BLACK);
            double bladeRadius = radius * 0.9;
            for (int i = 0; i < blades.length; ++i) {
                blades[i] = new Arc(c.getCenterX(), c.getCenterY(),
                        bladeRadius, bladeRadius, i * 90 + 30, 35);
                blades[i].setFill(Color.RED);
                blades[i].setType(ArcType.ROUND);
            }
            getChildren().addAll(c);
            getChildren().addAll(blades);
        }

        private void spin() {
            for (Arc blade : blades) {
                double prevStartAngle = blade.getStartAngle();
                blade.setStartAngle(prevStartAngle + increment);
            }
        }
    }

    @Override
    public void start(Stage stage) {
        FanPane fanPane = new FanPane(100);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(10), e -> fanPane.spin());

        Timeline fanTimeLine = new Timeline(keyFrame);
        fanTimeLine.setCycleCount(Timeline.INDEFINITE);
        Button pause = new Button("停止");
        pause.setOnAction(e -> fanTimeLine.pause());
        Button resume = new Button("转动");
        resume.setOnAction(e -> fanTimeLine.play());
        Button increase = new Button("加速");
        increase.setOnAction(e -> {
            fanTimeLine.setRate(fanTimeLine.getCurrentRate() + 1);
        });
        Button decrease = new Button("减速");
        decrease.setOnAction(e -> {
            fanTimeLine.setRate(fanTimeLine.getCurrentRate() - 1);
        });

        Button reverse = new Button("反转");
        reverse.setOnAction(e -> fanPane.increment *= -1);
        HBox hButtons = new HBox(pause, resume, increase, decrease, reverse);
        hButtons.setSpacing(10);
        hButtons.setAlignment(Pos.CENTER);
        hButtons.setPadding(new Insets(10, 10, 10, 10));
        BorderPane borderPane = new BorderPane(fanPane, null, null, hButtons, null);
        stage.setScene(new Scene(borderPane));
        stage.setTitle("动画: 风扇");
        stage.show();
    }
}
