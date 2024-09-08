package org.cocktail.thenadlee.common.exception;

import org.cocktail.thenadlee.common.error.ErrorCodeIfs;

public interface ApiExceptionIfs {
    ErrorCodeIfs errorcodeifs();

    String errorDescription();
}
