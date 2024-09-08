package org.cocktail.thenadlee.common.error;

public interface ErrorCodeIfs {
    Integer getHttpStatusCode();
    Integer getErrorCode();
    String getDescription();
}
