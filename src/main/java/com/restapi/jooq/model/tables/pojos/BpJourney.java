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
public class BpJourney implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long       id;
    private BigDecimal allocationRatio;
    private Boolean    arranged;
    private Long       container1;
    private Long       container2;
    private Long       contanier3;
    private Long       contanier4;

    public BpJourney() {}

    public BpJourney(BpJourney value) {
        this.id = value.id;
        this.allocationRatio = value.allocationRatio;
        this.arranged = value.arranged;
        this.container1 = value.container1;
        this.container2 = value.container2;
        this.contanier3 = value.contanier3;
        this.contanier4 = value.contanier4;
    }

    public BpJourney(
        Long       id,
        BigDecimal allocationRatio,
        Boolean    arranged,
        Long       container1,
        Long       container2,
        Long       contanier3,
        Long       contanier4
    ) {
        this.id = id;
        this.allocationRatio = allocationRatio;
        this.arranged = arranged;
        this.container1 = container1;
        this.container2 = container2;
        this.contanier3 = contanier3;
        this.contanier4 = contanier4;
    }

    /**
     * Getter for <code>public.bp_journey.id</code>.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.bp_journey.id</code>.
     */
    public BpJourney setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public.bp_journey.allocation_ratio</code>.
     */
    public BigDecimal getAllocationRatio() {
        return this.allocationRatio;
    }

    /**
     * Setter for <code>public.bp_journey.allocation_ratio</code>.
     */
    public BpJourney setAllocationRatio(BigDecimal allocationRatio) {
        this.allocationRatio = allocationRatio;
        return this;
    }

    /**
     * Getter for <code>public.bp_journey.arranged</code>.
     */
    public Boolean getArranged() {
        return this.arranged;
    }

    /**
     * Setter for <code>public.bp_journey.arranged</code>.
     */
    public BpJourney setArranged(Boolean arranged) {
        this.arranged = arranged;
        return this;
    }

    /**
     * Getter for <code>public.bp_journey.container1</code>.
     */
    public Long getContainer1() {
        return this.container1;
    }

    /**
     * Setter for <code>public.bp_journey.container1</code>.
     */
    public BpJourney setContainer1(Long container1) {
        this.container1 = container1;
        return this;
    }

    /**
     * Getter for <code>public.bp_journey.container2</code>.
     */
    public Long getContainer2() {
        return this.container2;
    }

    /**
     * Setter for <code>public.bp_journey.container2</code>.
     */
    public BpJourney setContainer2(Long container2) {
        this.container2 = container2;
        return this;
    }

    /**
     * Getter for <code>public.bp_journey.contanier3</code>.
     */
    public Long getContanier3() {
        return this.contanier3;
    }

    /**
     * Setter for <code>public.bp_journey.contanier3</code>.
     */
    public BpJourney setContanier3(Long contanier3) {
        this.contanier3 = contanier3;
        return this;
    }

    /**
     * Getter for <code>public.bp_journey.contanier4</code>.
     */
    public Long getContanier4() {
        return this.contanier4;
    }

    /**
     * Setter for <code>public.bp_journey.contanier4</code>.
     */
    public BpJourney setContanier4(Long contanier4) {
        this.contanier4 = contanier4;
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
        final BpJourney other = (BpJourney) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (allocationRatio == null) {
            if (other.allocationRatio != null)
                return false;
        }
        else if (!allocationRatio.equals(other.allocationRatio))
            return false;
        if (arranged == null) {
            if (other.arranged != null)
                return false;
        }
        else if (!arranged.equals(other.arranged))
            return false;
        if (container1 == null) {
            if (other.container1 != null)
                return false;
        }
        else if (!container1.equals(other.container1))
            return false;
        if (container2 == null) {
            if (other.container2 != null)
                return false;
        }
        else if (!container2.equals(other.container2))
            return false;
        if (contanier3 == null) {
            if (other.contanier3 != null)
                return false;
        }
        else if (!contanier3.equals(other.contanier3))
            return false;
        if (contanier4 == null) {
            if (other.contanier4 != null)
                return false;
        }
        else if (!contanier4.equals(other.contanier4))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.allocationRatio == null) ? 0 : this.allocationRatio.hashCode());
        result = prime * result + ((this.arranged == null) ? 0 : this.arranged.hashCode());
        result = prime * result + ((this.container1 == null) ? 0 : this.container1.hashCode());
        result = prime * result + ((this.container2 == null) ? 0 : this.container2.hashCode());
        result = prime * result + ((this.contanier3 == null) ? 0 : this.contanier3.hashCode());
        result = prime * result + ((this.contanier4 == null) ? 0 : this.contanier4.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BpJourney (");

        sb.append(id);
        sb.append(", ").append(allocationRatio);
        sb.append(", ").append(arranged);
        sb.append(", ").append(container1);
        sb.append(", ").append(container2);
        sb.append(", ").append(contanier3);
        sb.append(", ").append(contanier4);

        sb.append(")");
        return sb.toString();
    }
}
