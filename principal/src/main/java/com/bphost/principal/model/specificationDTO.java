package com.bphost.principal.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class specificationDTO {
    private Integer prod_id;
    private Integer size_id;
    private String  size;
    private Integer color_id;
    private String  color;
    private Integer storage;
}
