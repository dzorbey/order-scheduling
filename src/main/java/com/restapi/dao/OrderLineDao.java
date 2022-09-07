package com.restapi.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.jooq.model.tables.BpOrderLine;
import com.restapi.jooq.model.tables.records.BpOrderLineRecord;

@Service
public class OrderLineDao extends DAOImpl<BpOrderLineRecord, com.restapi.jooq.model.tables.pojos.BpOrderLine, Long> {

	@Autowired
	private DSLContext dslContext;

	@PostConstruct
	public void setup() {
		setConfiguration(dslContext.configuration());
	}

	public OrderLineDao() {
		super(BpOrderLine.BP_ORDER_LINE, com.restapi.jooq.model.tables.pojos.BpOrderLine.class);
	}

	public OrderLineDao(Configuration configuration) {
		super(BpOrderLine.BP_ORDER_LINE, com.restapi.jooq.model.tables.pojos.BpOrderLine.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getId(com.restapi.jooq.model.tables.pojos.BpOrderLine object) {
		return object.getId();
	}

	/**
	 * Fetch records that have <code>id IN (values)</code>
	 */
	public List<com.restapi.jooq.model.tables.pojos.BpOrderLine> fetchById(Long... values) {
		return fetch(BpOrderLine.BP_ORDER_LINE.ID, values);
	}

	public List<com.restapi.jooq.model.tables.pojos.BpOrderLine> fetchByOrderId(Long... values) {
		return fetch(BpOrderLine.BP_ORDER_LINE.ORDER_ID, values);
	}
	
	public List<com.restapi.jooq.model.tables.pojos.BpOrderLine> fetchAll() {
		return dslContext.
				selectFrom(super.getTable()).fetch(mapper());
	}

}