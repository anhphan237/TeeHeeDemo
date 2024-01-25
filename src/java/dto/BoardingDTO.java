/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Admin
 */
public class BoardingDTO {
    private String boardingId;
    private String name;
    private double rate;
    private String description;
    private String img;
    private double length;
    private double height;
    private double width;
    private double maxWeight;
    private boolean status;

    public BoardingDTO() {
    }

    public BoardingDTO(String boardingId, String name, double rate, String description, String img, double length, double height, double width, double maxWeight, boolean status) {
        this.boardingId = boardingId;
        this.name = name;
        this.rate = rate;
        this.description = description;
        this.img = img;
        this.length = length;
        this.height = height;
        this.width = width;
        this.maxWeight = maxWeight;
        this.status = status;
    }

    public String getBoardingId() {
        return boardingId;
    }

    public void setBoardingId(String boardingId) {
        this.boardingId = boardingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
