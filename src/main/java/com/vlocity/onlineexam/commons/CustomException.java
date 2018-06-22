package com.vlocity.onlineexam.commons;

/**
 * Created by gfs on 22/06/2018.
 */
public class CustomException extends RuntimeException {

  public CustomException(final String msg) {
    super(msg);
  }

  public CustomException(final String msg, final Throwable throwable) {
    super(msg, throwable);
  }

}
