/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.explorewise.phase4.server.models;

import jakarta.json.bind.annotation.JsonbTransient;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nathan
 */
@Entity
@Table(name = "Orders")
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findByOrderID", query = "SELECT o FROM Orders o WHERE o.orderID = :orderID"),
    @NamedQuery(name = "Orders.findByUserID", query = "SELECT o FROM Orders o WHERE o.user.userID = :userID"),
    @NamedQuery(name = "Orders.findByTripID", query = "SELECT o FROM Orders o WHERE o.trip.tripID = :tripID"),
    @NamedQuery(name = "Orders.findByTripIDAndUserID", query = "SELECT o FROM Orders o WHERE o.trip.tripID = :tripID AND o.user.userID = :userID"),
    @NamedQuery(name = "Orders.findByOrderDate", query = "SELECT o FROM Orders o WHERE o.orderDate = :orderDate"),
    @NamedQuery(name = "Orders.findByTotalAmount", query = "SELECT o FROM Orders o WHERE o.totalAmount = :totalAmount"),
    @NamedQuery(name = "Orders.findByNumberOfTravelers", query = "SELECT o FROM Orders o WHERE o.numberOfTravelers = :numberOfTravelers")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "OrderID")
    private Integer orderID;
    @Column(name = "tripID")
    private Integer tripID;
    @Column(name = "OrderDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TotalAmount")
    private Double totalAmount;
    @Column(name = "NumberOfTravelers")
    private Integer numberOfTravelers;
    
    @JsonbTransient
    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private Users user;
    
    @JsonbTransient
    @ManyToOne
    @JoinColumn(name = "tripID", insertable = false, updatable = false)
    private Trips trip;
    
    @JsonbTransient
    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orderDetails;
    
    
    
   public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Trips getTrip() {
        return trip;
    }

    public void setTrip(Trips trip) {
        this.trip = trip;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
    
    public Orders() {
    }

    public Orders(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }
    
    public Integer getTripID() {
        return tripID;
    }

    public void setTripID(Integer tripID) {
        this.tripID = tripID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getNumberOfTravelers() {
        return numberOfTravelers;
    }

    public void setNumberOfTravelers(Integer numberOfTravelers) {
        this.numberOfTravelers = numberOfTravelers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderID != null ? orderID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.orderID == null && other.orderID != null) || (this.orderID != null && !this.orderID.equals(other.orderID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.explorewise.phase2_v2.models.Orders[ orderID=" + orderID + " ]";
    }
    
}
