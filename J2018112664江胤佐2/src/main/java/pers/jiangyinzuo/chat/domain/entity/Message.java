package pers.jiangyinzuo.chat.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.Timestamp;

/**
 * @author Jiang Yinzuo
 */
public class Message {
	@JsonIgnore
	private Long messageId;

	/**
	 * 消息类型 1: 文本 2: 图片 3: 文件
	 */
	private Integer messageType;
	private String messageContent;
	private Timestamp sendTime;

	private Long sendFrom;
	private Long sendTo;

	public Message(Long messageId, Integer messageType, String messageContent, Timestamp sendTime, Long sendFrom, Long sendTo) {
		this.messageId = messageId;
		this.messageType = messageType;
		this.messageContent = messageContent;
		this.sendTime = sendTime;
		this.sendFrom = sendFrom;
		this.sendTo = sendTo;
	}

	public Message() {}

	/**
	 * JsonNode构造Message对象
	 * @param data JSON中的data字段
	 * @return Message实体类
	 */
	public static Message parseToMessage(JsonNode data) {
		try {
			return new ObjectMapper().readValue(data.toString(), Message.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将原始的字节码转换成Message实体类
	 * @param bytes 字节码
	 * @return Message实体类
	 */
	public static Message parseToMessageEntity(byte[] bytes) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode jsonNode = objectMapper.readTree(bytes).get("data");
			return objectMapper.readValue(jsonNode.toString(), Message.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Message(Builder builder) {
		setMessageId(builder.messageId);
		setMessageType(builder.messageType);
		setMessageContent(builder.messageContent);
		setSendTime(builder.sendTime);
		setSendFrom(builder.sendFrom);
		setSendTo(builder.sendTo);
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Timestamp getSendTime() {
		return sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	public Long getSendFrom() {
		return sendFrom;
	}

	public void setSendFrom(Long sendFrom) {
		this.sendFrom = sendFrom;
	}

	public Long getSendTo() {
		return sendTo;
	}

	public void setSendTo(Long sendTo) {
		this.sendTo = sendTo;
	}

	public static final class Builder {
		private Long messageId;
		private Integer messageType;
		private String messageContent;
		private Timestamp sendTime;
		private Long sendFrom;
		private Long sendTo;

		public Builder() {
		}

		public Builder messageId(Long val) {
			messageId = val;
			return this;
		}

		public Builder messageType(Integer val) {
			messageType = val;
			return this;
		}

		public Builder messageContent(String val) {
			messageContent = val;
			return this;
		}

		public Builder sendTime(Timestamp val) {
			sendTime = val;
			return this;
		}

		public Builder sendFrom(Long val) {
			sendFrom = val;
			return this;
		}

		public Builder sendTo(Long val) {
			sendTo = val;
			return this;
		}

		public Message build() {
			return new Message(this);
		}
	}
}
