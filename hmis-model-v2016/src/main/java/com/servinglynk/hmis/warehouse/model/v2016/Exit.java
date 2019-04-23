package com.servinglynk.hmis.warehouse.model.v2016;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.proxy.HibernateProxy;

import com.servinglynk.hmis.warehouse.enums.ExitDestinationEnum;
import com.servinglynk.hmis.warehouse.model.EnrollmentSharingModel;


/**
 * Object mapping for hibernate-handled table: exit.
 *
 *
 * @author autogenerated
 */
@Entity (name = "exit_v2016")
@Table(name = "exit", catalog = "hmis", schema = "v2016")
public class Exit extends HmisBaseModel implements Cloneable, Serializable ,EnrollmentSharingModel{

	/** Serial Version UID. */
	private static final long serialVersionUID = -75314002207556153L;

	/** Use a WeakHashMap so entries will be garbage collected once all entities
		referring to a saved hash are garbage collected themselves. */
	private static final Map<Serializable, java.util.UUID> SAVED_HASHES =
		Collections.synchronizedMap(new WeakHashMap<Serializable, java.util.UUID>());

	/** hashCode temporary storage. */
	private volatile java.util.UUID hashCode;

	/** Field mapping. */
	private ExitDestinationEnum destination;
	/** Field mapping. */
	private Enrollment enrollmentid;
	/** Field mapping. */
	private LocalDateTime exitdate;
	/** Field mapping. */
	private Set<Exithousingassessment> exithousingassessments = new HashSet<Exithousingassessment>();

	/** Field mapping. */
	private Set<Exitpath> exitpaths = new HashSet<Exitpath>();

	/** Field mapping. */
	private Set<Exitrhy> exitrhies = new HashSet<Exitrhy>();

	/** Field mapping. */
	private Set<Housingassessmentdisposition> housingassessmentdispositions = new HashSet<Housingassessmentdisposition>();

