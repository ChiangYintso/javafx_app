package ex.ch16.ex1601;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/**
 * @author Jiang Yinzuo
 */
public class ShapePoly extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Ellipse e = new Ellipse(50, 50, 35, 25);
        Polygon pg = new Polygon(new double[] {135, 15, 160, 30,
        160, 60, 135, 75, 110, 60, 110, 30});
        e.setFill(null);
        e.setStroke(Color.RED);
        pg.setFill(Color.PINK);
        pg.setStroke(Color.BLUE);
        Pane pane = new Pane();
        pane.setPrefSize(200, 100);
        pane.getChildren().addAll(e, pg);
        Scene scene = new Scene(pane, 200, 100);
        stage.setScene(scene);
        stage.setTitle("»æÖÆÐÎ×´");
        stage.show();
    }
}
