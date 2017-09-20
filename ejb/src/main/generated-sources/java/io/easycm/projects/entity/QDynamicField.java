package io.easycm.projects.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDynamicField is a Querydsl query type for DynamicField
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDynamicField extends EntityPathBase<DynamicField> {

    private static final long serialVersionUID = -1263353140L;

    public static final QDynamicField dynamicField = new QDynamicField("dynamicField");

    public final io.easycm.framework.base.entity.impl.QLogBaseEntityImpl _super = new io.easycm.framework.base.entity.impl.QLogBaseEntityImpl(this);

    public final BooleanPath asId = createBoolean("asId");

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    public final StringPath description = createString("description");

    public final EnumPath<io.easycm.projects.entity.enums.FieldType> fieldType = createEnum("fieldType", io.easycm.projects.entity.enums.FieldType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isArray = createBoolean("isArray");

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.util.Date> updatedDate = _super.updatedDate;

    //inherited
    public final NumberPath<Long> userCreated = _super.userCreated;

    //inherited
    public final NumberPath<Long> userUpdated = _super.userUpdated;

    public QDynamicField(String variable) {
        super(DynamicField.class, forVariable(variable));
    }

    public QDynamicField(Path<? extends DynamicField> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDynamicField(PathMetadata metadata) {
        super(DynamicField.class, metadata);
    }

}

