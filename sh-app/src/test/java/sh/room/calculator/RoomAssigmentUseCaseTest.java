package sh.room.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sh.room.dto.Room;
import sh.room.dto.RoomFactory;
import sh.room.dto.RoomType;
import sh.room.provider.IFreeRoomProviderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RoomAssigmentUseCaseTest {

  @Test
  public void test1() {
    IFreeRoomProviderService service = new IFreeRoomProviderService() {
      public Map<RoomType, List<Room>> getFreeRoomsByCategory() {
        Map<RoomType, List<Room>> roomTypeListMap = new HashMap<>();
        roomTypeListMap.put(RoomType.Premium, createPremiumRooms(3));
        roomTypeListMap.put(RoomType.Economy, createEconomyRooms(3));
        return roomTypeListMap;
      }
    };

    RoomAssigmentUseCase roomAssigmentUseCase = new RoomAssigmentUseCase(service);
    Map<RoomType, List<Room>> roomTypeListMap = roomAssigmentUseCase.assginRooms(new ArrayList<Double>());
    BigDecimal sumP = roomTypeListMap.get(RoomType.Premium).stream().map(Room::getRentPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    Assertions.assertEquals(BigDecimal.valueOf(738d), sumP);
    BigDecimal sumE = roomTypeListMap.get(RoomType.Economy).stream().map(Room::getRentPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    Assertions.assertEquals(BigDecimal.valueOf(167.99d), sumE);

  }

  @Test
  public void test2() {
    IFreeRoomProviderService service = new IFreeRoomProviderService() {
      public Map<RoomType, List<Room>> getFreeRoomsByCategory() {
        Map<RoomType, List<Room>> roomTypeListMap = new HashMap<>();
        roomTypeListMap.put(RoomType.Premium, createPremiumRooms(7));
        roomTypeListMap.put(RoomType.Economy, createEconomyRooms(5));
        return roomTypeListMap;
      }
    };

    RoomAssigmentUseCase roomAssigmentUseCase = new RoomAssigmentUseCase(service);
    Map<RoomType, List<Room>> roomTypeListMap = roomAssigmentUseCase.assginRooms(new ArrayList<Double>());
    BigDecimal sumP = roomTypeListMap.get(RoomType.Premium).stream().map(Room::getRentPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    Assertions.assertEquals(BigDecimal.valueOf(1054d), sumP);
    BigDecimal sumE = roomTypeListMap.get(RoomType.Economy).stream().map(Room::getRentPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    Assertions.assertEquals(BigDecimal.valueOf(189.99d), sumE);

  }

  @Test
  public void test3() {
    IFreeRoomProviderService service = new IFreeRoomProviderService() {
      public Map<RoomType, List<Room>> getFreeRoomsByCategory() {
        Map<RoomType, List<Room>> roomTypeListMap = new HashMap<>();
        roomTypeListMap.put(RoomType.Premium, createPremiumRooms(2));
        roomTypeListMap.put(RoomType.Economy, createEconomyRooms(7));
        return roomTypeListMap;
      }
    };

    RoomAssigmentUseCase roomAssigmentUseCase = new RoomAssigmentUseCase(service);
    Map<RoomType, List<Room>> roomTypeListMap = roomAssigmentUseCase.assginRooms(new ArrayList<Double>());
    BigDecimal sumP = roomTypeListMap.get(RoomType.Premium).stream().map(Room::getRentPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    Assertions.assertEquals(BigDecimal.valueOf(583d), sumP);
    BigDecimal sumE = roomTypeListMap.get(RoomType.Economy).stream().map(Room::getRentPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    Assertions.assertEquals(BigDecimal.valueOf(189.99d), sumE);

  }

  @Test
  public void test4() {
    IFreeRoomProviderService service = new IFreeRoomProviderService() {
      public Map<RoomType, List<Room>> getFreeRoomsByCategory() {
        Map<RoomType, List<Room>> roomTypeListMap = new HashMap<>();
        roomTypeListMap.put(RoomType.Premium, createPremiumRooms(7));
        roomTypeListMap.put(RoomType.Economy, createEconomyRooms(1));
        return roomTypeListMap;
      }
    };

    RoomAssigmentUseCase roomAssigmentUseCase = new RoomAssigmentUseCase(service);
    Map<RoomType, List<Room>> roomTypeListMap = roomAssigmentUseCase.assginRooms(new ArrayList<Double>());
    BigDecimal sumP = roomTypeListMap.get(RoomType.Premium).stream().map(Room::getRentPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    Assertions.assertEquals(BigDecimal.valueOf(1153.99d), sumP);
    BigDecimal sumE = roomTypeListMap.get(RoomType.Economy).stream().map(Room::getRentPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    Assertions.assertEquals(BigDecimal.valueOf(45d), sumE);

  }

  private List<Room> createEconomyRooms(int howManyRooms) {
    return createRooms(new ArrayList<Room>(), howManyRooms, RoomType.Economy);
  }

  private List<Room> createPremiumRooms(int howManyRooms) {
    return createRooms(new ArrayList<Room>(), howManyRooms, RoomType.Premium);
  }

  private List<Room> createRooms(List<Room> rooms, int howManyRooms, RoomType roomType) {
    for (int i = 0; i < howManyRooms; i++) {
      rooms.add(RoomFactory.createRoom(roomType));
    }
    return rooms;
  }

}