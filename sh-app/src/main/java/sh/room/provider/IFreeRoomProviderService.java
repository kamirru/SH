package sh.room.provider;

import sh.room.dto.Room;
import sh.room.dto.RoomType;

import java.util.List;
import java.util.Map;

public interface IFreeRoomProviderService {
  Map<RoomType, List<Room>> getFreeRoomsByCategory();
}
