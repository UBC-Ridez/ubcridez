package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the preferences database table.
 * 
 */
@Entity
@Table(name="preferences")
@NamedQuery(name="Preference.findAll", query="SELECT p FROM Preference p")
public class Preference implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="preference_id")
	private Integer preferenceId;

	private String description;

	private String favourites;

	//bi-directional many-to-one association to Ridee
	@ManyToOne
	@JoinColumn(name="ridee_id")
	private Ridee ridee;

	public Preference() {
	}

	public Integer getPreferenceId() {
		return this.preferenceId;
	}

	public void setPreferenceId(Integer preferenceId) {
		this.preferenceId = preferenceId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFavourites() {
		return this.favourites;
	}

	public void setFavourites(String favourites) {
		this.favourites = favourites;
	}

	public Ridee getRidee() {
		return this.ridee;
	}

	public void setRidee(Ridee ridee) {
		this.ridee = ridee;
	}

}