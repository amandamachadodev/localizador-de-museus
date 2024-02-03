package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller Museum.
 */
@RestController
@RequestMapping("/museums")
public class MuseumController {
  MuseumServiceInterface museumServiceInterface;

  @Autowired
  public MuseumController(MuseumServiceInterface museumServiceInterface) {
    this.museumServiceInterface = museumServiceInterface;
  }

  /**
   * Método post / cria Museu.
   */
  @PostMapping
  public ResponseEntity<MuseumDto> postMuseum(@RequestBody MuseumCreationDto museumCreationDto) {
    Museum modelConvert = ModelDtoConverter.dtoToModel(museumCreationDto);
    Museum museum = museumServiceInterface.createMuseum(modelConvert);
    MuseumDto dtoConvert = ModelDtoConverter.modelToDto(museum);
    return ResponseEntity.status(HttpStatus.CREATED).body(dtoConvert);
  }
}
