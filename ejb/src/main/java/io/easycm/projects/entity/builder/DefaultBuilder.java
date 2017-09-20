package io.easycm.projects.entity.builder;

import io.easycm.projects.entity.DynamicField;
import io.easycm.framework.base.form.FieldType;
import io.easycm.framework.base.form.FormColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.dynamic.DynamicType;

/**
 * Created by Fernando on 10/04/2016.
 */
public interface DefaultBuilder {

  public DynamicType.Builder makeField(DynamicType.Builder builder, DynamicField field);

  public Class getClassType();

  public default DynamicType.Builder.FieldDefinition.Optional makeId(
      DynamicType.Builder.FieldDefinition.Optional fieldMaked, DynamicField field) {
    if (field.isId()) {
      fieldMaked = fieldMaked.annotateField(AnnotationDescription.Builder.ofType(Id.class)
          .build())
          .annotateField(AnnotationDescription.Builder.ofType(GeneratedValue.class)
              .define("strategy", GenerationType.IDENTITY)
              .build());
    }

    return fieldMaked;
  }

  public default DynamicType.Builder.FieldDefinition.Optional formColumn(
      DynamicType.Builder.FieldDefinition.Optional fieldMaked, DynamicField field) {
    // FORM
    fieldMaked = fieldMaked.annotateField(AnnotationDescription.Builder.ofType(FormColumn.class)
        .define("type", FieldType.AUTO)
        .define("label", field.getName())
        // fazer vir do Field
        .define("required", true)
        .define("placeholder", field.getName())
        .define("description", field.getDescription())
        .define("filter", true)
        .define("grid", true)
        .build());

    return fieldMaked;
  }
}
