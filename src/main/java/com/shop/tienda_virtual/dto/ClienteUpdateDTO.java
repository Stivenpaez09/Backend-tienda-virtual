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
public class ClienteUpdateDTO {
    @Nullable
    private String nombre;

    @Nullable
    private String apellido;

    @Nullable
    private String cedula;
}
