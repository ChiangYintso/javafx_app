package pers.jiangyinzuo.chat.service;

/**
 * @author Jiang Yinzuo
 */
public interface MessageService {
    /**
     * ��message�������ݿ�
     * @param rawJson ���͸�TcpServer��ԭʼ�ֽ�����
     */
    void insertMessage(byte[] rawJson);
}
