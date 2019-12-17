package pers.jiangyinzuo.chat.dao.mysql;

import pers.jiangyinzuo.chat.dao.AccountDao;
import pers.jiangyinzuo.chat.domain.dto.LoginDTO;
import pers.jiangyinzuo.chat.helper.MySqlHelper;

import java.util.List;

public class AccountDaoImpl implements AccountDao {
    @Override
    public void login(LoginDTO dto) {
        String sql = "INSERT INTO chat_login_log(user_id, log_time) VALUES (?, ?)";
        MySqlHelper.executeUpdate(sql, dto.getUserId(), dto.getLogTime());
    }

    @Override
    public List<LoginDTO> queryLogByUserId(Long userId) {
        String sql = "SELECT * FROM chat_login_log WHERE user_id = ?";
        return MySqlHelper.queryMany(LoginDTO.class, sql, userId);
    }
}
