package main.java.pers.jiangyinzuo.chat.dao;

import main.java.pers.jiangyinzuo.chat.entity.Room;

public interface RoomDao {
	Room queryRoomByRoomId(Integer roomId);

	void insertRoom(Room room);

	void updateRoomByRoomId(Integer roomId);

	void deleteRoomByRoomId(Integer roomId);
}
