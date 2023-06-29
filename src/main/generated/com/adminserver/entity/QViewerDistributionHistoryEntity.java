package com.adminserver.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QViewerDistributionHistoryEntity is a Querydsl query type for ViewerDistributionHistoryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QViewerDistributionHistoryEntity extends EntityPathBase<ViewerDistributionHistoryEntity> {

    private static final long serialVersionUID = 1380571391L;

    public static final QViewerDistributionHistoryEntity viewerDistributionHistoryEntity = new QViewerDistributionHistoryEntity("viewerDistributionHistoryEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> viewerId = createNumber("viewerId", Long.class);

    public final NumberPath<Long> xrayId = createNumber("xrayId", Long.class);

    public QViewerDistributionHistoryEntity(String variable) {
        super(ViewerDistributionHistoryEntity.class, forVariable(variable));
    }

    public QViewerDistributionHistoryEntity(Path<? extends ViewerDistributionHistoryEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QViewerDistributionHistoryEntity(PathMetadata metadata) {
        super(ViewerDistributionHistoryEntity.class, metadata);
    }

}

