/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.explorewise.phase4.server.models;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author romainhovius
 */
@Entity
@Table(name = "OrderDetails")
@NamedQueries({
    @NamedQuery(name = "OrderDetails.findAll", query = "SELECT o FROM OrderDetails o"),
    @NamedQuery(name = "OrderDetails.findByOrderDetailID", query = "SELECT o FROM OrderDetails o WHERE o.orderDetailID = :orderDetailID"),
    @NamedQuery(name = "OrderDetails.findByOrderID", query = "SELECT o FROM OrderDetails o WHERE o.order.orderID = :orderID"),
    @NamedQuery(name = "OrderDetails.findByTravelerName", query = "SELECT o FROM OrderDetails o WHERE o.travelerName = :travelerName"),
    @NamedQuery(name = "OrderDetails.findByTravelerAge", query = "SELECT o FROM OrderDetails o WHERE o.travelerAge = :travelerAge")})
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "OrderDetailID")
    private Integer orderDetailID;
    @Column(name = "TravelerName")
    private String travelerName;
    @Column(name = "TravelerAge")
    private Integer travelerAge;
    
    @ManyToOne
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderID")
    private Orders order;
    
    public OrderDetails() {
    }
    
    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }
    public OrderDetails(Integer orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public Integer getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(Integer orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public String getTravelerName() {
        return travelerName;
    }

    public void setTravelerName(String travelerName) {
        this.travelerName = travelerName;
    }

    public Integer getTravelerAge() {
        return travelerAge;
    }

    public void setTravelerAge(Integer travelerAge) {
        this.travelerAge = travelerAge;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderDetailID != null ? orderDetailID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderDetails)) {
            return false;
        }
        OrderDetails other = (OrderDetails) object;
        if ((this.orderDetailID == null && other.orderDetailID != null) || (this.orderDetailID != null && !this.orderDetailID.equals(other.orderDetailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.explorewise.phase2_v2.models.OrderDetails[ orderDetailID=" + orderDetailID + " ]";
    }
    
}
