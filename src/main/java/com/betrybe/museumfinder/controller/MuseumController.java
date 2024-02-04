package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  /**
   * Método get / retorna museu mais próximo.
   */
  @GetMapping("/closest")
  public ResponseEntity<MuseumDto> getMuseumClosest(@RequestParam(name = "lat") String lat,
      @RequestParam(name = "lng") String lng, @RequestParam(name = "max_dist_km") String max) {
    Coordinate coordinate = new Coordinate(Double.parseDouble(lat), Double.parseDouble(lng));
    Museum museum = museumServiceInterface
        .getClosestMuseum(coordinate, Double.parseDouble(max));
    MuseumDto dtoConvert = ModelDtoConverter.modelToDto(museum);
    return ResponseEntity.status(HttpStatus.OK).body(dtoConvert);
  }

  /**
   * Método get / retorna museu com 'id' enviado.
   */
  @GetMapping("/{id}")
  public Museum getMuseum(@PathVariable(name = "id") Long id) {
    return museumServiceInterface.getMuseum(id);
  }
}
