package com.restapi.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.jooq.model.tables.BpOrder;
import com.restapi.jooq.model.tables.records.BpOrderRecord;

@Service
public class OrderDao extends DAOImpl<BpOrderRecord, com.restapi.jooq.model.tables.pojos.BpOrder, Long> {

	@Autowired
	private DSLContext dslContext;

	@PostConstruct
	public void setup() {
		setConfiguration(dslContext.configuration());
	}

	public OrderDao() {
		super(BpOrder.BP_ORDER, com.restapi.jooq.model.tables.pojos.BpOrder.class);
	}

	public OrderDao(Configuration configuration) {
		super(BpOrder.BP_ORDER, com.restapi.jooq.model.tables.pojos.BpOrder.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getId(com.restapi.jooq.model.tables.pojos.BpOrder object) {
		return object.getId();
	}

	/**
	 * Fetch records that have <code>id IN (values)</code>
	 */
	public List<com.restapi.jooq.model.tables.pojos.BpOrder> fetchById(Long... values) {
		return fetch(BpOrder.BP_ORDER.ID, values);
	}
	
	public List<com.restapi.jooq.model.tables.pojos.BpOrder> fetchAll() {
		return dslContext.
				selectFrom(super.getTable()).fetch(mapper());
	}
	
}