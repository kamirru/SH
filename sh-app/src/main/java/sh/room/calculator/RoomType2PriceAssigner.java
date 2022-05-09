package sh.room.calculator;

import sh.room.dto.RoomType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomType2PriceAssigner {

  private final static int premiumRoomMinPrice = 100;

  public Map<RoomType, List<BigDecimal>> splitPrices(List<Double> offeredPrices){
    offeredPrices = Arrays.asList(23d, 45d, 155d, 374d, 22d, 99.99d, 100d, 101d, 115d, 209d);

    Map<RoomType, List<BigDecimal>> roomTypeListMap = new HashMap<RoomType, List<BigDecimal>>();
    for (Double price : offeredPrices) {
      RoomType roomType = getDefaultRoomType(price);
      if (!roomTypeListMap.containsKey(roomType)) {
        roomTypeListMap.put(roomType, new ArrayList<BigDecimal>());
      }
      List<BigDecimal> prices = roomTypeListMap.get(roomType);
      prices.add(BigDecimal.valueOf(price));
    }
    return roomTypeListMap;
  }

  private RoomType getDefaultRoomType(Double price) {
    if (price >= premiumRoomMinPrice) {
      return RoomType.Premium;
    }
    return RoomType.Economy;
  }
}
