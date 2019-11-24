package main.java.pers.jiangyinzuo.rollcall.ui.javafx.utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

public class FXMLTool {
	public static Object loadFXMLComponent(String fileName) throws IOException {
		return FXMLLoader.load(FXMLTool.class.getResource("../scenes/components/" + fileName));
	}
}
