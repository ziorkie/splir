package com.ziora.splir.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FriendshipIdentity implements Serializable {

    private Long friendAId;
    private Long friendBId;

    public FriendshipIdentity() {
    }

    public FriendshipIdentity(Long friendAId, Long friendBId) {
        this.friendAId = friendAId;
        this.friendBId = friendBId;
    }

    public Long getFriendAId() {
        return friendAId;
    }

    public void setFriendAId(Long friendAId) {
        this.friendAId = friendAId;
    }

    public Long getFriendBId() {
        return friendBId;
    }

    public void setFriendBId(Long friendBId) {
        this.friendBId = friendBId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendshipIdentity that = (FriendshipIdentity) o;

        if (!friendAId.equals(that.friendAId)) return false;
        return friendBId.equals(that.friendBId);
    }

    @Override
    public int hashCode() {
        int result = friendAId.hashCode();
        result = 31 * result + friendBId.hashCode();
        return result;
    }
}
