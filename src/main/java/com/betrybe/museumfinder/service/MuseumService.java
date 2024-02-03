package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.util.CoordinateUtil;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Service Museum.
 */
@Service
public class MuseumService implements MuseumServiceInterface {
  MuseumFakeDatabase museumFakeDatabase;

  public MuseumService(MuseumFakeDatabase museumFakeDatabase) {
    this.museumFakeDatabase = museumFakeDatabase;
  }

  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    if (CoordinateUtil.isCoordinateValid(coordinate)) {
      Optional<Museum> closestMuseum = museumFakeDatabase.getClosestMuseum(coordinate, maxDistance);
      if (closestMuseum.isEmpty()) {
        throw new MuseumNotFoundException("Museu não encontrado");
      } else {
        return closestMuseum.get();
      }
    }
    throw new InvalidCoordinateException("Coordenada inválida.");
  }

  @Override
  public Museum createMuseum(Museum museum) {
    boolean coordinate = CoordinateUtil.isCoordinateValid(museum.getCoordinate());
    if (coordinate) {
      return this.museumFakeDatabase.saveMuseum(museum);
    }
    throw new InvalidCoordinateException("Coordenada inválida.");
  }

  @Override
  public Museum getMuseum(Long id) {
    return null;
  }
}
