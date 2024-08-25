package com.paung.utils;

import org.hibernate.annotations.IdGeneratorType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@IdGeneratorType(ULIDGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface ULIDGenerated {
  boolean usePersistentCounter() default false;
}
