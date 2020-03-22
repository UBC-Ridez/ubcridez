package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


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
  @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String description;

	private String favourites;

	//bi-directional many-to-one association to Ridee
  @JsonIgnore
	@ManyToOne
	@JoinColumn(name="ridee_id")
	private Ridee ridee;

	public Preference() {
	}

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
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