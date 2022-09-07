/*
 * This file is generated by jOOQ.
 */
package com.restapi.jooq.model.tables;


import com.restapi.jooq.model.Keys;
import com.restapi.jooq.model.Public;
import com.restapi.jooq.model.tables.records.BpJourneyLineRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BpJourneyLine extends TableImpl<BpJourneyLineRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.bp_journey_line</code>
     */
    public static final BpJourneyLine BP_JOURNEY_LINE = new BpJourneyLine();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BpJourneyLineRecord> getRecordType() {
        return BpJourneyLineRecord.class;
    }

    /**
     * The column <code>public.bp_journey_line.id</code>.
     */
    public final TableField<BpJourneyLineRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.bp_journey_line.journey_id</code>.
     */
    public final TableField<BpJourneyLineRecord, Long> JOURNEY_ID = createField(DSL.name("journey_id"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.bp_journey_line.compartment_id</code>.
     */
    public final TableField<BpJourneyLineRecord, Long> COMPARTMENT_ID = createField(DSL.name("compartment_id"), SQLDataType.BIGINT, this, "");

    private BpJourneyLine(Name alias, Table<BpJourneyLineRecord> aliased) {
        this(alias, aliased, null);
    }

    private BpJourneyLine(Name alias, Table<BpJourneyLineRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.bp_journey_line</code> table reference
     */
    public BpJourneyLine(String alias) {
        this(DSL.name(alias), BP_JOURNEY_LINE);
    }

    /**
     * Create an aliased <code>public.bp_journey_line</code> table reference
     */
    public BpJourneyLine(Name alias) {
        this(alias, BP_JOURNEY_LINE);
    }

    /**
     * Create a <code>public.bp_journey_line</code> table reference
     */
    public BpJourneyLine() {
        this(DSL.name("bp_journey_line"), null);
    }

    public <O extends Record> BpJourneyLine(Table<O> child, ForeignKey<O, BpJourneyLineRecord> key) {
        super(child, key, BP_JOURNEY_LINE);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<BpJourneyLineRecord, Long> getIdentity() {
        return (Identity<BpJourneyLineRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<BpJourneyLineRecord> getPrimaryKey() {
        return Keys.PK_BP_JOURNEY_LINE;
    }

    @Override
    public List<UniqueKey<BpJourneyLineRecord>> getKeys() {
        return Arrays.<UniqueKey<BpJourneyLineRecord>>asList(Keys.PK_BP_JOURNEY_LINE);
    }

    @Override
    public BpJourneyLine as(String alias) {
        return new BpJourneyLine(DSL.name(alias), this);
    }

    @Override
    public BpJourneyLine as(Name alias) {
        return new BpJourneyLine(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public BpJourneyLine rename(String name) {
        return new BpJourneyLine(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public BpJourneyLine rename(Name name) {
        return new BpJourneyLine(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, Long, Long> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
