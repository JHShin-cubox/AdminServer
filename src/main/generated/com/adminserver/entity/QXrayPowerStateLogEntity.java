package com.adminserver.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QXrayPowerStateLogEntity is a Querydsl query type for XrayPowerStateLogEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QXrayPowerStateLogEntity extends EntityPathBase<XrayPowerStateLogEntity> {

    private static final long serialVersionUID = -1822098297L;

    public static final QXrayPowerStateLogEntity xrayPowerStateLogEntity = new QXrayPowerStateLogEntity("xrayPowerStateLogEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isOn = createBoolean("isOn");

    public final StringPath luggageId = createString("luggageId");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> xrayId = createNumber("xrayId", Long.class);

    public QXrayPowerStateLogEntity(String variable) {
        super(XrayPowerStateLogEntity.class, forVariable(variable));
    }

    public QXrayPowerStateLogEntity(Path<? extends XrayPowerStateLogEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QXrayPowerStateLogEntity(PathMetadata metadata) {
        super(XrayPowerStateLogEntity.class, metadata);
    }

}

