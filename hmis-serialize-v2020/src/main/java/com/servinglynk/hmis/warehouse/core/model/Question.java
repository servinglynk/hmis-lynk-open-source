package com.servinglynk.hmis.warehouse.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("question")
public class Question extends ClientModel {

	private UUID questionId;

	private String projectGroupId;

	private String questionDescription;

	private String displayText;

	private String questionDataType;

	private String questionType;

	private String picklistGroupName;
	
	@JsonProperty("options")
	private Map<String, String> pickListValues;
	
	//private HMISTypes pickList;
	
	private List<HMISType> pickList;

	private String hudQuestionId;
	
	private List<ActionLink> links;

	private HudQuestionDefinition definition;
	
	private String uriObjectField;
	
	private String updateUriTemplate;
	
	private Long questionGroupId;
	
	public UUID getQuestionId() {
		return questionId;
	}

	public void setQuestionId(UUID questionId) {
		this.questionId = questionId;
	}

	public String getProjectGroupId() {
		return projectGroupId;
	}

	public void setProjectGroupId(String projectGroupId) {
		this.projectGroupId = projectGroupId;
	}

	public String getQuestionDescription() {
		return questionDescription;
	}

	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public String getQuestionDataType() {
		return questionDataType;
	}

	public void setQuestionDataType(String questionDataType) {
		this.questionDataType = questionDataType;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getPicklistGroupName() {
		return picklistGroupName;
	}

	public void setPicklistGroupName(String picklistGroupName) {
		this.picklistGroupName = picklistGroupName;
	}

	public String getHudQuestionId() {
		return hudQuestionId;
	}

	public void setHudQuestionId(String hudQuestionId) {
		this.hudQuestionId = hudQuestionId;
	}

	public List<ActionLink> getLinks() {
		return links;
	}

	public void setLinks(List<ActionLink> links) {
		this.links = links;
	}
	public void addLink(ActionLink link) {
		if(this.links==null) this.links = new ArrayList<ActionLink>();
		this.links.add(link);		
	}

	public List<HMISType> getPickList() {
		return pickList;
	}

	public void setPickList(List<HMISType> pickList) {
		this.pickList = pickList;
	}
	
	public void addPickList(HMISType hmisType) {
		if(this.pickList ==null) pickList = new ArrayList<HMISType>();
		this.pickList.add(hmisType);;
	}

	public Map<String, String> getPickListValues() {
		if(this.pickListValues==null) this.pickListValues = new LinkedHashMap<String,String>();
		return pickListValues;
	}

	public void setPickListValues(Map<String, String> pickListValues) {
		this.pickListValues = pickListValues;
	}

	public HudQuestionDefinition getDefinition() {
		return definition;
	}

	public void setDefinition(HudQuestionDefinition definition) {
		this.definition = definition;
	}

	public String getUriObjectField() {
		return uriObjectField;
	}

	public void setUriObjectField(String uriObjectField) {
		this.uriObjectField = uriObjectField;
	}

	public String getUpdateUriTemplate() {
		return updateUriTemplate;
	}

	public void setUpdateUriTemplate(String updateUriTemplate) {
		this.updateUriTemplate = updateUriTemplate;
	}

	public Long getQuestionGroupId() {
		return questionGroupId;
	}

	public void setQuestionGroupId(Long questionGroupId) {
		this.questionGroupId = questionGroupId;
	}

	/*public HMISTypes getPickList() {
		return pickList;
	}

	public void setPickList(HMISTypes pickList) {
		this.pickList = pickList;
	}*/
}