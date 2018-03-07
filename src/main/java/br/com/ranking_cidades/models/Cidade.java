package br.com.ranking_cidades.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.Data;
import lombok.NonNull;

/**
 *
 * @author Igor Queiroz
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cidade implements Serializable {
    
    private static final long serialVersionUID = 8921012526124239L;
    
    @NonNull
    private String Nome;
    
    @NonNull
    private String Estado;
    
}
