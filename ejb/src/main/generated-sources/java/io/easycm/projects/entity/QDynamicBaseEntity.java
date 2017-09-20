package io.easycm.projects.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDynamicBaseEntity is a Querydsl query type for DynamicBaseEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QDynamicBaseEntity extends EntityPathBase<DynamicBaseEntity> {

    private static final long serialVersionUID = 1827465090L;

    public static final QDynamicBaseEntity dynamicBaseEntity = new QDynamicBaseEntity("dynamicBaseEntity");

    public final io.easycm.framework.base.entity.impl.QLogBaseEntityImpl _super = new io.easycm.framework.base.entity.impl.QLogBaseEntityImpl(this);

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    //inherited
    public final DateTimePath<java.util.Date> updatedDate = _super.updatedDate;

    //inherited
    public final NumberPath<Long> userCreated = _super.userCreated;

    //inherited
    public final NumberPath<Long> userUpdated = _super.userUpdated;

    public final ComparablePath<java.util.UUID> uuid = createComparable("uuid", java.util.UUID.class);

    public QDynamicBaseEntity(String variable) {
        super(DynamicBaseEntity.class, forVariable(variable));
    }

    public QDynamicBaseEntity(Path<? extends DynamicBaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDynamicBaseEntity(PathMetadata metadata) {
        super(DynamicBaseEntity.class, metadata);
    }

}

