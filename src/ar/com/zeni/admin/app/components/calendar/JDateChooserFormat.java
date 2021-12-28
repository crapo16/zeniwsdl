/*
 * Created on Apr 6, 2005
 *
 * Proyecto: Jazz
 * Java: JDateChooserFormat.java
 * Autor: JQUIROGA
 * Copyright (c) Apertura Software S.A. 2005
 */

package ar.com.zeni.admin.app.components.calendar;

public interface JDateChooserFormat {
  public int FORMAT_LATIN = 0;
  public int FORMAT_USA   = 1;
  public int FORMAT_JAPAN = 2;

  public String DATE_LATIN = "dd/MM/yyyy";
  public String DATE_USA = "MM/dd/yyyy";
  public String DATE_JAPAN = "yyyy/MM/dd";
  
  public String MASK_LATIN = "##/##/####"; 
  public String MASK_USA = "##/##/####";
  public String MASK_JAPAN = "####/##/##";
  
  public String EMPTY_LATIN = "__/__/____";
  public String EMPTY_USA = "__/__/____";
  public String EMPTY_JAPAN = "____/__/__";
}