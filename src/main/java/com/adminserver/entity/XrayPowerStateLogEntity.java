package com.adminserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Builder
@Getter
@DynamicUpdate @DynamicInsert
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "xray_power_state_log")
public class XrayPowerStateLogEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "xray_id", nullable = false)
    private Long xrayId;

    @Column(name = "luggage_id", nullable = false)
    private String luggageId;

    @Column(name = "is_on", nullable = false)
    private boolean isOn;

    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate;
}
