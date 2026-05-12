package co.com.bancolombia.api.helpers;
import jakarta.servlet.http.HttpServletRequest;
public class Headers {

    private String token;

    private static final Headers instance = new Headers();

    private Headers() {}

    public static Headers getInstance() {
        return instance;
    }

    /*
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    */

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }

    /*
    public String getToken() {

        String authorization =
                request.getHeader("Authorization");

        if (authorization == null
                || !authorization.startsWith("Bearer ")) {

            return null;
        }

        return authorization.substring(7);
    }
    */

}