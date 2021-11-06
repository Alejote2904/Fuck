package com.bike.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Esta clase representa a la entidad Bicicleta
 * @author desaextremo
 */
@Entity
@Table(name = "bike")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bike implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * Codigo de la bicicleta
     */
    private Integer id;
    /**
     * Nombre de la bicicleta
     */
    private String name;
    /**
     * Marca de la bicicleta
     */
    private String brand;
    /**
     * Año o modelo de la bicicleta
     */
    private Integer year;
    /**
     * Descripcion de la bicicleta
     */
    private String description;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonIgnoreProperties("bikes")
    /**
     * Categoria de la bicileta
     */
    private Category category;


    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "bike")
    @JsonIgnoreProperties({"bike","client"})
    /**
     * Mensajes asociados con la bicileta
     */
    private List<Message> messages;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "bike")
    @JsonIgnoreProperties({"bike","messages"})
     /**
     * Reservaciones asociadas con la bicileta
     */
    public List<Reservation> reservations;
}
