package br.com.ranking_cidades.controller;

import br.com.ranking_cidades.models.Cidade;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Igor Queiroz
 */
@Log4j2
@CrossOrigin
@RestController
public class CidadeController {
    
    @Value( "${service.url}" )
    private String serviceURL;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<List<Cidade>> getCidade(
            @RequestParam(name = "nome", required = false) String nomeCidade, 
            @RequestParam(name = "estado", required = false) String nomeEstado) {
        List<Cidade> cidadesFiltradas = null;
        if (nomeCidade != null && !nomeCidade.trim().isEmpty()) {
            final List<Cidade> cidades = Arrays.asList(restTemplate.getForObject(serviceURL + "BuscaTodasCidades", Cidade[].class));
            Stream<Cidade> filter = cidades.stream().filter(cidade -> cidade.getNome().toUpperCase().contains(nomeCidade.toUpperCase()));
            if (nomeEstado != null && !nomeEstado.trim().isEmpty()) {
                filter = filter.filter(cidade -> cidade.getEstado().toUpperCase().contains(nomeEstado.toUpperCase()));
            }
            cidadesFiltradas = filter.collect(Collectors.toList());
        }
        return new ResponseEntity<>(cidadesFiltradas, HttpStatus.OK);
    }
    
    @PostMapping(path = "/pontos", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<String> getPontos(@RequestBody Cidade cidade) {
        if (cidade != null && !cidade.getNome().trim().isEmpty() && !cidade.getEstado().trim().isEmpty()) {
            final String pontos = restTemplate.postForObject(serviceURL + "BuscaPontos", cidade, String.class);
            return new ResponseEntity<>(pontos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
