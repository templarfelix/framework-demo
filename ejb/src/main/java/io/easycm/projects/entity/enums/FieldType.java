package io.easycm.projects.entity.enums;

import io.easycm.projects.entity.builder.DefaultBuilder;
import io.easycm.projects.entity.builder.EntityBuilder;
import io.easycm.projects.entity.builder.IntegerBuilder;
import io.easycm.projects.entity.builder.LongBuilder;
import io.easycm.projects.entity.builder.StringBuilder;

/**
 * Created by Fernando on 06/01/2015.
 */
// FIXME DECLARAR MAIS TIPOS
public enum FieldType {

  STRING(new StringBuilder()),
  INTEGER(new IntegerBuilder()),
  LONG(new LongBuilder()),
  CLASS(new EntityBuilder());

  private DefaultBuilder type = new StringBuilder();

  FieldType(DefaultBuilder type) {
    this.type = type;
  }

  public DefaultBuilder getType() {
    return type;
  }

}
