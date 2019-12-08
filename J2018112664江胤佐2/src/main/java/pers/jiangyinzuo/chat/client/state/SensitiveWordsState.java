package pers.jiangyinzuo.chat.client.state;

import pers.jiangyinzuo.chat.service.MessageService;
import pers.jiangyinzuo.chat.service.impl.MessageServiceImpl;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class SensitiveWordsState {
    private static List<String> sensitiveWords;

    public static List<String> getSensitiveWords() {
        if (sensitiveWords == null) {
            loadSensitiveWords();
        }
        return sensitiveWords;
    }

    public static void setSensitiveWords(List<String> sensitiveWords) {
        SensitiveWordsState.sensitiveWords = sensitiveWords;
    }

    /**
     * 用户登录时, 加载敏感词列表
     */
    public static void loadSensitiveWords() {
        MessageService messageService = new MessageServiceImpl();
        sensitiveWords = messageService.getSensitiveWords();
    }
}
