package pers.jiangyinzuo.chat.dao;

public interface RoomDao {
//	SessionRoom queryRoomByRoomId(Integer roomId);
//
//	void insertRoom(SessionRoom room);

	void updateRoomByRoomId(Integer roomId);

	void deleteRoomByRoomId(Integer roomId);
}
