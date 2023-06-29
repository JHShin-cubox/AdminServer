package com.adminserver.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QViewerInfoEntity is a Querydsl query type for ViewerInfoEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QViewerInfoEntity extends EntityPathBase<ViewerInfoEntity> {

    private static final long serialVersionUID = 485165093L;

    public static final QViewerInfoEntity viewerInfoEntity = new QViewerInfoEntity("viewerInfoEntity");

    public final NumberPath<Integer> distributionCount = createNumber("distributionCount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isOn = createBoolean("isOn");

    public final DateTimePath<java.time.LocalDateTime> modifiedDate = createDateTime("modifiedDate", java.time.LocalDateTime.class);

    public final StringPath name = createString("name");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath status = createString("status");

    public QViewerInfoEntity(String variable) {
        super(ViewerInfoEntity.class, forVariable(variable));
    }

    public QViewerInfoEntity(Path<? extends ViewerInfoEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QViewerInfoEntity(PathMetadata metadata) {
        super(ViewerInfoEntity.class, metadata);
    }

}

