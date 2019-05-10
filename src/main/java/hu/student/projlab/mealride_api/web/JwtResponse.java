package hu.student.projlab.mealride_api.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class JwtResponse implements Serializable {
  private String accessToken;
  private String type = "Bearer";
  private String username;
  private List<String> authorities;
 
  public JwtResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities) {
    this.accessToken = accessToken;
    this.username = username;

    // This is necessary because the MockMvc test cannot serialize the GrantedAuthority type.
    this.authorities = authorities.stream()
            .map(authority -> (authority).getAuthority())
            .collect(Collectors.toList());
  }
}
