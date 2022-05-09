package sh.room.provider;

import sh.room.dto.Room;
import sh.room.dto.RoomType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FreeRoomProviderService implements IFreeRoomProviderService {

  public Map<RoomType, List<Room>> getFreeRoomsByCategory() {
    throw new NotImplementedException();
  }

}
