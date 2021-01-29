package com.ziora.splir.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="invitations")
public class Invitation {

    @EmbeddedId
    private InvitationIdentity invitationIdentity;

    public Invitation() {
    }

    public Invitation(InvitationIdentity invitationIdentity) {
        this.invitationIdentity = invitationIdentity;
    }

    public InvitationIdentity getInvitationIdentity() {
        return invitationIdentity;
    }

    public void setInvitationIdentity(InvitationIdentity invitationIdentity) {
        this.invitationIdentity = invitationIdentity;
    }
}
