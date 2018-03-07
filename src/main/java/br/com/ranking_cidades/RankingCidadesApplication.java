package br.com.ranking_cidades;

import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Igor Queiroz
 */
@EnableCaching
@SpringBootApplication
public class RankingCidadesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RankingCidadesApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
