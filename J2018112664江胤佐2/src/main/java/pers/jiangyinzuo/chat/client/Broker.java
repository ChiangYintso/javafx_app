package pers.jiangyinzuo.chat.client;

import com.fasterxml.jackson.databind.JsonNode;
import pers.jiangyinzuo.chat.domain.entity.Message;

/**
 * @author Jiang Yinzuo
 */
public class Broker {
    public static void showMessage(JsonNode jsonNode) {
        System.out.println(jsonNode);
    }
}
