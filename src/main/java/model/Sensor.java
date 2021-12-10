package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class Sensor {
    private String fechaGeneracion;
    private Integer idDispositivo;
    private Double temperatura;
    private Double humedad;
}