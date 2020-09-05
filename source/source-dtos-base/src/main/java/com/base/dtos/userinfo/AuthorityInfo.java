package com.base.dtos.userinfo;

import com.base.dtos.GrantedDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthorityInfo extends GrantedDTO {

    private Boolean isRoot;

    private Boolean enableAdmin;

    private Boolean enableClient;

    private Boolean enablePartner;

    public AuthorityInfo() {
        super();
    }

    public AuthorityInfo(String authority) {
        super(authority);
    }

    public AuthorityInfo(GrantedAuthority authority) {
        super(authority);
    }

    public AuthorityInfo(String authority, Boolean enableAdmin, Boolean enableClient, Boolean enablePartner) {
        super(authority);
        this.enableAdmin = enableAdmin;
        this.enableClient = enableClient;
        this.enablePartner = enablePartner;
    }

    public Boolean getRoot() {
        return isRoot;
    }

    public void setRoot(Boolean root) {
        isRoot = root;
    }

    public Boolean getEnableAdmin() {
        return enableAdmin;
    }

    public void setEnableAdmin(Boolean enableAdmin) {
        this.enableAdmin = enableAdmin;
    }

    public Boolean getEnableClient() {
        return enableClient;
    }

    public void setEnableClient(Boolean enableClient) {
        this.enableClient = enableClient;
    }

    public Boolean getEnablePartner() {
        return enablePartner;
    }

    public void setEnablePartner(Boolean enablePartner) {
        this.enablePartner = enablePartner;
    }
}