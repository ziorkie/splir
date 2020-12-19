package com.ziora.splir.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "friendships")
public class Friendship {

    @EmbeddedId
    private FriendshipIdentity friendshipIdentity;

    public Friendship() {
    }

    public Friendship(FriendshipIdentity friendshipIdentity) {
        this.friendshipIdentity = friendshipIdentity;
    }

    public FriendshipIdentity getFriendshipIdentity() {
        return friendshipIdentity;
    }

    public void setFriendshipIdentity(FriendshipIdentity friendshipIdentity) {
        this.friendshipIdentity = friendshipIdentity;
    }
}
