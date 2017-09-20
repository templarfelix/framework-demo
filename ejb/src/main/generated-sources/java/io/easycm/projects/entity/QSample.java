package io.easycm.projects.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSample is a Querydsl query type for Sample
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSample extends EntityPathBase<Sample> {

    private static final long serialVersionUID = 1555646779L;

    public static final QSample sample = new QSample("sample");

    public final io.easycm.framework.base.entity.impl.QLogBaseEntityImpl _super = new io.easycm.framework.base.entity.impl.QLogBaseEntityImpl(this);

    //inherited
    public final DateTimePath<java.util.Date> creationDate = _super.creationDate;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> updatedDate = _super.updatedDate;

    //inherited
    public final NumberPath<Long> userCreated = _super.userCreated;

    //inherited
    public final NumberPath<Long> userUpdated = _super.userUpdated;

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public QSample(String variable) {
        super(Sample.class, forVariable(variable));
    }

    public QSample(Path<? extends Sample> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSample(PathMetadata metadata) {
        super(Sample.class, metadata);
    }

}

