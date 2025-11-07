package com.code.aes256;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestData implements Serializable {

  private String newCellPhone;
  private String opportunityId;
  private String channel;
  private String status;
  private String createdDate;
  private List<CampaignList> campaignList;
}
