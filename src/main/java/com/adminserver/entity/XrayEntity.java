package com.adminserver.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@DynamicInsert @DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "xray_analyze_statistics")
public class XrayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "xray_name", nullable = false)
    private String xrayName;

    @Column(name = "label_id", nullable = false)
    private String labelId;

    @Column(name = "luggage_id")
    private String luggageId;

    @Column(nullable = false)
    private String score;

    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate;

}
