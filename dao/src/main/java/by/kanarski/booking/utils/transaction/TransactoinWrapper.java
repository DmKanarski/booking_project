package by.kanarski.booking.utils.transaction;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public interface TransactoinWrapper {

    void begin();

    void commit();

    void rollback();

    void clean();
}