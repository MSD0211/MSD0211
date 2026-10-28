package com.api.elifeconnect.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.Hidden;

/**
 * Indicates that an API endpoint or controller is currently under development.
 * Endpoints or controllers marked with this annotation are automatically
 * excluded from OpenAPI / Swagger documentation and will not appear in Swagger UI.
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Hidden
public @interface InDevelopment {

    /**
     * Optional description or notes regarding the development status.
     */
    String value() default "In Development";
}
