package ex.ch14.ex1402;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.font.*;
import javafx.stage.Stage;

public class GridStyle extends Application {

	Label lab1 = new Label("姓名");
	Label lab2 = new Label("职业:");
	Label lab3 = new Label("性别");
	Font font = new Font("Times New Roman", 20);
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(10, 8, 10, 8));
		pane.setHgap(5);
		pane.setVgap(5);
		lab1.setTextFill(Color.RED);
		lab1.setFont(font);
		lab2.setFont(new Font("黑体", 20));
		lab2.setStyle("-fx-border-color:blue");
		pane.add(lab1, 0, 0);
		pane.add(lab2, 0, 1);
		pane.add(lab3, 0, 2);
		pane.add(new TextField(), 1, 0);
		pane.add(new TextField(), 1, 1);
		pane.add(new TextField(), 1, 2);
		Button but = new Button("确认");
		pane.add(but, 1, 3);
		Scene scene = new Scene(pane);
		primaryStage.setTitle("网格与文本组件");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
