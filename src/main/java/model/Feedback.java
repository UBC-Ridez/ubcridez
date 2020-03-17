package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the feedback database table.
 * 
 */
@Entity
@NamedQuery(name="Feedback.findAll", query="SELECT f FROM Feedback f")
public class Feedback implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="feedback_id")
	private Integer feedbackId;

	private String feedback;

	@Column(name="feedback_type")
	private String feedbackType;

	//bi-directional many-to-one association to Member
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;

	//bi-directional many-to-one association to Ride
	@ManyToOne
	@JoinColumn(name="ride_id")
	private Ride ride;

	public Feedback() {
	}

	public Integer getFeedbackId() {
		return this.feedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getFeedback() {
		return this.feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getFeedbackType() {
		return this.feedbackType;
	}

	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Ride getRide() {
		return this.ride;
	}

	public void setRide(Ride ride) {
		this.ride = ride;
	}

}