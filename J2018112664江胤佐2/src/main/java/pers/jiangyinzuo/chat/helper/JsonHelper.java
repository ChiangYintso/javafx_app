package pers.jiangyinzuo.chat.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.jiangyinzuo.chat.domain.entity.Message;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * ���������ҵ�Server��Client֮����Json��������Ϣ��
 * Json�������"option"���ԣ�ֵ����Ϊ"login"��"logout"��"message"���ַ���
 * @author Jiang Yinzuo
 */
public class JsonHelper {

    /**
     * ��¼�ǳ�
     * @param option "login"��"logout"
     * @param userId ���е�¼��ǳ��������û�id
     * @return �ֽ�����
     * @throws JsonProcessingException ת��ΪJson��ʽʱ�����쳣
     */
    public static byte[] writeLogBytes(String option, Long userId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>(10);
        map.put("option", option);
        map.put("userId", userId);
        return objectMapper.writeValueAsBytes(map);
    }

    /**
     * ����Ϣ
     * @param messageType ��Ϣ����
     * @param messageContent ��Ϣ����
     * @param sendFrom ���ͷ�id
     * @param sendTo ���շ�id
     * @return �ֽ�����
     * @throws JsonProcessingException ת��ΪJson��ʽʱ�����쳣
     */
    public static byte[] sendMessage(Integer messageType, String messageContent, Long sendFrom, Long sendTo) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        Message message = new Message.Builder()
                .messageType(messageType)
                .messageContent(messageContent)
                .sendFrom(sendFrom)
                .sendTo(sendTo)
                .sendTime(new Timestamp(0))
                .build();
        map.put("option", "message");
        map.put("data", message);

        return objectMapper.writeValueAsBytes(map);
    }

    public static Integer getMessageSendTo(JsonNode jsonNode) {
        return jsonNode.get("data").get("userId").asInt();
    }
}
