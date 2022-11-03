package com.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Name cannot be null")
    @Size(min = 2, message = "Vui lòng nhập tối thiểu 2 kí tự trở lên")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Status cannot be null")
    @Column(name = "status")
    @ColumnDefault(value = "1")
    private Integer status;

}
