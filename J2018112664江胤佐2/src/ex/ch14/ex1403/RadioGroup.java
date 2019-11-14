package ex.ch14.ex1403;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RadioGroup extends Application {
	final RadioButton rb1 = new RadioButton("红色");
	final RadioButton rb2 = new RadioButton("绿色");
	final RadioButton rb3 = new RadioButton("蓝色");
	final TextArea ta = new TextArea("我是文本区");

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		final ToggleGroup gro = new ToggleGroup();
		rb1.setToggleGroup(gro);
		rb2.setToggleGroup(gro);
		rb3.setToggleGroup(gro);
		HBox rgbPane = new HBox(10);
		rgbPane.getChildren().addAll(rb1, rb2, rb3);
		rgbPane.setAlignment(Pos.CENTER);
		
		BorderPane rootBP = new BorderPane();
		ta.setPrefColumnCount(15);
		ta.setPrefRowCount(3);
		ta.setWrapText(true);
		rootBP.setCenter(ta);
		rootBP.setBottom(rgbPane);
		Scene scene = new Scene(rootBP);
		primaryStage.setTitle("单选按钮");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
