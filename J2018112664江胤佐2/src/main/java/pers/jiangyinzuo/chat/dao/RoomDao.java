package pers.jiangyinzuo.chat.dao;

import pers.jiangyinzuo.chat.domain.entity.Room;

public interface RoomDao {
	Room queryRoomByRoomId(Integer roomId);

	void insertRoom(Room room);

	void updateRoomByRoomId(Integer roomId);

	void deleteRoomByRoomId(Integer roomId);
}
