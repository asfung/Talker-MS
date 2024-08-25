package com.paung.utils;

import cn.hutool.core.lang.Snowflake;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;
import org.hibernate.generator.EventTypeSets;

import java.util.EnumSet;

public class SNFIdGenerator implements BeforeExecutionGenerator {

  @Override
  public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o, Object o1, EventType eventType) {
//    return new Snowflake();
    Snowflake snowflake = new Snowflake();
    return snowflake.nextIdStr();
  }

  @Override
  public EnumSet<EventType> getEventTypes() {
    return EventTypeSets.INSERT_ONLY;
  }
}
