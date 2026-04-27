package com.api.elifeconnect.aop;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogDocumentOperation {
    String operationType(); // "UPLOAD" or "DOWNLOAD"
}
