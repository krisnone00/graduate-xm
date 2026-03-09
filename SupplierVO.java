package com.example.dto.response;

import java.time.LocalDateTime;

public class SupplierVO {
    private Integer id;
    private String supplierCode;
    private String supplierName;
    private Integer supplierType;
    private String supplierTypeName; // 1-生产商, 2-批发商...
    private String contactPerson;
    private String contactPhone;
    private String contactEmail;
    private String address;
    private Integer status;
    private String statusName;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    // Getter & Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getSupplierCode() { return supplierCode; }
    public void setSupplierCode(String supplierCode) { this.supplierCode = supplierCode; }
    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
    public Integer getSupplierType() { return supplierType; }
    public void setSupplierType(Integer supplierType) { this.supplierType = supplierType; }
    public String getSupplierTypeName() { return supplierTypeName; }
    public void setSupplierTypeName(String supplierTypeName) { this.supplierTypeName = supplierTypeName; }
    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public String getStatusName() { return statusName; }
    public void setStatusName(String statusName) { this.statusName = statusName; }
    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }
    public LocalDateTime getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(LocalDateTime updatedTime) { this.updatedTime = updatedTime; }
}