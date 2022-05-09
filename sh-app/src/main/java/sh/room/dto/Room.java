package sh.room.dto;

import java.math.BigDecimal;

public abstract class Room {

  protected boolean isRented;

  protected BigDecimal price;

  public abstract RoomType getType();

  public boolean isRented() {
    return this.isRented;
  }

  public void rent(BigDecimal price) {
    this.price = price;
    this.isRented = true;
  }


  public BigDecimal getRentPrice() {
    return this.price;
  }

}
