package hu.student.projlab.mealride_api.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
public class JwtResponse implements Serializable {
  private String accessToken;
  private String type = "Bearer";
  private String username;
  private Collection<? extends GrantedAuthority> authorities;
 
  public JwtResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities) {
    this.accessToken = accessToken;
    this.username = username;
    this.authorities = authorities;
  }
}
