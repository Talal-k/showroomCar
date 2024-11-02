package com.example.carshowroom.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Builder
@Entity(name = "showroom")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE showroom SET is_delete = true WHERE id = ?")
@SQLRestriction(value = "is_delete = false")
public class Showroom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "commercial_registration_number", nullable = false, length = 10,unique = true)
    private Long commercialRegistrationNumber;
    @Column(name = "manager_name", length = 255)
    private String managerName;
    @Column(name = "contact_number", length = 15,nullable = false)
    private Long contactNumber;
    @Column(name = "address", length = 255)
    private String address;
    @Column(name = "is_delete", length = 255)
    private boolean isDelete = true;



}
