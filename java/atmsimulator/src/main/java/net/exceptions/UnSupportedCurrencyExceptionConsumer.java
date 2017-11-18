package net.exceptions;

/**
 * Functional Interface for wrapping the UnsupportedDenominationException.
 * @author Sandip Singh.
 * @param <T>
 */
@FunctionalInterface
public interface UnSupportedCurrencyExceptionConsumer<T> {

    void accept(T t) throws UnSupportedDenominationException;
}
