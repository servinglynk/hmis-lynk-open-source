package com.servinglynk.hmis.warehouse.listener;

import java.util.UUID;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.servinglynk.hmis.warehouse.model.AMQEvent;
import com.servinglynk.hmis.warehouse.model.ClientMetaDataModel;
import com.servinglynk.hmis.warehouse.model.JSONObjectMapper;

@Component
public class ClientMergeListener extends BaseListener {

	@JmsListener(destination="client.merge")
	public void listeneQueue(String eventString) {
		System.out.println("inside client.merge listener");
		JSONObjectMapper mapper = new JSONObjectMapper();
		try {
			AMQEvent event = mapper.readValue(eventString, AMQEvent.class);
			
			ClientMetaDataModel model = new ClientMetaDataModel();
			model.setAdditionalInfo(mapper.writeValueAsString(event.getPayload()));
			if(event.getPayload().get("oldClientDedupId")!=null) model.setClientDedupId(UUID.fromString(event.getPayload().get("oldClientDedupId").toString()));
			if(event.getPayload().get("newClientDedupId")!=null) model.setNewDedulClientId(UUID.fromString(event.getPayload().get("newClientDedupId").toString()));
			if(event.getPayload().get("projectGroupCode")!=null) model.setProjectGroupCode(event.getPayload().get("projectGroupCode").toString());
			model.setType(event.getEventType());

				serviceFactory.getClientManagementService().mergeClientIdentities(model);
			
		}catch (Exception e) {
		e.printStackTrace();	
		}
	}
}