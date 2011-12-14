package com.murex.ccportal.client.exceptions;

/**
 * @author hsuleiman
 *         Date: 12/14/11
 *         Time: 11:04 AM
 */
public class ValidationException extends RuntimeException {

  public ValidationException() {
  }

  public ValidationException(String message) {
    super(message);
  }
}
