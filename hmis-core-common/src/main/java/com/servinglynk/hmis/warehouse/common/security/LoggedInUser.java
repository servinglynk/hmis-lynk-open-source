package com.servinglynk.hmis.warehouse.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class LoggedInUser implements UserDetails  {
	
	private String username;
	private String projectGroup;
	private List<UUID> enrollments = new ArrayList<>();
	private List<UUID> clients= new ArrayList<>();
	private UUID profileId;
	private UUID userId;
	
	public LoggedInUser(String username, String projectGroup,UUID profileId,UUID userId) {
		super();
		this.username = username;
		this.projectGroup = projectGroup;
		this.profileId = profileId;
		this.userId = userId;
	}

	public LoggedInUser(String username, String projectGroup,UUID profileId,UUID userId, List<UUID> enrollments, List<UUID> clients) {
		super();
		this.username = username;
		this.projectGroup = projectGroup;
		this.profileId = profileId;
		this.userId = userId;
		this.enrollments = enrollments;
		this.clients = clients;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


	public List<UUID> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<UUID> enrollments) {
		this.enrollments = enrollments;
	}

	public List<UUID> getClients() {
		return clients;
	}

	public void setClients(List<UUID> clients) {
		this.clients = clients;
	}

	public String getProjectGroup() {
		return projectGroup;
	}

	public void setProjectGroup(String projectGroup) {
		this.projectGroup = projectGroup;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UUID getProfileId() {
		return profileId;
	}

	public void setProfileId(UUID profileId) {
		this.profileId = profileId;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}
}