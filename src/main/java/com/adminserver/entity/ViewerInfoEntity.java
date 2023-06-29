package com.adminserver.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter
@DynamicUpdate @DynamicInsert
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "viewer_info")
public class ViewerInfoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(name = "is_on", nullable = false)
    private boolean isOn;

    @Column(nullable = false)
    private String status;

    @Column(name = "reg_date", nullable = false, length = 20)
    private LocalDateTime regDate;

    @Column(name = "modified_date", nullable = false, length = 20)
    private LocalDateTime modifiedDate;

    @Column(name = "distribution_count", nullable = false)
    @ColumnDefault("0")
    private Integer distributionCount;




}
