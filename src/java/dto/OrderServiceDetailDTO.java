/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Admin
 */
public class OrderServiceDetailDTO {
    private String orderId;
    private ServiceDTO service;
    private PetDTO pet;

    public OrderServiceDetailDTO() {
    }

    public OrderServiceDetailDTO(String orderId, ServiceDTO service, PetDTO pet) {
        this.orderId = orderId;
        this.service = service;
        this.pet = pet;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public ServiceDTO getService() {
        return service;
    }

    public void setService(ServiceDTO service) {
        this.service = service;
    }

    public PetDTO getPet() {
        return pet;
    }

    public void setPet(PetDTO pet) {
        this.pet = pet;
    }
    
    
    
}
