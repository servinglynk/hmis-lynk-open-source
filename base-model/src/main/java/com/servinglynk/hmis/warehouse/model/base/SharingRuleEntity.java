package com.servinglynk.hmis.warehouse.model.base;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="hmis_sharing_rule",schema="base")
public class SharingRuleEntity extends HMISModel {
	
	private UUID id;
	
	private RoleEntity role;
		
	private String toProjectGroup;
	

	private GlobalProjectEntity globalProjectEntity;
	

	private Date activeFrom;
	

	private Date activeTo;
	
    @javax.persistence.Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name = "id")
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	@Column(name = "active_from")
	public Date getActiveFrom() {
		return activeFrom;
	}

	public void setActiveFrom(Date activeFrom) {
		this.activeFrom = activeFrom;
	}
	
	@Column(name = "active_to")
	public Date getActiveTo() {
		return activeTo;
	}
	public void setActiveTo(Date activeTo) {
		this.activeTo = activeTo;
	}
	
	@Column(name="to_project_group_code")
	public String getToProjectGroup() {
		return toProjectGroup;
	}
	public void setToProjectGroup(String toProjectGroup) {
		this.toProjectGroup = toProjectGroup;
	}
	
	@ManyToOne
	@JoinColumn(name = "global_project_id", referencedColumnName="id")
	public GlobalProjectEntity getGlobalProjectEntity() {
		return globalProjectEntity;
	}
	public void setGlobalProjectEntity(GlobalProjectEntity globalProjectEntity) {
		this.globalProjectEntity = globalProjectEntity;
	}
	
	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName="id")
	public RoleEntity getRole() {
		return role;
	}
	public void setRole(RoleEntity role) {
		this.role = role;
	}
}