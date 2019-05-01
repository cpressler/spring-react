package com.softvision.example.springboot.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "user_group")
public class Group {

    @Id
    @GeneratedValue
    @ApiModelProperty(notes = "The database generated group ID")
    private Long id;
    @NonNull
    @ApiModelProperty(notes = "The group name")
    private String name;
    @ApiModelProperty(notes = "The group address")
    private String address;
    @ApiModelProperty(notes = "The group city")
    private String city;
    @ApiModelProperty(notes = "The group state")
    private String stateOrProvince;
    @ApiModelProperty(notes = "The group country")
    private String country;
    @ApiModelProperty(notes = "The group zipcode")
    private String postalCode;
    @ManyToOne(cascade=CascadeType.PERSIST)
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @ApiModelProperty(notes = "The group events")
    private Set<Event> events;
}