package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * The persistent class for the ride database table.
 * 
 */
@Entity
@NamedQuery(name="Ride.findAll", query="SELECT r FROM Ride r")
public class Ride implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
  @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name="arrival_location")
	private String arrivalLocation;

	@Column(name="arrival_time")
	private Long arrivalTime;

	@Column(name="departure_location")
	private String departureLocation;

	@Column(name="departure_time")
	private Long departureTime;

	private String notes;

	//bi-directional many-to-one association to Feedback
  @JsonIgnore
	@OneToMany(mappedBy="ride")
	private List<Feedback> feedbacks;

	//bi-directional many-to-one association to Ridee
  @JsonIgnore
	@ManyToOne
	@JoinColumn(name="ridee_id")
	private Ridee ridee;

	//bi-directional many-to-one association to Rider
  @JsonManagedReference
	@ManyToOne
	@JoinColumn(name="rider_id")
	private Rider rider;

	public Ride() {
	}

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }
  
	public String getArrivalLocation() {
		return this.arrivalLocation;
	}

	public void setArrivalLocation(String arrivalLocation) {
		this.arrivalLocation = arrivalLocation;
	}

	public Long getArrivalTime() {
		return this.arrivalTime;
	}

	public void setArrivalTime(Long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDepartureLocation() {
		return this.departureLocation;
	}

	public void setDepartureLocation(String departureLocation) {
		this.departureLocation = departureLocation;
	}

	public Long getDepartureTime() {
		return this.departureTime;
	}

	public void setDepartureTime(Long departureTime) {
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