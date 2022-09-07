package com.restapi.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.jooq.model.tables.BpCompartmentAllocation;
import com.restapi.jooq.model.tables.records.BpCompartmentAllocationRecord;

@Service
public class CompartmentAllocationDao extends
		DAOImpl<BpCompartmentAllocationRecord, com.restapi.jooq.model.tables.pojos.BpCompartmentAllocation, Long> {

	@Autowired
	private DSLContext dslContext;

	@PostConstruct
	public void setup() {
		setConfiguration(dslContext.configuration());
	}

	public CompartmentAllocationDao() {
		super(BpCompartmentAllocation.BP_COMPARTMENT_ALLOCATION,
				com.restapi.jooq.model.tables.pojos.BpCompartmentAllocation.class);
	}

	public CompartmentAllocationDao(Configuration configuration) {
		super(BpCompartmentAllocation.BP_COMPARTMENT_ALLOCATION,
				com.restapi.jooq.model.tables.pojos.BpCompartmentAllocation.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getId(com.restapi.jooq.model.tables.pojos.BpCompartmentAllocation object) {
		return object.getId();
	}

	/**
	 * Fetch records that have <code>id IN (values)</code>
	 */
	public List<com.restapi.jooq.model.tables.pojos.BpCompartmentAllocation> fetchById(Long... values) {
		return fetch(BpCompartmentAllocation.BP_COMPARTMENT_ALLOCATION.ID, values);
	}

	public List<com.restapi.jooq.model.tables.pojos.BpCompartmentAllocation> fetchByJourneyId(Long value) {
		return fetch(BpCompartmentAllocation.BP_COMPARTMENT_ALLOCATION.JOURNEY_ID, value);
	}

	
	public List<com.restapi.jooq.model.tables.pojos.BpCompartmentAllocation> fetchByOrderId(Long... values) {
		return fetch(BpCompartmentAllocation.BP_COMPARTMENT_ALLOCATION.ORDER_ID, values);
	}

    public List<com.restapi.jooq.model.tables.pojos.BpCompartmentAllocation> fetchAll() {
    	return dslContext
    			.selectFrom(super.getTable()).fetch(mapper());
    }
}