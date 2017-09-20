package io.easycm.projects.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDynamicFieldEntity is a Querydsl query type for DynamicFieldEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDynamicFieldEntity extends EntityPathBase<DynamicFieldEntity> {

    private static final long serialVersionUID = 59984335L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDynamicFieldEntity dynamicFieldEntity = new QDynamicFieldEntity("dynamicFieldEntity");

    public final QDynamicField _super = new QDynamicField(this);

    //inherited
    public final BooleanPath asId = _super.asId;

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final StringPath description = _super.description;

    public final QDynamicEntity entity;

    //inherited
    public final EnumPath<io.easycm.projects.entity.enums.FieldType> fieldType = _super.fieldType;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final BooleanPath isArray = _super.isArray;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final DateTimePath<java.util.Date> updatedDate = _super.updatedDate;

    //inherited
    public final NumberPath<Long> userCreated = _super.userCreated;

    //inherited
    public final NumberPath<Long> userUpdated = _super.userUpdated;

    public QDynamicFieldEntity(String variable) {
        this(DynamicFieldEntity.class, forVariable(variable), INITS);
    }

    public QDynamicFieldEntity(Path<? extends DynamicFieldEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDynamicFieldEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDynamicFieldEntity(PathMetadata metadata, PathInits inits) {
        this(DynamicFieldEntity.class, metadata, inits);
    }

    public QDynamicFieldEntity(Class<? extends DynamicFieldEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.entity = inits.isInitialized("entity") ? new QDynamicEntity(forProperty("entity"), inits.get("entity")) : null;
    }

}

