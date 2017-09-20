package io.easycm.projects.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPoolStatus is a Querydsl query type for PoolStatus
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPoolStatus extends EntityPathBase<PoolStatus> {

    private static final long serialVersionUID = 1905226303L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPoolStatus poolStatus = new QPoolStatus("poolStatus");

    public final io.easycm.framework.base.entity.impl.QLogBaseEntityImpl _super = new io.easycm.framework.base.entity.impl.QLogBaseEntityImpl(this);

    public final NumberPath<Integer> activeConnections = createNumber("activeConnections", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> idleConnections = createNumber("idleConnections", Integer.class);

    public final io.easycm.framework.security.entity.QTenant tenant;

    public final NumberPath<Integer> threadsAwaitingConnection = createNumber("threadsAwaitingConnection", Integer.class);

    public final NumberPath<Integer> totalConnections = createNumber("totalConnections", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> updatedDate = _super.updatedDate;

    //inherited
    public final NumberPath<Long> userCreated = _super.userCreated;

    //inherited
    public final NumberPath<Long> userUpdated = _super.userUpdated;

    public QPoolStatus(String variable) {
        this(PoolStatus.class, forVariable(variable), INITS);
    }

    public QPoolStatus(Path<? extends PoolStatus> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPoolStatus(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPoolStatus(PathMetadata metadata, PathInits inits) {
        this(PoolStatus.class, metadata, inits);
    }

    public QPoolStatus(Class<? extends PoolStatus> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tenant = inits.isInitialized("tenant") ? new io.easycm.framework.security.entity.QTenant(forProperty("tenant")) : null;
    }

}

