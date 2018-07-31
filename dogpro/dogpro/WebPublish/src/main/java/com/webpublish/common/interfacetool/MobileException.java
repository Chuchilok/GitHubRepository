package com.webpublish.common.interfacetool;

public class MobileException extends RuntimeException
{
  private static final long serialVersionUID = 2982141012146067224L;

  public MobileException(String msg)
  {
    super(msg);
  }

  public MobileException(Throwable t) {
    super(t);
  }

  public MobileException(String msg, Throwable t) {
    super(msg, t);
  }
}