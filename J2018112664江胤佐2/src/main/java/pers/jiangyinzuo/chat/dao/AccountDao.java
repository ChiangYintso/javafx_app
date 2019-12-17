package pers.jiangyinzuo.chat.dao;

import pers.jiangyinzuo.chat.domain.dto.LoginDTO;

import java.util.List;

public interface AccountDao {
    void login(LoginDTO dto);

    List<LoginDTO> queryLogByUserId(Long userId);
}
