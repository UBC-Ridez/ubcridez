package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vehicle database table.
 * 
 */
@Entity
@NamedQuery(name="Vehicle.findAll", query="SELECT v FROM Vehicle v")
public class Vehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="vehicle_id")
	private Integer vehicleId;

	private String make;

	private String model;

	private Integer year;

	//bi-directional many-to-one association to Insurance
	@OneToMany(mappedBy="vehicle")
	private List<Insurance> insurances;

	//bi-directional many-to-one association to Rider
	@OneToMany(mappedBy="vehicle")
	private List<Rider> riders;

	public Vehicle() {
	}

	public Integer getVehicleId() {
		return this.vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getMake() {
		return this.make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<Insurance> getInsurances() {
		return this.insurances;
	}

	public void setInsurances(List<Insurance> insurances) {
		this.insurances = insurances;
	}

	public Insurance addInsurance(Insurance insurance) {
		getInsurances().add(insurance);
		insurance.setVehicle(this);

		return insurance;
	}

	public Insurance removeInsurance(Insurance insurance) {
		getInsurances().remove(insurance);
		insurance.setVehicle(null);

		return insurance;
	}

	public List<Rider> getRiders() {
		return this.riders;
	}

	public void setRiders(List<Rider> riders) {
		this.riders = riders;
	}

	public Rider addRider(Rider rider) {
		getRiders().add(rider);
		rider.setVehicle(this);

		return rider;
	}

	public Rider removeRider(Rider rider) {
		getRiders().remove(rider);
		rider.setVehicle(null);

		return rider;
	}

}