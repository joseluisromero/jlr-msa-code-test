package com.code.aes256;


import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserData implements Serializable {

  private String _id;
  private String identifier;
  private String email;
  private String cellPhone;
  private String cif;
  private List<RequestData> request ;
}


