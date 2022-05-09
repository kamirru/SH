package sh.room.calculator;

import sh.room.dto.Room;
import sh.room.dto.RoomType;
import sh.room.provider.IFreeRoomProviderService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomAssigmentUseCase {

  public RoomAssigmentUseCase(final IFreeRoomProviderService iFreeRoomProviderService) {
    this.iFreeRoomProviderService = iFreeRoomProviderService;
  }

  private final IFreeRoomProviderService iFreeRoomProviderService;

  public Map<RoomType, List<Room>> assginRooms(List<Double> offeredPrices) {
    RoomType2PriceAssigner roomType2PriceAssigner = new RoomType2PriceAssigner();
    Map<RoomType, List<BigDecimal>> roomTypeListMap = roomType2PriceAssigner.splitPrices(offeredPrices);
    Map<RoomType, List<Room>> freeRoomsByCategory = iFreeRoomProviderService.getFreeRoomsByCategory();

    Map<RoomType, List<Room>> bookedRooms = new HashMap<>();

    SimpleRoomBooker simpleRoomBooker = new SimpleRoomBooker();
    Map<RoomType, List<Room>> bookRoomsPremium = simpleRoomBooker.bookRooms(roomTypeListMap, freeRoomsByCategory, RoomType.Premium);
    bookedRooms.put(RoomType.Premium, bookRoomsPremium.get(RoomType.Premium));

    EconomyToPremiumRoomBooker economyToPremiumRoomBooker = new EconomyToPremiumRoomBooker();
    Map<RoomType, List<Room>> upgradedMap = economyToPremiumRoomBooker.bookRooms(roomTypeListMap, freeRoomsByCategory);
    List<Room> rooms = bookRoomsPremium.get(RoomType.Premium);
    rooms.addAll(upgradedMap.get(RoomType.Premium));
    bookedRooms.put(RoomType.Premium, rooms);

    Map<RoomType, List<Room>> bookRoomsEconomy = simpleRoomBooker.bookRooms(roomTypeListMap, freeRoomsByCategory, RoomType.Economy);
    bookedRooms.put(RoomType.Economy, bookRoomsEconomy.get(RoomType.Economy));

    return bookedRooms;

  }

}
