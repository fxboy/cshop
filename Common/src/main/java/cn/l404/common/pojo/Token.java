/**
 * Copyright 2021 bejson.com
 */
package cn.l404.common.pojo;

/**
 * Auto-generated: 2021-04-26 18:9:20
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Token {

    private String accessToken;
    private long expireIn;
    private String openId;
    private String refreshToken;
    private int refreshTokenExpireIn;

    public Token() {

    }

    public Token(String accessToken, long expireIn, String openId, String refreshToken, int refreshTokenExpireIn) {
        this.accessToken = accessToken;
        this.expireIn = expireIn;
        this.openId = openId;
        this.refreshToken = refreshToken;
        this.refreshTokenExpireIn = refreshTokenExpireIn;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getAccessToken() {
        return accessToken;
    }

    public void setExpireIn(long expireIn) {
        this.expireIn = expireIn;
    }
    public long getExpireIn() {
        return expireIn;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
    public String getOpenId() {
        return openId;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshTokenExpireIn(int refreshTokenExpireIn) {
        this.refreshTokenExpireIn = refreshTokenExpireIn;
    }
    public int getRefreshTokenExpireIn() {
        return refreshTokenExpireIn;
    }

}