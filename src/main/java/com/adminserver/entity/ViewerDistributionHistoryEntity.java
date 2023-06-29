package com.adminserver.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Builder
@Getter
@DynamicUpdate @DynamicInsert
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "distribution_history")
public class ViewerDistributionHistoryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "viewer_id", nullable = false)
    private Long viewerId;

    @Column(name = "xray_id", nullable = false)
    private Long xrayId;

    @Column(name = "reg_date", nullable = false, length = 20)
    private LocalDateTime regDate;

}
