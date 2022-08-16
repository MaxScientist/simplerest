package com.websocket.simplerest.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
public class Simple {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;
}
