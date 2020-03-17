package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ride database table.
 * 
 */
@Entity
@NamedQuery(name="Ride.findAll", query="SELECT r FROM Ride r")
public class Ride implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ride_id")
	private Integer rideId;

	@Column(name="arrival_location")
	private String arrivalLocation;

	@Column(name="arrival_time")
	private String arrivalTime;

	@Column(name="departure_location")
	private String departureLocation;

	@Column(name="departure_time")
	private String departureTime;

	private String notes;

	//bi-directional many-to-one association to Feedback
	@OneToMany(mappedBy="ride")
	private List<Feedback> feedbacks;

	//bi-directional many-to-one association to Ridee
	@ManyToOne
	@JoinColumn(name="ridee_id")
	private Ridee ridee;

	//bi-directional many-to-one association to Rider
	@ManyToOne
	@JoinColumn(name="rider_id")
	private Rider rider;

	public Ride() {
	}

	public Integer getRideId() {
		return this.rideId;
	}

	public void setRideId(Integer rideId) {
		this.rideId = rideId;
	}

	public String getArrivalLocation() {
		return this.arrivalLocation;
	}

	public void setArrivalLocation(String arrivalLocation) {
		this.arrivalLocation = arrivalLocation;
	}

	public String getArrivalTime() {
		return this.arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDepartureLocation() {
		return this.departureLocation;
	}

	public void setDepartureLocation(String departureLocation) {
		this.departureLocation = departureLocation;
	}

	public String getDepartureTime() {
		return this.departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<Feedback> getFeedbacks() {
		return this.feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public Feedback addFeedback(Feedback feedback) {
		getFeedbacks().add(feedback);
		feedback.setRide(this);

		return feedback;
	}

	public Feedback removeFeedback(Feedback feedback) {
		getFeedbacks().remove(feedback);
		feedback.setRide(null);

		return feedback;
	}

	public Ridee getRidee() {
		return this.ridee;
	}

	public void setRidee(Ridee ridee) {
		this.ridee = ridee;
	}

	public Rider getRider() {
		return this.rider;
	}

	public void setRider(Rider rider) {
		this.rider = rider;
	}

}