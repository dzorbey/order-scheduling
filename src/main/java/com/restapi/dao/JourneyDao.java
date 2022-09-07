package com.restapi.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.jooq.model.tables.BpJourney;
import com.restapi.jooq.model.tables.records.BpJourneyRecord;

@Service
public class JourneyDao extends DAOImpl<BpJourneyRecord, com.restapi.jooq.model.tables.pojos.BpJourney, Long> {

	@Autowired
	private DSLContext dslContext;

	@PostConstruct
	public void setup() {
		setConfiguration(dslContext.configuration());
	}

	public JourneyDao() {
		super(BpJourney.BP_JOURNEY, com.restapi.jooq.model.tables.pojos.BpJourney.class);
	}

	public JourneyDao(Configuration configuration) {
		super(BpJourney.BP_JOURNEY, com.restapi.jooq.model.tables.pojos.BpJourney.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getId(com.restapi.jooq.model.tables.pojos.BpJourney object) {
		return object.getId();
	}

	/**
	 * Fetch records that have <code>id IN (values)</code>
	 */
	public List<com.restapi.jooq.model.tables.pojos.BpJourney> fetchById(Long... values) {
		return fetch(BpJourney.BP_JOURNEY.ID, values);
	}
	
	public com.restapi.jooq.model.tables.pojos.BpJourney fetchOneById(Long value) {
		return fetchOne(BpJourney.BP_JOURNEY.ID, value);
	}

	public List<com.restapi.jooq.model.tables.pojos.BpJourney> fetchAll() {
		return dslContext.
				selectFrom(super.getTable()).fetch(mapper());
	}
}