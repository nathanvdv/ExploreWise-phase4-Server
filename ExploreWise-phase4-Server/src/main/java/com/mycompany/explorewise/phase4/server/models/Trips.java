/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.explorewise.phase4.server.models;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
import jakarta.persistence.Query;


/**
 *
 * @author romainhovius
 */
@Entity
@Table(name = "Trips")
@NamedQueries({
    @NamedQuery(name = "Trips.findAll", query = "SELECT t FROM Trips t"),
    @NamedQuery(name = "Trips.findByTripID", query = "SELECT t FROM Trips t WHERE t.tripID = :tripID"),
    @NamedQuery(name = "Trips.findByCityID", query = "SELECT t FROM Trips t WHERE t.city.cityID = :cityID"),
    @NamedQuery(name = "Trips.findByTripName", query = "SELECT t FROM Trips t WHERE t.tripName = :tripName"),
    @NamedQuery(name = "Trips.findByPartialTripName", query = "SELECT t FROM Trips t WHERE t.tripName LIKE :partialTripName"),
    @NamedQuery(name = "Trips.findByPrice", query = "SELECT t FROM Trips t WHERE t.price = :price"),
    @NamedQuery(name = "Trips.findByStartDate", query = "SELECT t FROM Trips t WHERE t.startDate = :startDate"),
    @NamedQuery(name = "Trips.findByEndDate", query = "SELECT t FROM Trips t WHERE t.endDate = :endDate"),
    @NamedQuery(name = "Trips.findByCityName", query = "SELECT t FROM Trips t WHERE t.city.cityName = :cityName"),
    @NamedQuery(name = "Trips.findByAvailability", query = "SELECT t FROM Trips t WHERE t.availability = :availability"),
    @NamedQuery(name = "Trips.findBySeason", query = "SELECT t FROM Trips t WHERE t.season = :season")
})

public class Trips implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TripID")
    private Integer tripID;
    @Column(name = "TripName")
    private String tripName;
    @Lob
    @Column(name = "Description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Price")
    private Double price;
    @Column(name = "StartDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "EndDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "Availability")
    private Integer availability;
    @Column(name = "Season") // Ensure this matches the column name in your database
    private String season;
    
    @ManyToOne
    @JoinColumn(name = "CityID", referencedColumnName = "CityID")
    private Cities city;

    @OneToMany(mappedBy = "trip")
    private List<Orders> orders;

    @OneToMany(mappedBy = "trip")
    private List<Reviews> reviews;

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }

        // A static method for finding trips by name
    public static List<Trips> findTripsByName(EntityManager em, String tripName) {
        TypedQuery<Trips> query = em.createNamedQuery("Trips.findTripByName", Trips.class);
        query.setParameter("tripName", tripName);
        return query.getResultList();
    }
    public static List<Trips> findTripsBySeason(EntityManager em, String season) {
        TypedQuery<Trips> query = em.createNamedQuery("Trips.findBySeason", Trips.class);
        query.setParameter("season", season);
        return query.getResultList();
    }

    public Trips() {
    }

    public Trips(Integer tripID) {
        this.tripID = tripID;
    }

    public Integer getTripID() {
        return tripID;
    }

    public void setTripID(Integer tripID) {
        this.tripID = tripID;
    }


    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
/*
    public Double getAverageRatingByTripID(Integer tripID) {
        Query query = em.createNamedQuery("Reviews.findByTripID");
        List<Reviews> rating = query.setParameter("tripID", tripID).getResultList();
        if (rating == null || rating.isEmpty()) {
            return null;
        }
        double sum = 0.0;
        for (Reviews review : rating) {
            sum += review.getRating();
        }
        return sum / rating.size();
   }
   */ 
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tripID != null ? tripID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trips)) {
            return false;
        }
        Trips other = (Trips) object;
        if ((this.tripID == null && other.tripID != null) || (this.tripID != null && !this.tripID.equals(other.tripID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.explorewise.phase2_v2.models.Trips[ tripID=" + tripID + " ]";
    }
    
}
