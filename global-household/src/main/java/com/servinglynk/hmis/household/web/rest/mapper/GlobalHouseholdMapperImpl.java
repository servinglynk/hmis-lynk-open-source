package com.servinglynk.hmis.household.web.rest.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.household.domain.Client;
import com.servinglynk.hmis.household.domain.GlobalHousehold;
import com.servinglynk.hmis.household.domain.HouseholdMembership;
import com.servinglynk.hmis.household.enums.RelationshipToHOfHEnum;
import com.servinglynk.hmis.household.repository.ClientRepository;
import com.servinglynk.hmis.household.web.rest.dto.GlobalHouseholdDTO;
import com.servinglynk.hmis.household.web.rest.util.SecurityContextUtil;

@Component("globalHouseholdMapper")
public class GlobalHouseholdMapperImpl implements GlobalHouseholdMapper {
	
	@Autowired
	ClientRepository clientRepository;

	@Override
	public GlobalHouseholdDTO globalHouseholdToGlobalHouseholdDTO(GlobalHousehold globalHousehold) {
			
			GlobalHouseholdDTO globalHouseholdDTO = new GlobalHouseholdDTO();
			globalHouseholdDTO.setDateCreated(globalHousehold.getDateCreated());
			globalHouseholdDTO.setDateUpdated(globalHousehold.getDateUpdated());
			globalHouseholdDTO.setGlobalHouseholdId(globalHousehold.getGlobalHouseholdId());
			globalHouseholdDTO.setHeadOfHouseholdId(globalHousehold.getHeadOfHouseholdId());
			globalHouseholdDTO.setUserId(globalHousehold.getUserId());
			globalHouseholdDTO.setLink(globalHousehold.getHeadOfHouseHoldLink());
			globalHouseholdDTO.setName(globalHousehold.getName());
			
	//		System.out.println("Clients size is "+clientRepository.findByDedupClientIdOrderBySchemaYearAsc(globalHousehold.getDedupClientId()).size());
			if(globalHousehold.getDedupClientId()!=null)
			{
				List<Client> clients = clientRepository.findByDedupClientIdAndProjectGroupCodeOrderBySchemaYearDesc(globalHousehold.getDedupClientId(),SecurityContextUtil.getUserProjectGroup());
				globalHouseholdDTO.setHeadOfHouseholdId(clients.get(0).getId());
				globalHouseholdDTO.setDedupClientId(clients.get(0).getDedupClientId());
				globalHouseholdDTO.setLink("/hmis-clientapi/rest/v"+clients.get(0).getSchemaYear()+"/clients/"+clients.get(0).getId());
			}
			
			
			return globalHouseholdDTO;
	}

	@Override
	public List<GlobalHouseholdDTO> globalHouseholdsToGlobalHouseholdDTOs(List<GlobalHousehold> globalHouseholds) {
				List<GlobalHouseholdDTO> globalHouseholdDTOs = new ArrayList<GlobalHouseholdDTO>();
				for(GlobalHousehold globalHousehold : globalHouseholds){
					globalHouseholdDTOs.add(this.globalHouseholdToGlobalHouseholdDTO(globalHousehold));
				}
		return globalHouseholdDTOs;
	}

	@Override
	public GlobalHousehold globalHouseholdDTOToGlobalHousehold(GlobalHouseholdDTO globalHouseholdDTO) {
		GlobalHousehold globalHousehold = new GlobalHousehold();
		globalHousehold.setGlobalHouseholdId(globalHouseholdDTO.getGlobalHouseholdId());
		globalHousehold.setHeadOfHouseholdId(globalHouseholdDTO.getHeadOfHouseholdId());	
		globalHousehold.setHeadOfHouseHoldLink(globalHouseholdDTO.getLink());
		globalHousehold.setDedupClientId(globalHouseholdDTO.getDedupClientId());
		globalHousehold.setSchemaYear(globalHouseholdDTO.getSchemaYear());
		globalHousehold.setName(globalHouseholdDTO.getName());
		if(globalHouseholdDTO.getGlobalHouseholdId()==null){
			HouseholdMembership membership = new HouseholdMembership();
			membership.setGlobalClientId(globalHouseholdDTO.getHeadOfHouseholdId());
			membership.setGlobalHousehold(globalHousehold);
			membership.setRelationshipToHeadOfHousehold(RelationshipToHOfHEnum.lookupEnum("1"));
			membership.setSchemaYear(globalHouseholdDTO.getSchemaYear());
			membership.setClientLink(globalHouseholdDTO.getLink());
			globalHousehold.getMembers().add(membership);
		}
	return globalHousehold;
	}

	@Override
	public List<GlobalHousehold> globalHouseholdDTOsToGlobalHouseholds(List<GlobalHouseholdDTO> globalHouseholdDTOs) {
		List<GlobalHousehold> globalHouseholds = new ArrayList<GlobalHousehold>();
			for(GlobalHouseholdDTO globalHouseholdDTO : globalHouseholdDTOs){
				globalHouseholds.add(this.globalHouseholdDTOToGlobalHousehold(globalHouseholdDTO));
			}
		
		return globalHouseholds;
	}

}
