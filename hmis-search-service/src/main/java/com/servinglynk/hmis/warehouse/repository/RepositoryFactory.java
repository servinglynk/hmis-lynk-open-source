package com.servinglynk.hmis.warehouse.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepositoryFactory {

	//@Autowired
	ClientElasticRepository clientElasticRepository;
	//@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	ClientMetaDataRepository clientMetaDataRepository;
	
	public ClientElasticRepository getClientElasticRepository() {
		return clientElasticRepository;
	}
	public void setClientElasticRepository(ClientElasticRepository clientElasticRepository) {
		this.clientElasticRepository = clientElasticRepository;
	}
	public ClientRepository getClientRepository() {
		return clientRepository;
	}
	public void setClientRepository(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	public ClientMetaDataRepository getClientMetaDataRepository() {
		return clientMetaDataRepository;
	}
	public void setClientMetaDataRepository(ClientMetaDataRepository clientMetaDataRepository) {
		this.clientMetaDataRepository = clientMetaDataRepository;
	}	
}