package com.adminserver.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Getter
@DynamicUpdate @DynamicInsert
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pc_status")
public class PcStatusEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long pcIdx;

    @Column(unique = true)
    private String ip;

    @Column(name = "pc_status")
    private String pcStatus;

    @Column(name = "open")
    private boolean open;

    @Column(name = "type")
    private String type;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private String time;


}
