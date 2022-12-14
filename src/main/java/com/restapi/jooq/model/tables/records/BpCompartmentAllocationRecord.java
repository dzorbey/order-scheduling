/*
 * This file is generated by jOOQ.
 */
package com.restapi.jooq.model.tables.records;


import com.restapi.jooq.model.tables.BpCompartmentAllocation;

import java.math.BigDecimal;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BpCompartmentAllocationRecord extends UpdatableRecordImpl<BpCompartmentAllocationRecord> implements Record7<Long, Long, Long, BigDecimal, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.bp_compartment_allocation.id</code>.
     */
    public BpCompartmentAllocationRecord setId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.bp_compartment_allocation.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.bp_compartment_allocation.order_id</code>.
     */
    public BpCompartmentAllocationRecord setOrderId(Long value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.bp_compartment_allocation.order_id</code>.
     */
    public Long getOrderId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.bp_compartment_allocation.journey_id</code>.
     */
    public BpCompartmentAllocationRecord setJourneyId(Long value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.bp_compartment_allocation.journey_id</code>.
     */
    public Long getJourneyId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.bp_compartment_allocation.weight</code>.
     */
    public BpCompartmentAllocationRecord setWeight(BigDecimal value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.bp_compartment_allocation.weight</code>.
     */
    public BigDecimal getWeight() {
        return (BigDecimal) get(3);
    }

    /**
     * Setter for <code>public.bp_compartment_allocation.source</code>.
     */
    public BpCompartmentAllocationRecord setSource(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.bp_compartment_allocation.source</code>.
     */
    public String getSource() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.bp_compartment_allocation.destination</code>.
     */
    public BpCompartmentAllocationRecord setDestination(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.bp_compartment_allocation.destination</code>.
     */
    public String getDestination() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.bp_compartment_allocation.fuel_type</code>.
     */
    public BpCompartmentAllocationRecord setFuelType(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public.bp_compartment_allocation.fuel_type</code>.
     */
    public String getFuelType() {
        return (String) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<Long, Long, Long, BigDecimal, String, String, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<Long, Long, Long, BigDecimal, String, String, String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return BpCompartmentAllocation.BP_COMPARTMENT_ALLOCATION.ID;
    }

    @Override
    public Field<Long> field2() {
        return BpCompartmentAllocation.BP_COMPARTMENT_ALLOCATION.ORDER_ID;
    }

    @Override
    public Field<Long> field3() {
        return BpCompartmentAllocation.BP_COMPARTMENT_ALLOCATION.JOURNEY_ID;
    }

    @Override
    public Field<BigDecimal> field4() {
        return BpCompartmentAllocation.BP_COMPARTMENT_ALLOCATION.WEIGHT;
    }

    @Override
    public Field<String> field5() {
        return BpCompartmentAllocation.BP_COMPARTMENT_ALLOCATION.SOURCE;
    }

    @Override
    public Field<String> field6() {
        return BpCompartmentAllocation.BP_COMPARTMENT_ALLOCATION.DESTINATION;
    }

    @Override
    public Field<String> field7() {
        return BpCompartmentAllocation.BP_COMPARTMENT_ALLOCATION.FUEL_TYPE;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public Long component2() {
        return getOrderId();
    }

    @Override
    public Long component3() {
        return getJourneyId();
    }

    @Override
    public BigDecimal component4() {
        return getWeight();
    }

    @Override
    public String component5() {
        return getSource();
    }

    @Override
    public String component6() {
        return getDestination();
    }

    @Override
    public String component7() {
        return getFuelType();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public Long value2() {
        return getOrderId();
    }

    @Override
    public Long value3() {
        return getJourneyId();
    }

    @Override
    public BigDecimal value4() {
        return getWeight();
    }

    @Override
    public String value5() {
        return getSource();
    }

    @Override
    public String value6() {
        return getDestination();
    }

    @Override
    public String value7() {
        return getFuelType();
    }

    @Override
    public BpCompartmentAllocationRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public BpCompartmentAllocationRecord value2(Long value) {
        setOrderId(value);
        return this;
    }

    @Override
    public BpCompartmentAllocationRecord value3(Long value) {
        setJourneyId(value);
        return this;
    }

    @Override
    public BpCompartmentAllocationRecord value4(BigDecimal value) {
        setWeight(value);
        return this;
    }

    @Override
    public BpCompartmentAllocationRecord value5(String value) {
        setSource(value);
        return this;
    }

    @Override
    public BpCompartmentAllocationRecord value6(String value) {
        setDestination(value);
        return this;
    }

    @Override
    public BpCompartmentAllocationRecord value7(String value) {
        setFuelType(value);
        return this;
    }

    @Override
    public BpCompartmentAllocationRecord values(Long value1, Long value2, Long value3, BigDecimal value4, String value5, String value6, String value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BpCompartmentAllocationRecord
     */
    public BpCompartmentAllocationRecord() {
        super(BpCompartmentAllocation.BP_COMPARTMENT_ALLOCATION);
    }

    /**
     * Create a detached, initialised BpCompartmentAllocationRecord
     */
    public BpCompartmentAllocationRecord(Long id, Long orderId, Long journeyId, BigDecimal weight, String source, String destination, String fuelType) {
        super(BpCompartmentAllocation.BP_COMPARTMENT_ALLOCATION);

        setId(id);
        setOrderId(orderId);
        setJourneyId(journeyId);
        setWeight(weight);
        setSource(source);
        setDestination(destination);
        setFuelType(fuelType);
    }
}
