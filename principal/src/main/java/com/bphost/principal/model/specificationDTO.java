package com.bphost.principal.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class specificationDTO {
    private Integer                 prod_id;
    private Integer                 size_id;
    private String                  size;
    private Integer                 color_id;
    private String                  color;
    private Integer                 storage;
    private List<specificationDTO2> colorActives;

    public specificationDTO(Integer prod_id, 
                            Integer size_id, 
                            String  size, 
                            Integer color_id, 
                            String  color,
                            Integer storage) {
        this.prod_id  = prod_id;
        this.size_id  = size_id;
        this.size     = size;
        this.color_id = color_id;
        this.color    = color;
        this.storage  = storage;
    }

    public specificationDTO(Integer size_id, String size) {
        this.size_id = size_id;
        this.size    = size;
    }

}
