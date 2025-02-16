package io.scout.controller;

import java.util.List;

/**
 * @author DEV Scout
 */
public class SystemResponse<T> {

  private short code;
  private boolean state;
  private String message;
  private List<T> content;

  public SystemResponse() {}

  public SystemResponse(short code, boolean state, String message) {
    this.code = code;
    this.state = state;
    this.message = message;
  }

  public short getCode() {
    return code;
  }

  public void setCode(short code) {
    this.code = code;
  }

  public boolean isState() {
    return state;
  }

  public void setState(boolean state) {
    this.state = state;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<T> getContent() {
    return content;
  }

  public void setContent(List<T> content) {
    this.content = content;
  }
}