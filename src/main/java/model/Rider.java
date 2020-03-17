package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rider database table.
 * 
 */
@Entity
@NamedQuery(name="Rider.findAll", query="SELECT r FROM Rider r")
public class Rider implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="rider_id")
	private Integer riderId;

	//bi-directional many-to-one association to Ride
	@OneToMany(mappedBy="rider")
	private List<Ride> rides;

	//bi-directional many-to-one association to Member
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;

	//bi-directional many-to-one association to Vehicle
	@ManyToOne
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;

	public Rider() {
	}

	public Integer getRiderId() {
		return this.riderId;
	}

	public void setRiderId(Integer riderId) {
		this.riderId = riderId;
	}

	public List<Ride> getRides() {
		return this.rides;
	}

	public void setRides(List<Ride> rides) {
		this.rides = rides;
	}

	public Ride addRide(Ride ride) {
		getRides().add(ride);
		ride.setRider(this);

		return ride;
	}

	public Ride removeRide(Ride ride) {
		getRides().remove(ride);
		ride.setRider(null);

		return ride;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}