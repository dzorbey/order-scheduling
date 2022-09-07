/*
 * This file is generated by jOOQ.
 */
package com.restapi.jooq.model.tables.pojos;


import java.io.Serializable;
import java.math.BigDecimal;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BpOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long       id;
    private BigDecimal weight;

    public BpOrder() {}

    public BpOrder(BpOrder value) {
        this.id = value.id;
        this.weight = value.weight;
    }

    public BpOrder(
        Long       id,
        BigDecimal weight
    ) {
        this.id = id;
        this.weight = weight;
    }

    /**
     * Getter for <code>public.bp_order.id</code>.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.bp_order.id</code>.
     */
    public BpOrder setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public.bp_order.weight</code>.
     */
    public BigDecimal getWeight() {
        return this.weight;
    }

    /**
     * Setter for <code>public.bp_order.weight</code>.
     */
    public BpOrder setWeight(BigDecimal weight) {
        this.weight = weight;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final BpOrder other = (BpOrder) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (weight == null) {
            if (other.weight != null)
                return false;
        }
        else if (!weight.equals(other.weight))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.weight == null) ? 0 : this.weight.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BpOrder (");

        sb.append(id);
        sb.append(", ").append(weight);

        sb.append(")");
        return sb.toString();
    }
}
