package com.base.dtos.others;

import com.base.dtos.userinfo.AuthorityInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JwtAuthorityDTO extends JwtDTO {

    private Boolean enableAdmin;

    private Boolean enablePartner;

    public JwtAuthorityDTO(String token, List<AuthorityInfo> authorityInfos) {
        super(token);
        this.enableAdmin = false;
        this.enablePartner = false;
        for (AuthorityInfo authorityInfo : authorityInfos) {
            if (authorityInfo.getEnableAdmin()) {
                this.enableAdmin = true;
                break;
            }
        }
        for (AuthorityInfo authorityInfo : authorityInfos) {
            if (authorityInfo.getEnablePartner()) {
                this.enablePartner = true;
                break;
            }
        }
    }
}