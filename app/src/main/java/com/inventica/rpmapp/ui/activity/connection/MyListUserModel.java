package com.inventica.rpmapp.ui.activity.connection;

import android.os.Parcel;
import android.os.Parcelable;

public class MyListUserModel implements Parcelable {
    private Long inviteUserId;
    private String inviteUserName;
    private String inviteUserContactNumber;
    private String inviteUserEmailId;
    private String inviteUserProfilePhoto;
    private Long groupId;
    private String groupName;
    private Boolean isConnected;

    public MyListUserModel(Long inviteUserId, String inviteUserName, String inviteUserContactNumber, String inviteUserEmailId, String inviteUserProfilePhoto, Long groupId, String groupName, Boolean isConnected) {
        this.inviteUserId = inviteUserId;
        this.inviteUserName = inviteUserName;
        this.inviteUserContactNumber = inviteUserContactNumber;
        this.inviteUserEmailId = inviteUserEmailId;
        this.inviteUserProfilePhoto = inviteUserProfilePhoto;
        this.groupId = groupId;
        this.groupName = groupName;
        this.isConnected = isConnected;
    }

    protected MyListUserModel(Parcel in) {
        if (in.readByte() == 0) {
            inviteUserId = null;
        } else {
            inviteUserId = in.readLong();
        }
        inviteUserName = in.readString();
        inviteUserContactNumber = in.readString();
        inviteUserEmailId = in.readString();
        inviteUserProfilePhoto = in.readString();
        if (in.readByte() == 0) {
            groupId = null;
        } else {
            groupId = in.readLong();
        }
        groupName = in.readString();
        byte tmpIsConnected = in.readByte();
        isConnected = tmpIsConnected == 0 ? null : tmpIsConnected == 1;
    }

    public static final Creator<MyListUserModel> CREATOR = new Creator<MyListUserModel>() {
        @Override
        public MyListUserModel createFromParcel(Parcel in) {
            return new MyListUserModel(in);
        }

        @Override
        public MyListUserModel[] newArray(int size) {
            return new MyListUserModel[size];
        }
    };

    public Long getInviteUserId() {
        return inviteUserId;
    }

    public void setInviteUserId(Long inviteUserId) {
        this.inviteUserId = inviteUserId;
    }

    public String getInviteUserName() {
        return inviteUserName;
    }

    public void setInviteUserName(String inviteUserName) {
        this.inviteUserName = inviteUserName;
    }

    public String getInviteUserContactNumber() {
        return inviteUserContactNumber;
    }

    public void setInviteUserContactNumber(String inviteUserContactNumber) {
        this.inviteUserContactNumber = inviteUserContactNumber;
    }

    public String getInviteUserEmailId() {
        return inviteUserEmailId;
    }

    public void setInviteUserEmailId(String inviteUserEmailId) {
        this.inviteUserEmailId = inviteUserEmailId;
    }

    public String getInviteUserProfilePhoto() {
        return inviteUserProfilePhoto;
    }

    public void setInviteUserProfilePhoto(String inviteUserProfilePhoto) {
        this.inviteUserProfilePhoto = inviteUserProfilePhoto;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Boolean getConnected() {
        return isConnected;
    }

    public void setConnected(Boolean connected) {
        isConnected = connected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (inviteUserId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(inviteUserId);
        }
        dest.writeString(inviteUserName);
        dest.writeString(inviteUserContactNumber);
        dest.writeString(inviteUserEmailId);
        dest.writeString(inviteUserProfilePhoto);
        if (groupId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(groupId);
        }
        dest.writeString(groupName);
        dest.writeByte((byte) (isConnected == null ? 0 : isConnected ? 1 : 2));
    }
}
