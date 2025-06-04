package com.shop.tienda_virtual.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoUpdateDTO {
    @Nullable
    private String nombre;

    @Nullable
    private String marca;

    @Nullable
    private Double costo;

    @Nullable
    private  Double cantidad_disponible;
}
