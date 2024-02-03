

package dto;

import java.sql.Date;


public class PetDTO {
    private String petId;
    private String name;
    private Date dob;
    private String customerId;
    private boolean type;
    private double weight;
    private boolean gender;
    private boolean vaccinated;
    private boolean status;

    public PetDTO() {
    }

    public PetDTO(String petId, String name, Date dob, String customerId, boolean type, double weight, boolean gender, boolean vaccinated, boolean status) {
        this.petId = petId;
        this.name = name;
        this.dob = dob;
        this.customerId = customerId;
        this.type = type;
        this.weight = weight;
        this.gender = gender;
        this.vaccinated = vaccinated;
        this.status = status;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
}
