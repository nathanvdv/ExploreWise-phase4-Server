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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author romainhovius
 */
@Entity
@Table(name = "Reviews")
@NamedQueries({
    @NamedQuery(name = "Reviews.findAll", query = "SELECT r FROM Reviews r"),
    @NamedQuery(name = "Reviews.findByTripIDAndUserID", query = "SELECT r FROM Reviews r WHERE r.trip.tripID = :tripID AND r.user.userID = :userID"),
    @NamedQuery(name = "Reviews.findByReviewID", query = "SELECT r FROM Reviews r WHERE r.reviewID = :reviewID"),
    @NamedQuery(name = "Reviews.findByTripID", query = "SELECT r FROM Reviews r WHERE r.trip.tripID = :tripID"),
    @NamedQuery(name = "Reviews.findByUserID", query = "SELECT r FROM Reviews r WHERE r.user.userID = :userID"),
    @NamedQuery(name = "Reviews.findByRating", query = "SELECT r FROM Reviews r WHERE r.rating = :rating"),
    @NamedQuery(name = "Reviews.findByReviewDate", query = "SELECT r FROM Reviews r WHERE r.reviewDate = :reviewDate")})
public class Reviews implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ReviewID")
    private Integer reviewID;
    @Column(name = "Rating")
    private Integer rating;
    @Lob
    @Column(name = "Comment")
    private String comment;
    @Column(name = "ReviewDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewDate;
    
    @ManyToOne
    @JoinColumn(name = "UserID")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "TripID")
    private Trips trip;
    
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
        public Reviews() {
    }

    public Reviews(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public Integer getReviewID() {
        return reviewID;
    }

    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reviewID != null ? reviewID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reviews)) {
            return false;
        }
        Reviews other = (Reviews) object;
        if ((this.reviewID == null && other.reviewID != null) || (this.reviewID != null && !this.reviewID.equals(other.reviewID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.explorewise.phase2_v2.models.Reviews[ reviewID=" + reviewID + " ]";
    }
    
}
