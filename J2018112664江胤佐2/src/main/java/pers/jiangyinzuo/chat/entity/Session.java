package main.java.pers.jiangyinzuo.chat.entity;

public interface Session {
	String getSessionName();

	String getAvatarUrl();
	
	static String DEFAULT_AVATAR_URL = "file:src/main/java/pers/jiangyinzuo/chat/ui/javafx/scenes/static/avatar.png";
}
