	package com.servinglynk.hmis.warehouse.model.v2016;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.proxy.HibernateProxy;

import com.servinglynk.hmis.warehouse.enums.RecordTypeEnum;
import com.servinglynk.hmis.warehouse.model.EnrollmentSharingModel;


/**
 * Object mapping for hibernate-handled table: service_fa_referral.
 *
 *
 * @author autogenerated
 */


@Entity(name = "service_fa_referral_v2016")
@Table(name = "service_fa_referral", catalog = "hmis", schema = "v2016")
public class ServiceFaReferral extends HmisBaseModel implements Cloneable, Serializable,EnrollmentSharingModel {

	/** Serial Version UID. */
	private static final long serialVersionUID = 6799387931915626866L;

	/** Use a WeakHashMap so entries will be garbage collected once all entities
		referring to a saved hash are garbage collected themselves. */
	private static final Map<Serializable, java.util.UUID> SAVED_HASHES =
		Collections.synchronizedMap(new WeakHashMap<Serializable, java.util.UUID>());

	/** hashCode temporary storage. */
	private volatile java.util.UUID hashCode;

	/** Field mapping. */
	private Enrollment enrollmentid;
	/** Field mapping. */
	private java.math.BigDecimal faAmount;
	/** Field mapping. */
	private Integer funderList;
	/** Field mapping. */
	private java.util.UUID id;
	/** Field mapping. */
	private String otherTypeProvided;
	/** Field mapping. */
	private Integer referralOutcome;
	/** Field mapping. */
	private RecordTypeEnum recordType;
	/** Field mapping. */
	private Integer serviceCategory;
	/** Field mapping. */
	private Integer subTypeProvided;
	/** Field mapping. */
	private Integer typeProvided;
	/** Field mapping. */
	private LocalDateTime dateprovided;
	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public ServiceFaReferral() {
		// Default constructor
	}

	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public ServiceFaReferral(java.util.UUID id) {
		this.id = id;
	}





	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return ServiceFaReferral.class;
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
	 * Return the value associated with the column: faAmount.
	 * @return A java.math.BigDecimal object (this.faAmount)
	 */
	@Basic( optional = true )
	@Column( name = "fa_amount"  )
	public java.math.BigDecimal getFaAmount() {
		return this.faAmount;

	}



	 /**
	 * Set the value related to the column: faAmount.
	 * @param faAmount the faAmount value you wish to set
	 */
	public void setFaAmount(final java.math.BigDecimal faAmount) {
		this.faAmount = faAmount;
	}


	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	@Basic( optional = true )
	@Column( name = "dateProvided"  )
	 public LocalDateTime getDateprovided() {
		return dateprovided;
	}

	public void setDateprovided(LocalDateTime dateprovided) {
		this.dateprovided = dateprovided;
	}

	/**
	 * Return the value associated with the column: funderList.
	 * @return A Integer object (this.funderList)
	 */
	@Basic( optional = true )
	@Column( name = "funder_list"  )
	public Integer getFunderList() {
		return this.funderList;

	}



	 /**
	 * Set the value related to the column: funderList.
	 * @param funderList the funderList value you wish to set
	 */
	public void setFunderList(final Integer funderList) {
		this.funderList = funderList;
	}
	
	/**
	 * Return the value associated with the column: record_type.
	 * @return A Integer object (this.recordType)
	 */
	@Type(type = "com.servinglynk.hmis.warehouse.enums.RecordTypeEnumType")
	@Basic( optional = true )
	@Column( name = "record_type"  )
	public RecordTypeEnum getRecordType() {
		return this.recordType;

	}



