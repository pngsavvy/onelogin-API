import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Base64;

public class GenerateAccessToken {

    GenerateAccessToken() {}

    public String getToken() throws Exception {
            String accessToken = "null";

            CloseableHttpClient client = HttpClientBuilder.create().build();

            HttpPost request = new HttpPost("https://api.us.onelogin.com/auth/oauth2/v2/token");

            String credentials = String.format("%s:%s", "client_credential", "client_credential");
            byte[] encodedAuth = Base64.getEncoder().encode(credentials.getBytes());
            String authHeader = "Basic " + new String(encodedAuth);

            request.setHeader("Authorization", authHeader);
            request.addHeader("Content-Type", "application/json");

            request.setEntity(new StringEntity("{ \"grant_type\": \"client_credentials\" }", "UTF-8"));

            try {
                CloseableHttpResponse reponse = client.execute(request);

                String content = EntityUtils.toString(reponse.getEntity());

                JSONObject json = new JSONObject(content);

                accessToken = json.getString("access_token");

            } catch (Exception e) {
                e.printStackTrace();
            }

            return accessToken;
        }

}

