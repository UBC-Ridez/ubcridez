package model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the ridee database table.
 * 
 */
@Entity
@NamedQuery(name="Ridee.findAll", query="SELECT r FROM Ridee r")
public class Ridee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="is_minor")
	private Boolean isMinor;

	//bi-directional many-to-one association to Preference
  @JsonIgnore
	@OneToMany(mappedBy="ridee")
	private List<Preference> preferences;

	//bi-directional many-to-one association to Ride
  @JsonIgnore
	@OneToMany(mappedBy="ridee")
	private List<Ride> rides;

	//bi-directional many-to-one association to Member
  @JsonIgnore
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;

	public Ridee() {
	}

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }
  
	public Boolean getIsMinor() {
		return this.isMinor;
	}

	public void setIsMinor(Boolean isMinor) {
		this.isMinor = isMinor;
	}

	public List<Preference> getPreferences() {
		return this.preferences;
	}

	public void setPreferences(List<Preference> preferences) {
		this.preferences = preferences;
	}

	public Preference addPreference(Preference preference) {
		getPreferences().add(preference);
		preference.setRidee(this);

		return preference;
	}

	public Preference removePreference(Preference preference) {
		getPreferences().remove(preference);
		preference.setRidee(null);

		return preference;
	}

	public List<Ride> getRides() {
		return this.rides;
	}

	public void setRides(List<Ride> rides) {
		this.rides = rides;
	}

	public Ride addRide(Ride ride) {
		getRides().add(ride);
		ride.setRidee(this);

		return ride;
	}

	public Ride removeRide(Ride ride) {
		getRides().remove(ride);
		ride.setRidee(null);

		return ride;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}