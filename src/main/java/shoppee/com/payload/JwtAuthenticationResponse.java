package shoppee.com.payload;

public class JwtAuthenticationResponse {
	private String accessToken;
    private String tokenType = "Bearer";
    private int id;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JwtAuthenticationResponse(String accessToken, int id) {
        this.accessToken = accessToken;
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
