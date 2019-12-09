package pers.jiangyinzuo.chat.domain.repository;

import pers.jiangyinzuo.chat.helper.MySqlHelper;

import java.sql.SQLException;
import java.util.concurrent.*;

/**
 * @author Jiang Yinzuo
 */
public class MessageRepo {

    public static void shutDownThreadPool() {
        if (!threadPool.isShutdown()) {
            threadPool.shutdown();
        }
    }

    private static ExecutorService threadPool = new ThreadPoolExecutor(10, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(3));

    public static void updateUserSensitiveCount(Long userId, int count) {
        String sql = "UPDATE chat_user SET sensitive_words_count = sensitive_words_count + ? WHERE user_id = ?";
        threadPool.execute(() -> {
            MySqlHelper.executeUpdate(sql, count, userId);
        });
    }
}