	 /**
	 * Set the value related to the column: recordType.
	 * @param funderList the recordType value you wish to set
	 */
	public void setRecordType(final RecordTypeEnum recordType) {
		this.recordType = recordType;
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
	 * Return the value associated with the column: otherTypeProvided.
	 * @return A String object (this.otherTypeProvided)
	 */
	@Basic( optional = true )
	@Column( name = "other_type_provided", length = 800  )
	public String getOtherTypeProvided() {
		return this.otherTypeProvided;

	}



	 /**
	 * Set the value related to the column: otherTypeProvided.
	 * @param otherTypeProvided the otherTypeProvided value you wish to set
	 */
	public void setOtherTypeProvided(final String otherTypeProvided) {
		this.otherTypeProvided = otherTypeProvided;
	}



	 /**
	 * Return the value associated with the column: referralOutcome.
	 * @return A Integer object (this.referralOutcome)
	 */
	@Basic( optional = true )
	@Column( name = "referral_outcome"  )
	public Integer getReferralOutcome() {
		return this.referralOutcome;

	}

	 /**
	 * Set the value related to the column: referralOutcome.
	 * @param referralOutcome the referralOutcome value you wish to set
	 */
	public void setReferralOutcome(final Integer referralOutcome) {
		this.referralOutcome = referralOutcome;
	}

	 /**
	 * Return the value associated with the column: serviceCategory.
	 * @return A Integer object (this.serviceCategory)
	 */
	@Basic( optional = true )
	@Column( name = "service_category"  )
	public Integer getServiceCategory() {
		return this.serviceCategory;

	}



	 /**
	 * Set the value related to the column: serviceCategory.
	 * @param serviceCategory the serviceCategory value you wish to set
	 */
	public void setServiceCategory(final Integer serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	 /**
	 * Return the value associated with the column: subTypeProvided.
	 * @return A Integer object (this.subTypeProvided)
	 */
	@Basic( optional = true )
	@Column( name = "sub_type_provided"  )
	public Integer getSubTypeProvided() {
		return this.subTypeProvided;

	}



	 /**
	 * Set the value related to the column: subTypeProvided.
	 * @param subTypeProvided the subTypeProvided value you wish to set
	 */
	public void setSubTypeProvided(final Integer subTypeProvided) {
		this.subTypeProvided = subTypeProvided;
	}


	 /**
	 * Return the value associated with the column: typeProvided.
	 * @return A Integer object (this.typeProvided)
	 */
	@Basic( optional = true )
	@Column( name = "type_provided"  )
	public Integer getTypeProvided() {
		return this.typeProvided;

	}



	 /**
	 * Set the value related to the column: typeProvided.
	 * @param typeProvided the typeProvided value you wish to set
	 */
	public void setTypeProvided(final Integer typeProvided) {
		this.typeProvided = typeProvided;
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
    public ServiceFaReferral clone() throws CloneNotSupportedException {

        final ServiceFaReferral copy = (ServiceFaReferral)super.clone();

		copy.setDateprovided(this.getDateprovided());
		copy.setDateCreated(this.getDateCreated());
		copy.setDateCreatedFromSource(this.getDateCreatedFromSource());
		copy.setDateUpdated(this.getDateUpdated());
		copy.setDateUpdatedFromSource(this.getDateUpdatedFromSource());
		copy.setDeleted(this.isDeleted());
		copy.setEnrollmentid(this.getEnrollmentid());
		copy.setExport(this.getExport());
		copy.setFaAmount(this.getFaAmount());
		copy.setFunderList(this.getFunderList());
		copy.setId(this.getId());
		copy.setOtherTypeProvided(this.getOtherTypeProvided());
		copy.setParentId(this.getParentId());
		copy.setProjectGroupCode(this.getProjectGroupCode());
		copy.setReferralOutcome(this.getReferralOutcome());
		copy.setServiceCategory(this.getServiceCategory());
		copy.setSubTypeProvided(this.getSubTypeProvided());
		copy.setSync(this.isSync());
		copy.setTypeProvided(this.getTypeProvided());
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

		sb.append("dateprovided: " + this.getDateprovided() + ", ");
		sb.append("dateCreated: " + this.getDateCreated() + ", ");
		sb.append("dateCreatedFromSource: " + this.getDateCreatedFromSource() + ", ");
		sb.append("dateUpdated: " + this.getDateUpdated() + ", ");
		sb.append("dateUpdatedFromSource: " + this.getDateUpdatedFromSource() + ", ");
		sb.append("deleted: " + this.isDeleted() + ", ");
		sb.append("faAmount: " + this.getFaAmount() + ", ");
		sb.append("funderList: " + this.getFunderList() + ", ");
		sb.append("id: " + this.getId() + ", ");
		sb.append("otherTypeProvided: " + this.getOtherTypeProvided() + ", ");
		sb.append("parentId: " + this.getParentId() + ", ");
		sb.append("projectGroupCode: " + this.getProjectGroupCode() + ", ");
		sb.append("referralOutcome: " + this.getReferralOutcome() + ", ");
		sb.append("serviceCategory: " + this.getServiceCategory() + ", ");
		sb.append("subTypeProvided: " + this.getSubTypeProvided() + ", ");
		sb.append("sync: " + this.isSync() + ", ");
		sb.append("typeProvided: " + this.getTypeProvided() + ", ");
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

		final ServiceFaReferral that;
		try {
			that = (ServiceFaReferral) proxyThat;
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
		result = result && (((getDateprovided() == null) && (that.getDateprovided() == null)) || (getDateprovided() != null && getDateprovided().equals(that.getDateprovided())));
		result = result && (((getDateCreated() == null) && (that.getDateCreated() == null)) || (getDateCreated() != null && getDateCreated().equals(that.getDateCreated())));
		result = result && (((getDateCreatedFromSource() == null) && (that.getDateCreatedFromSource() == null)) || (getDateCreatedFromSource() != null && getDateCreatedFromSource().equals(that.getDateCreatedFromSource())));
		result = result && (((getDateUpdated() == null) && (that.getDateUpdated() == null)) || (getDateUpdated() != null && getDateUpdated().equals(that.getDateUpdated())));
		result = result && (((getDateUpdatedFromSource() == null) && (that.getDateUpdatedFromSource() == null)) || (getDateUpdatedFromSource() != null && getDateUpdatedFromSource().equals(that.getDateUpdatedFromSource())));
		result = result && (((getEnrollmentid() == null) && (that.getEnrollmentid() == null)) || (getEnrollmentid() != null && getEnrollmentid().getId().equals(that.getEnrollmentid().getId())));
		result = result && (((getExport() == null) && (that.getExport() == null)) || (getExport() != null && getExport().getId().equals(that.getExport().getId())));
		result = result && (((getFaAmount() == null) && (that.getFaAmount() == null)) || (getFaAmount() != null && getFaAmount().equals(that.getFaAmount())));
		result = result && (((getFunderList() == null) && (that.getFunderList() == null)) || (getFunderList() != null && getFunderList().equals(that.getFunderList())));
		result = result && (((getOtherTypeProvided() == null) && (that.getOtherTypeProvided() == null)) || (getOtherTypeProvided() != null && getOtherTypeProvided().equals(that.getOtherTypeProvided())));
		result = result && (((getParentId() == null) && (that.getParentId() == null)) || (getParentId() != null && getParentId().equals(that.getParentId())));
		result = result && (((getProjectGroupCode() == null) && (that.getProjectGroupCode() == null)) || (getProjectGroupCode() != null && getProjectGroupCode().equals(that.getProjectGroupCode())));
		result = result && (((getReferralOutcome() == null) && (that.getReferralOutcome() == null)) || (getReferralOutcome() != null && getReferralOutcome().equals(that.getReferralOutcome())));
		result = result && (((getServiceCategory() == null) && (that.getServiceCategory() == null)) || (getServiceCategory() != null && getServiceCategory().equals(that.getServiceCategory())));
		result = result && (((getSubTypeProvided() == null) && (that.getSubTypeProvided() == null)) || (getSubTypeProvided() != null && getSubTypeProvided().equals(that.getSubTypeProvided())));
		result = result && (((getTypeProvided() == null) && (that.getTypeProvided() == null)) || (getTypeProvided() != null && getTypeProvided().equals(that.getTypeProvided())));
		result = result && (((getUserId() == null) && (that.getUserId() == null)) || (getUserId() != null && getUserId().equals(that.getUserId())));
		result = result && (((getVersion() == null) && (that.getVersion() == null)) || (getVersion() != null && getVersion().equals(that.getVersion())));
		return result;
	}
}
