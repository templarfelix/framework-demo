package io.easycm.projects.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDynamicEntity is a Querydsl query type for DynamicEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDynamicEntity extends EntityPathBase<DynamicEntity> {

    private static final long serialVersionUID = -532808623L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDynamicEntity dynamicEntity = new QDynamicEntity("dynamicEntity");

    public final io.easycm.framework.base.entity.impl.QLogBaseEntityImpl _super = new io.easycm.framework.base.entity.impl.QLogBaseEntityImpl(this);

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    public final StringPath description = createString("description");

    public final ListPath<DynamicField, QDynamicField> fields = this.<DynamicField, QDynamicField>createList("fields", DynamicField.class, QDynamicField.class, PathInits.DIRECT2);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final BooleanPath isAuditable = createBoolean("isAuditable");

    public final StringPath name = createString("name");

    public final io.easycm.framework.security.entity.QTenant tenant;

    //inherited
    public final DateTimePath<java.util.Date> updatedDate = _super.updatedDate;

    //inherited
    public final NumberPath<Long> userCreated = _super.userCreated;

    //inherited
    public final NumberPath<Long> userUpdated = _super.userUpdated;

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public QDynamicEntity(String variable) {
        this(DynamicEntity.class, forVariable(variable), INITS);
    }

    public QDynamicEntity(Path<? extends DynamicEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDynamicEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDynamicEntity(PathMetadata metadata, PathInits inits) {
        this(DynamicEntity.class, metadata, inits);
    }

    public QDynamicEntity(Class<? extends DynamicEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tenant = inits.isInitialized("tenant") ? new io.easycm.framework.security.entity.QTenant(forProperty("tenant")) : null;
    }

}

