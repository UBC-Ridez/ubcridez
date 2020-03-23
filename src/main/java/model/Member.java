package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * The persistent class for the member database table.
 * 
 */
@Entity
@NamedQuery(name="Member.findAll", query="SELECT m FROM Member m")
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
  @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer age;

	private String name;

	private String rating;

	private String status;

	//bi-directional many-to-one association to Feedback
	@JsonBackReference
	@OneToMany(mappedBy="member")
	private List<Feedback> feedbacks;

	//bi-directional many-to-one association to Address
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name="address_id")
	private Address address;

	//bi-directional many-to-one association to Ridee
  @JsonBackReference
	@OneToMany(mappedBy="member")
	private List<Ridee> ridees;

	//bi-directional many-to-one association to Rider
  @JsonIgnore
	@OneToMany(mappedBy="member")
	private List<Rider> riders;

	public Member() {
	}

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }
  
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Feedback> getFeedbacks() {
		return this.feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public Feedback addFeedback(Feedback feedback) {
		getFeedbacks().add(feedback);
		feedback.setMember(this);

		return feedback;
	}

	public Feedback removeFeedback(Feedback feedback) {
		getFeedbacks().remove(feedback);
		feedback.setMember(null);

		return feedback;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Ridee> getRidees() {
		return this.ridees;
	}

	public void setRidees(List<Ridee> ridees) {
		this.ridees = ridees;
	}

	public Ridee addRidee(Ridee ridee) {
		getRidees().add(ridee);
		ridee.setMember(this);

		return ridee;
	}

	public Ridee removeRidee(Ridee ridee) {
		getRidees().remove(ridee);
		ridee.setMember(null);

		return ridee;
	}

	public List<Rider> getRiders() {
		return this.riders;
	}

	public void setRiders(List<Rider> riders) {
		this.riders = riders;
	}

	public Rider addRider(Rider rider) {
		getRiders().add(rider);
		rider.setMember(this);

		return rider;
	}

	public Rider removeRider(Rider rider) {
		getRiders().remove(rider);
		rider.setMember(null);

		return rider;
	}

}