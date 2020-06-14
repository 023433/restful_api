package dev.j.api.restful.common.enums;

public enum EnumRole {

  GUEST("GUEST"),
  USER("USER"),
  MANAGER("MANAGER"),
  ADMIN("ADMIN");

  private String label;

  EnumRole(String label){
    this.label = "ROLE_" + label;
  }

  public String label(){
    return label;
  }
    
}