package io.easycm.projects.entity.builder;

import io.easycm.projects.entity.DynamicField;
import com.mysema.codegen.StringUtils;
import net.bytebuddy.description.modifier.FieldManifestation;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FieldAccessor;

/**
 * Created by Fernando on 10/04/2016.
 */
public class LongBuilder implements DefaultBuilder {

  @Override
  public DynamicType.Builder makeField(DynamicType.Builder builder, DynamicField field) {

    DynamicType.Builder.FieldDefinition.Optional fieldMaked = builder
        .defineField(field.getName(), getClassType(), Visibility.PRIVATE, FieldManifestation.PLAIN);

    // id
    fieldMaked = makeId(fieldMaked, field);

    //formColumn
    fieldMaked = formColumn(fieldMaked, field);

    builder = fieldMaked;

    //get
    builder = builder.defineMethod("get" + StringUtils.capitalize(field.getName()), getClassType(),
        Visibility.PUBLIC)
        .intercept(FieldAccessor.ofField(field.getName()));

    //set
    builder = builder
        .defineMethod("set" + StringUtils.capitalize(field.getName()), Void.TYPE, Visibility.PUBLIC)
        .withParameter(getClassType())
        .intercept(FieldAccessor.ofField(field.getName()));

    return builder;
  }

  @Override
  public Class getClassType() {
    return Long.class;
  }
}
