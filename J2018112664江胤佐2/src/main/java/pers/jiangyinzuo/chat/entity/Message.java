package main.java.pers.jiangyinzuo.chat.entity;

import java.time.Instant;

public class Message {
	private Instant time;
	private String text;
	private User sender;

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public Instant getTime() {
		return time;
	}

	public void setTime(Instant time) {
		this.time = time;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
