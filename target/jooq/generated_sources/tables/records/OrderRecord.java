/*
 * This file is generated by jOOQ.
 */
package generated_sources.tables.records;


import generated_sources.tables.Order;

import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class OrderRecord extends UpdatableRecordImpl<OrderRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.order.id</code>.
     */
    public OrderRecord setId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.order.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.order.date_order</code>.
     */
    public OrderRecord setDateOrder(LocalDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.order.date_order</code>.
     */
    public LocalDateTime getDateOrder() {
        return (LocalDateTime) get(1);
    }

    /**
     * Setter for <code>public.order.date_delivery</code>.
     */
    public OrderRecord setDateDelivery(LocalDateTime value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.order.date_delivery</code>.
     */
    public LocalDateTime getDateDelivery() {
        return (LocalDateTime) get(2);
    }

    /**
     * Setter for <code>public.order.date_recieve</code>.
     */
    public OrderRecord setDateRecieve(LocalDateTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.order.date_recieve</code>.
     */
    public LocalDateTime getDateRecieve() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>public.order.user_id</code>.
     */
    public OrderRecord setUserId(Long value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.order.user_id</code>.
     */
    public Long getUserId() {
        return (Long) get(4);
    }

    /**
     * Setter for <code>public.order.status</code>.
     */
    public OrderRecord setStatus(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.order.status</code>.
     */
    public String getStatus() {
        return (String) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached OrderRecord
     */
    public OrderRecord() {
        super(Order.ORDER);
    }

    /**
     * Create a detached, initialised OrderRecord
     */
    public OrderRecord(Long id, LocalDateTime dateOrder, LocalDateTime dateDelivery, LocalDateTime dateRecieve, Long userId, String status) {
        super(Order.ORDER);

        setId(id);
        setDateOrder(dateOrder);
        setDateDelivery(dateDelivery);
        setDateRecieve(dateRecieve);
        setUserId(userId);
        setStatus(status);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised OrderRecord
     */
    public OrderRecord(generated_sources.tables.pojos.Order value) {
        super(Order.ORDER);

        if (value != null) {
            setId(value.getId());
            setDateOrder(value.getDateOrder());
            setDateDelivery(value.getDateDelivery());
            setDateRecieve(value.getDateRecieve());
            setUserId(value.getUserId());
            setStatus(value.getStatus());
            resetChangedOnNotNull();
        }
    }
}
