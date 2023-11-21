package com.api.spring.doc.app.canal.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Instância Bean do ModelMapper para mapper de Entity para DTO ou vice-versa.
 * </p>
 * 
 * Referência: {@linkplain http://modelmapper.org/getting-started/}
 * 
 * @author cleberson.pauluci
 *
 */
@Slf4j
@Configuration
@ComponentScan("br.com.datasystem.sinistro.api")
public class ApplicationModelMapper {

  @Bean
  public ModelMapper modelMapper() {
	log.info("[OPEN-API 3] - modelMapper - inicializado");
    final ModelMapper mp = new ModelMapper();
    mp.getConfiguration().setAmbiguityIgnored(true).setDeepCopyEnabled(false)
        .setFullTypeMatchingRequired(true).setMatchingStrategy(MatchingStrategies.STRICT);
    return mp;
  }
}
