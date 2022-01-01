package com.mohamedcode.customer.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    @Serial
    private static final long serialVersionUID = -5759809582006667247L;

    @Id
    @SequenceGenerator(name = "customer_id_sequence", sequenceName = "customer_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_sequence")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
