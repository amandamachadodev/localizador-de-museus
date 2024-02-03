package com.betrybe.museumfinder.advice;

import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Gerenciador de exceções.
 */
@ControllerAdvice
public class GeneralControllerAdvice {
  /**
   * Coordenada inválida.
   */
  @ExceptionHandler(InvalidCoordinateException.class)
  public ResponseEntity<String> handleInvalidCoordinateException(
      InvalidCoordinateException exception) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body("Coordenada inválida!");
  }

  /**
   * Museu não encontrado.
   */
  @ExceptionHandler(MuseumNotFoundException.class)
  public ResponseEntity<String> handleMuseumNotFoundExcpetionon(
      MuseumNotFoundException exception) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body("Museu não encontrado!");
  }

  /**
   * Outros erros.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception exception) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Erro interno!");
  }
}
