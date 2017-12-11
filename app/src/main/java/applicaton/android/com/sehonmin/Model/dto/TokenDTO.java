package applicaton.android.com.sehonmin.Model.dto;

/**
 * Created by ken13 on 2017-12-12.
 */

public class TokenDTO {

    private String token;
    private String formName;

    public TokenDTO(String token, String formName){
        this.token=token;
        this.formName=formName;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }
}
