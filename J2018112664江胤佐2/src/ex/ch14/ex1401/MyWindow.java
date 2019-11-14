package ex.ch14.ex1401;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MyWindow extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane rootPane = new BorderPane();
		rootPane.setPadding(new Insets(8, 8, 8, 8));
		Image imb = new Image("file:///D:/stuspace/java2019a/J2018112664江胤佐2/src/ex/ch14/ex1401/flag.jpg");
		ImageView iv = new ImageView(imb);
		iv.setFitWidth(80);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
		iv.setCache(true);
		Pane pane = new Pane();
		pane.getChildren().add(iv);
		Button bt = new Button("北部区域");
		rootPane.setTop(bt);
		rootPane.setBottom(new Button("南部区域"));
		rootPane.setLeft(new Button("西部区域"));
		rootPane.setRight(new Button("东部区域"));
		rootPane.setCenter(pane);
		Scene scene = new Scene(rootPane);
		primaryStage.setTitle("界面布局");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
