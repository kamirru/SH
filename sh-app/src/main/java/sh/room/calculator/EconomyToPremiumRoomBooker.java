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

public class EconomyToPremiumRoomBooker {

  public Map<RoomType, List<Room>> bookRooms(Map<RoomType, List<BigDecimal>> roomTypeListMap, Map<RoomType, List<Room>> freeRoomsByCategory) {
    Map<RoomType, List<Room>> bookedRooms = new HashMap<>();
    bookedRooms.put(RoomType.Premium, new ArrayList<>());

    int economyCustomerSize = roomTypeListMap.get(RoomType.Economy).size();
    int economyRoomSize = freeRoomsByCategory.get(RoomType.Economy).size();
    if (economyRoomSize >= economyCustomerSize) {
      return bookedRooms;
    }

    List<Room> premiumRooms = freeRoomsByCategory.get(RoomType.Premium);
    boolean areThereAnyFreePremiumRooms = premiumRooms.stream().anyMatch(room -> !room.isRented());

    if (!areThereAnyFreePremiumRooms) {
      return bookedRooms;
    }

    List<BigDecimal> roomOfferedPrices = roomTypeListMap.get(RoomType.Economy);
    Collections.sort(roomOfferedPrices, Collections.reverseOrder());
    Iterator<BigDecimal> iterator = roomOfferedPrices.iterator();
    while (iterator.hasNext()) {
      BigDecimal price = iterator.next();
      for (Room room : freeRoomsByCategory.get(RoomType.Premium)) {
        if (room.getType() == RoomType.Premium && !room.isRented()) {
          room.rent(price);
          List<Room> list = bookedRooms.get(RoomType.Premium);
          list.add(room);
          bookedRooms.put(RoomType.Premium, list);
          iterator.remove();
          break;
        }
      }
    }
    return bookedRooms;
  }

}
