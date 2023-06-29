package com.adminserver.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QXrayInfoEntity is a Querydsl query type for XrayInfoEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QXrayInfoEntity extends EntityPathBase<XrayInfoEntity> {

    private static final long serialVersionUID = -1174668475L;

    public static final QXrayInfoEntity xrayInfoEntity = new QXrayInfoEntity("xrayInfoEntity");

    public final NumberPath<Integer> distributionCount = createNumber("distributionCount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isOn = createBoolean("isOn");

    public final DateTimePath<java.time.LocalDateTime> modifiedDate = createDateTime("modifiedDate", java.time.LocalDateTime.class);

    public final StringPath name = createString("name");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath status = createString("status");

    public QXrayInfoEntity(String variable) {
        super(XrayInfoEntity.class, forVariable(variable));
    }

    public QXrayInfoEntity(Path<? extends XrayInfoEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QXrayInfoEntity(PathMetadata metadata) {
        super(XrayInfoEntity.class, metadata);
    }

}

