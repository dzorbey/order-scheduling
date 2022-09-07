package com.restapi.dao;

import java.util.List;
import javax.annotation.PostConstruct;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.jooq.model.tables.BpJourneyLine;
import com.restapi.jooq.model.tables.records.BpJourneyLineRecord;

@Service
public class JourneyLineDao extends DAOImpl<BpJourneyLineRecord, com.restapi.jooq.model.tables.pojos.BpJourneyLine, Long> {

	@Autowired
	private DSLContext dslContext;
	
	@PostConstruct
	public void setup() {
		setConfiguration(dslContext.configuration());
	}
	
    public JourneyLineDao() {
        super(BpJourneyLine.BP_JOURNEY_LINE, com.restapi.jooq.model.tables.pojos.BpJourneyLine.class);
    }

    public JourneyLineDao(Configuration configuration) {
    	super(BpJourneyLine.BP_JOURNEY_LINE, com.restapi.jooq.model.tables.pojos.BpJourneyLine.class, configuration);
    }
    

    /**
     * {@inheritDoc}
     */
    @Override
	public Long getId(com.restapi.jooq.model.tables.pojos.BpJourneyLine object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.restapi.jooq.model.tables.pojos.BpJourneyLine> fetchByJourneyId(Long... values) {
        return fetch(BpJourneyLine.BP_JOURNEY_LINE.JOURNEY_ID, values);
    }
    
	public List<com.restapi.jooq.model.tables.pojos.BpJourneyLine> fetchAll() {
		return dslContext.
				selectFrom(super.getTable()).fetch(mapper());
	}
}