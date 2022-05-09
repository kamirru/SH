package sh.room.dto;

public class RoomFactory {

  public static Room createRoom(RoomType roomType) {
    switch (roomType) {
      case Economy:
        return new EconomyRoom();
      case Premium:
        return new PremiumRoom();
      default:
        throw new IllegalStateException();
    }
  }

}
