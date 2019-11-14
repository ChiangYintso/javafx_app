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
		Image imb = new Image("file:///D:/stuspace/java2019a/J2018112664��ط��2/src/ex/ch14/ex1401/flag.jpg");
		ImageView iv = new ImageView(imb);
		iv.setFitWidth(80);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
		iv.setCache(true);
		Pane pane = new Pane();
		pane.getChildren().add(iv);
		Button bt = new Button("��������");
		rootPane.setTop(bt);
		rootPane.setBottom(new Button("�ϲ�����"));
		rootPane.setLeft(new Button("��������"));
		rootPane.setRight(new Button("��������"));
		rootPane.setCenter(pane);
		Scene scene = new Scene(rootPane);
		primaryStage.setTitle("���沼��");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
