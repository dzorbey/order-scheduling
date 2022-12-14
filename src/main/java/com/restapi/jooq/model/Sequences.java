/*
 * This file is generated by jOOQ.
 */
package com.restapi.jooq.model;


import org.jooq.Sequence;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;


/**
 * Convenience access to all sequences in public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

    /**
     * The sequence <code>public.bp_compartment_allocation_id_seq</code>
     */
    public static final Sequence<Long> BP_COMPARTMENT_ALLOCATION_ID_SEQ = Internal.createSequence("bp_compartment_allocation_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.bp_journey_id_seq</code>
     */
    public static final Sequence<Long> BP_JOURNEY_ID_SEQ = Internal.createSequence("bp_journey_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.bp_journey_line_id_seq</code>
     */
    public static final Sequence<Long> BP_JOURNEY_LINE_ID_SEQ = Internal.createSequence("bp_journey_line_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.bp_order_id_seq</code>
     */
    public static final Sequence<Long> BP_ORDER_ID_SEQ = Internal.createSequence("bp_order_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);

    /**
     * The sequence <code>public.bp_order_line_id_seq</code>
     */
    public static final Sequence<Long> BP_ORDER_LINE_ID_SEQ = Internal.createSequence("bp_order_line_id_seq", Public.PUBLIC, SQLDataType.BIGINT.nullable(false), 1000, null, 1000, null, false, null);
}
