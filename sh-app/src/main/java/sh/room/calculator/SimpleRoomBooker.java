package sh.room.calculator;

import sh.room.dto.Room;
import sh.room.dto.RoomType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SimpleRoomBooker {

  public Map<RoomType, List<Room>> bookRooms(Map<RoomType, List<BigDecimal>> roomTypeListMap, Map<RoomType, List<Room>> freeRoomsByCategory, RoomType roomType) {
    Map<RoomType, List<Room>> bookedRooms = new HashMap<>();
    bookedRooms.put(roomType, new ArrayList<>());
    List<BigDecimal> roomOfferedPrices = roomTypeListMap.get(roomType);
    Collections.sort(roomOfferedPrices, Collections.reverseOrder());
    Iterator<BigDecimal> iterator = roomOfferedPrices.iterator();
    while (iterator.hasNext()) {
      BigDecimal price = iterator.next();
      for (Room room : freeRoomsByCategory.get(roomType)) {
        if (room.getType() == roomType && !room.isRented()) {
          room.rent(price);
          List<Room> premiumRooms = bookedRooms.get(roomType);
          premiumRooms.add(room);
          bookedRooms.put(roomType, premiumRooms);
          break;
        }
      }
      iterator.remove();
    }
    return bookedRooms;
  }

}
