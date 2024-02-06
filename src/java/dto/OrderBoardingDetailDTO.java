/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class OrderBoardingDetailDTO {
    private String orderId;
    private RoomDTO room;
    private PetDTO pet;
    private Date checkInDate;
    private Date checkOutDate;

    public OrderBoardingDetailDTO() {
    }

    public OrderBoardingDetailDTO(String orderId, RoomDTO room, PetDTO pet, Date checkInDate, Date checkOutDate) {
        this.orderId = orderId;
        this.room = room;
        this.pet = pet;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }

    public PetDTO getPet() {
        return pet;
    }

    public void setPet(PetDTO pet) {
        this.pet = pet;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    
    
}
