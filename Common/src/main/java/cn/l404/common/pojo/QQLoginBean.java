package cn.l404.common.pojo;

/**
 * @auther Fanxing
 * 这是一个简介
 */
public class QQLoginBean {

    private String avatar;
    private String gender;
    private String location;
    private String nickname;
    private RawUserInfo rawUserInfo;
    private String source;
    private Token token;
    private String username;
    private String uuid;
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() {
        return location;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getNickname() {
        return nickname;
    }

    public void setRawUserInfo(RawUserInfo rawUserInfo) {
        this.rawUserInfo = rawUserInfo;
    }
    public RawUserInfo getRawUserInfo() {
        return rawUserInfo;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public String getSource() {
        return source;
    }

    public void setToken(Token token) {
        this.token = token;
    }
    public Token getToken() {
        return token;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getUuid() {
        return uuid;
    }

}