package com.code.aes256;


import static org.apache.tomcat.util.codec.binary.Base64.decodeBase64;

import java.util.ArrayList;
import java.util.Objects;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.code.aes256.RequestData;

@RestController
@RequestMapping("/api/aes")
@Slf4j
public class AESController {

  private static final String AES_ALGORITHM = "AES/CBC/PKCS5Padding";

  private final String secretKey = "Callmeback3-CelulaGoldenMadmoney"; // Clave de 32 caracteres (256 bits)
  //private final String iv = "NX3@9YP(B;CNRU8="; // Vector de inicialización de 16 caracteres (128 bits)
  private final String iv = "NX3@9YP(B;CNRU8="; // Vector de inicialización de 16 caracteres (128 bits)

  @PostMapping("/decrypt")
  public List<UserData> decryptUserData(@RequestBody List<UserData> userDataList) {
    return userDataList.stream()
        .map(this::decrypt)
        .collect(Collectors.toList());
  }

  private UserData decrypt(UserData userData) {
    try {
      log.info("Desencriptando datos del usuario: {}", userData);
      UserData userData1 = new UserData();
      userData1.set_id(userData.get_id());
      userData1.setCif(userData.getCif());
      userData1.setIdentifier(decrypt(userData.getIdentifier()));
      userData1.setEmail(decrypt(userData.getEmail()));
      userData1.setCellPhone(decrypt(userData.getCellPhone()));
      RequestData requestData = new RequestData();
      if (userData.getRequest() == null || userData.getRequest().isEmpty()) {
        requestData.setNewCellPhone(null);
      } else {
        requestData.setNewCellPhone(decrypt(userData.getRequest().get(0).getNewCellPhone()));
        requestData.setOpportunityId(userData.getRequest().get(0).getOpportunityId());
        requestData.setChannel(userData.getRequest().get(0).getChannel());
        requestData.setStatus(userData.getRequest().get(0).getStatus());
        requestData.setCreatedDate(userData.getRequest().get(0).getCreatedDate());
        CampaignList campaignList = new CampaignList();
        if (userData.getRequest().get(0).getCampaignList() == null
            || userData.getRequest().get(0).getCampaignList().isEmpty()) {
          campaignList.setProductName(null);
        } else {
          campaignList.setProductName(
              userData.getRequest().get(0).getCampaignList().get(0).getProductName());
          requestData.setCampaignList(List.of(campaignList));
        }
      }
      userData1.setRequest(List.of(requestData));
      if(userData1.getCellPhone()!=null && userData1.getCellPhone().length()==9) {
        return userData1;
      }
      return null;
    } catch (Exception e) {
      throw new RuntimeException("Error al desencriptar los datos", e);
    }
  }

  public String decrypt(String text) {
    if (!isBlank(text)) {
      return decryptAES(text);
    }
    return text;
  }

  public String decryptAES(String encrypted) {
    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
      IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
      byte[] enc = decodeBase64(encrypted);
      cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
      byte[] decrypted = cipher.doFinal(enc);
      return new String(decrypted);

  } catch (IllegalArgumentException e) {
    log.error("El texto encriptado no está correctamente codificado en Base64: {}", e.getMessage());
  } catch (javax.crypto.BadPaddingException e) {
    log.error("Error al desencriptar: clave o IV incorrectos, o datos corruptos: {}", e.getMessage());
  } catch (Exception e) {
    log.error("Error inesperado al desencriptar: {}", e.getMessage());
  }
    return null;
  }

  public synchronized static boolean isBlank(Object value) {
    return Objects.isNull(value) || String.valueOf(value).trim().isEmpty();
  }
}