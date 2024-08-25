package com.paung.utils;

import de.huxhorn.sulky.ulid.ULID;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;
import org.hibernate.generator.EventTypeSets;

import java.util.EnumSet;

public class ULIDGenerator implements BeforeExecutionGenerator {

  @Override
  public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o, Object o1, EventType eventType) {
    ULID ulid = new ULID();
    return ulid.nextULID();
  }

  @Override
  public EnumSet<EventType> getEventTypes() {
    return EventTypeSets.INSERT_ONLY;
  }
}
