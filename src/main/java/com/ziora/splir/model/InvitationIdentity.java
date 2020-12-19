package com.ziora.splir.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class InvitationIdentity implements Serializable {

    private Long invitationId;
    private Long invitedId;

    public InvitationIdentity() {
    }

    public InvitationIdentity(Long invitationId, Long invitedId) {
        this.invitationId = invitationId;
        this.invitedId = invitedId;
    }

    public Long getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Long invitationId) {
        this.invitationId = invitationId;
    }

    public Long getInvitedId() {
        return invitedId;
    }

    public void setInvitedId(Long invitedId) {
        this.invitedId = invitedId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvitationIdentity that = (InvitationIdentity) o;

        if (!invitationId.equals(that.invitationId)) return false;
        return invitedId.equals(that.invitedId);
    }

    @Override
    public int hashCode() {
        int result = invitationId.hashCode();
        result = 31 * result + invitedId.hashCode();
        return result;
    }
}