	/** Field mapping. */
	private java.util.UUID id;
	/** Field mapping. */
	private String otherdestination;
	/** Field mapping. */
	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public Exit() {
		// Default constructor
	}

	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public Exit(java.util.UUID id) {
		this.id = id;
	}





	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return Exit.class;
	}


	 /**
	 * Return the value associated with the column: destination.
	 * @return A ExitDestinationEnum object (this.destination)
	 */
	@Type(type = "com.servinglynk.hmis.warehouse.enums.ExitDestinationEnumType")
	@Basic( optional = true )
	@Column
	public ExitDestinationEnum getDestination() {
		return this.destination;

	}



	 /**
	 * Set the value related to the column: destination.
	 * @param destination the destination value you wish to set
	 */
	public void setDestination(final ExitDestinationEnum destination) {
		this.destination = destination;
	}

	 /**
	 * Return the value associated with the column: enrollmentid.
	 * @return A Enrollment object (this.enrollmentid)
	 */
	@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Basic( optional = true )
	@JoinColumn(name = "enrollmentid", nullable = true )
	public Enrollment getEnrollmentid() {
		return this.enrollmentid;

	}



	 /**
	 * Set the value related to the column: enrollmentid.
	 * @param enrollmentid the enrollmentid value you wish to set
	 */
	public void setEnrollmentid(final Enrollment enrollmentid) {
		this.enrollmentid = enrollmentid;
	}

	 /**
	 * Return the value associated with the column: exitdate.
	 * @return A LocalDateTime object (this.exitdate)
	 */
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	public LocalDateTime getExitdate() {
		return this.exitdate;

	}



	 /**
	 * Set the value related to the column: exitdate.
	 * @param exitdate the exitdate value you wish to set
	 */
	public void setExitdate(final LocalDateTime exitdate) {
		this.exitdate = exitdate;
	}

	 /**
	 * Return the value associated with the column: exithousingassessment.
	 * @return A Set&lt;Exithousingassessment&gt; object (this.exithousingassessment)
	 */
 	@OneToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "exitid"  )
 	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Basic( optional = false )
	@Column( nullable = false  )
	public Set<Exithousingassessment> getExithousingassessments() {
		return this.exithousingassessments;

	}

	/**
	 * Adds a bi-directional link of type Exithousingassessment to the exithousingassessments set.
	 * @param exithousingassessment item to add
	 */
	public void addExithousingassessment(Exithousingassessment exithousingassessment) {
		exithousingassessment.setExitid(this);
		this.exithousingassessments.add(exithousingassessment);
	}


	 /**
	 * Set the value related to the column: exithousingassessment.
	 * @param exithousingassessment the exithousingassessment value you wish to set
	 */
	public void setExithousingassessments(final Set<Exithousingassessment> exithousingassessment) {
		this.exithousingassessments = exithousingassessment;
	}

	 /**
	 * Return the value associated with the column: exitpath.
	 * @return A Set&lt;Exitpath&gt; object (this.exitpath)
	 */
 	@OneToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "exitid"  )
 	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Basic( optional = false )
	@Column( nullable = false  )
	public Set<Exitpath> getExitpaths() {
		return this.exitpaths;

	}

	/**
	 * Adds a bi-directional link of type Exitpath to the exitpaths set.
	 * @param exitpath item to add
	 */
	public void addExitpath(Exitpath exitpath) {
		exitpath.setExitid(this);
		this.exitpaths.add(exitpath);
	}


	 /**
	 * Set the value related to the column: exitpath.
	 * @param exitpath the exitpath value you wish to set
	 */
	public void setExitpaths(final Set<Exitpath> exitpath) {
		this.exitpaths = exitpath;
	}

	 /**
	 * Return the value associated with the column: exitrhy.
	 * @return A Set&lt;Exitrhy&gt; object (this.exitrhy)
	 */
 	@OneToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "exitid"  )
 	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Basic( optional = false )
	@Column( nullable = false  )
	public Set<Exitrhy> getExitrhies() {
		return this.exitrhies;

	}

	/**
	 * Adds a bi-directional link of type Exitrhy to the exitrhies set.
	 * @param exitrhy item to add
	 */
	public void addExitrhy(Exitrhy exitrhy) {
		exitrhy.setExitid(this);
		this.exitrhies.add(exitrhy);
	}


	 /**
	 * Set the value related to the column: exitrhy.
	 * @param exitrhy the exitrhy value you wish to set
	 */
	public void setExitrhies(final Set<Exitrhy> exitrhy) {
		this.exitrhies = exitrhy;
	}

	 /**
	 * Return the value associated with the column: housingassessmentdisposition.
	 * @return A Set&lt;Housingassessmentdisposition&gt; object (this.housingassessmentdisposition)
	 */
 	@OneToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "exitid"  )
 	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Basic( optional = false )
	@Column( nullable = false  )
	public Set<Housingassessmentdisposition> getHousingassessmentdispositions() {
		return this.housingassessmentdispositions;

	}

	/**
	 * Adds a bi-directional link of type Housingassessmentdisposition to the housingassessmentdispositions set.
	 * @param housingassessmentdisposition item to add
	 */
	public void addHousingassessmentdisposition(Housingassessmentdisposition housingassessmentdisposition) {
		housingassessmentdisposition.setExitid(this);
		this.housingassessmentdispositions.add(housingassessmentdisposition);
	}


	 /**
	 * Set the value related to the column: housingassessmentdisposition.
	 * @param housingassessmentdisposition the housingassessmentdisposition value you wish to set
	 */
	public void setHousingassessmentdispositions(final Set<Housingassessmentdisposition> housingassessmentdisposition) {
		this.housingassessmentdispositions = housingassessmentdisposition;
	}

	 /**
	 * Return the value associated with the column: id.
	 * @return A java.util.UUID object (this.id)
	 */
	@Id
	 @Basic( optional = false )
    @Column( name = "id", nullable = false  ) @org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
	public java.util.UUID getId() {
		return this.id;

	}



	 /**
	 * Set the value related to the column: id.
	 * @param id the id value you wish to set
	 */
	public void setId(final java.util.UUID id) {
		// If we've just been persisted and hashCode has been
		// returned then make sure other entities with this
		// ID return the already returned hash code
		if ( (this.id == null ) &&
				(id != null) &&
				(this.hashCode != null) ) {
		SAVED_HASHES.put( id, this.hashCode );
		}
		this.id = id;
	}

	 /**
	 * Return the value associated with the column: otherdestination.
	 * @return A String object (this.otherdestination)
	 */
	@Basic( optional = true )
	@Column
	public String getOtherdestination() {
		return this.otherdestination;

	}



	 /**
	 * Set the value related to the column: otherdestination.
	 * @param otherdestination the otherdestination value you wish to set
	 */
	public void setOtherdestination(final String otherdestination) {
		this.otherdestination = otherdestination;
	}

	/** Field mapping. */
	protected Export export;
	 /**
	 * Return the value associated with the column: export.
	 * @return A Export object (this.export)
	 */
	@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Basic( optional = true )
	@JoinColumn(name = "export_id", nullable = true )
	public Export getExport() {
		return this.export;

	}



	 /**
	 * Set the value related to the column: export.
	 * @param export the export value you wish to set
	 */
	public void setExport(final Export export) {
		this.export = export;
	}

   /**
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public Exit clone() throws CloneNotSupportedException {

        final Exit copy = (Exit)super.clone();

		copy.setDateCreated(this.getDateCreated());
		copy.setDateCreatedFromSource(this.getDateCreatedFromSource());
		copy.setDateUpdated(this.getDateUpdated());
		copy.setDateUpdatedFromSource(this.getDateUpdatedFromSource());
		copy.setDeleted(this.isDeleted());
		copy.setDestination(this.getDestination());
		copy.setEnrollmentid(this.getEnrollmentid());
		copy.setExitdate(this.getExitdate());
		if (this.getExithousingassessments() != null) {
			copy.getExithousingassessments().addAll(this.getExithousingassessments());
		}
		if (this.getExitpaths() != null) {
			copy.getExitpaths().addAll(this.getExitpaths());
		}
		if (this.getExitrhies() != null) {
			copy.getExitrhies().addAll(this.getExitrhies());
		}
		copy.setExport(this.getExport());
		if (this.getHousingassessmentdispositions() != null) {
			copy.getHousingassessmentdispositions().addAll(this.getHousingassessmentdispositions());
		}
		copy.setId(this.getId());
		copy.setOtherdestination(this.getOtherdestination());
		copy.setParentId(this.getParentId());
		copy.setProjectGroupCode(this.getProjectGroupCode());
		copy.setSync(this.isSync());
		copy.setUserId(this.getUserId());
		copy.setVersion(this.getVersion());
		return copy;
	}



	/** Provides toString implementation.
	 * @see java.lang.Object#toString()
	 * @return String representation of this class.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("dateCreated: " + this.getDateCreated() + ", ");
		sb.append("dateCreatedFromSource: " + this.getDateCreatedFromSource() + ", ");
		sb.append("dateUpdated: " + this.getDateUpdated() + ", ");
		sb.append("dateUpdatedFromSource: " + this.getDateUpdatedFromSource() + ", ");
		sb.append("deleted: " + this.isDeleted() + ", ");
		sb.append("destination: " + this.getDestination() + ", ");
		sb.append("exitdate: " + this.getExitdate() + ", ");
		sb.append("id: " + this.getId() + ", ");
		sb.append("otherdestination: " + this.getOtherdestination() + ", ");
		sb.append("parentId: " + this.getParentId() + ", ");
		sb.append("projectGroupCode: " + this.getProjectGroupCode() + ", ");
		sb.append("sync: " + this.isSync() + ", ");
		sb.append("userId: " + this.getUserId() + ", ");
		sb.append("version: " + this.getVersion());
		return sb.toString();
	}


	/** Equals implementation.
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @param aThat Object to compare with
	 * @return true/false
	 */
	@Override
	public boolean equals(final Object aThat) {
		Object proxyThat = aThat;

		if ( this == aThat ) {
			 return true;
		}


		if (aThat instanceof HibernateProxy) {
 			// narrow down the proxy to the class we are dealing with.
 			try {
				proxyThat = ((HibernateProxy) aThat).getHibernateLazyInitializer().getImplementation();
			} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		   	}
		}
		if (aThat == null)  {
			 return false;
		}

		final Exit that;
		try {
			that = (Exit) proxyThat;
			if ( !(that.getClassType().equals(this.getClassType()))){
				return false;
			}
		} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		} catch (ClassCastException e) {
				return false;
		}


		boolean result = true;
		result = result && (((this.getId() == null) && ( that.getId() == null)) || (this.getId() != null  && this.getId().equals(that.getId())));
		result = result && (((getDateCreated() == null) && (that.getDateCreated() == null)) || (getDateCreated() != null && getDateCreated().equals(that.getDateCreated())));
		result = result && (((getDateCreatedFromSource() == null) && (that.getDateCreatedFromSource() == null)) || (getDateCreatedFromSource() != null && getDateCreatedFromSource().equals(that.getDateCreatedFromSource())));
		result = result && (((getDateUpdated() == null) && (that.getDateUpdated() == null)) || (getDateUpdated() != null && getDateUpdated().equals(that.getDateUpdated())));
		result = result && (((getDateUpdatedFromSource() == null) && (that.getDateUpdatedFromSource() == null)) || (getDateUpdatedFromSource() != null && getDateUpdatedFromSource().equals(that.getDateUpdatedFromSource())));
		result = result && (((getDestination() == null) && (that.getDestination() == null)) || (getDestination() != null && getDestination().equals(that.getDestination())));
		result = result && (((getEnrollmentid() == null) && (that.getEnrollmentid() == null)) || (getEnrollmentid() != null && getEnrollmentid().getId().equals(that.getEnrollmentid().getId())));
		result = result && (((getExitdate() == null) && (that.getExitdate() == null)) || (getExitdate() != null && getExitdate().equals(that.getExitdate())));
		result = result && (((getExport() == null) && (that.getExport() == null)) || (getExport() != null && getExport().getId().equals(that.getExport().getId())));
		result = result && (((getOtherdestination() == null) && (that.getOtherdestination() == null)) || (getOtherdestination() != null && getOtherdestination().equals(that.getOtherdestination())));
		result = result && (((getParentId() == null) && (that.getParentId() == null)) || (getParentId() != null && getParentId().equals(that.getParentId())));
		result = result && (((getProjectGroupCode() == null) && (that.getProjectGroupCode() == null)) || (getProjectGroupCode() != null && getProjectGroupCode().equals(that.getProjectGroupCode())));
		result = result && (((getUserId() == null) && (that.getUserId() == null)) || (getUserId() != null && getUserId().equals(that.getUserId())));
		result = result && (((getVersion() == null) && (that.getVersion() == null)) || (getVersion() != null && getVersion().equals(that.getVersion())));
		return result;
	}
}
