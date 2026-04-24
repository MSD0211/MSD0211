package com.api.elifeconnect.aop;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogApiCall {
    String value() default ""; // Full API Name e.g. "Agent Authentication API"

    String shortName() default ""; // Short API code e.g. "AGNTAUTH"
}
