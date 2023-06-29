package com.adminserver.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QViewerPowerStateLogEntity is a Querydsl query type for ViewerPowerStateLogEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QViewerPowerStateLogEntity extends EntityPathBase<ViewerPowerStateLogEntity> {

    private static final long serialVersionUID = 1055288231L;

    public static final QViewerPowerStateLogEntity viewerPowerStateLogEntity = new QViewerPowerStateLogEntity("viewerPowerStateLogEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isOn = createBoolean("isOn");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> viewerId = createNumber("viewerId", Long.class);

    public QViewerPowerStateLogEntity(String variable) {
        super(ViewerPowerStateLogEntity.class, forVariable(variable));
    }

    public QViewerPowerStateLogEntity(Path<? extends ViewerPowerStateLogEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QViewerPowerStateLogEntity(PathMetadata metadata) {
        super(ViewerPowerStateLogEntity.class, metadata);
    }

}

