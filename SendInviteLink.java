import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Base64;

public class SendInviteLink {
    SendInviteLink(){}

    public String sendInviteLink(String token, String studentEmail, String personalEmail) {
        String inviteLink = "null";

        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("https://api.us.onelogin.com/api/1/invites/send_invite_link");

        request.setHeader("Authorization", "bearer:"+token);
        request.addHeader("Content-Type", "application/json");

        request.setEntity(new StringEntity("{ \"email\": \"" + studentEmail + "\", \"personal_email\": \"" + personalEmail + "\"}",  "UTF-8"));


        try {
            CloseableHttpResponse reponse = client.execute(request);

            String content = EntityUtils.toString(reponse.getEntity());

            JSONObject json = new JSONObject(content);

            System.out.println(json.toString(4));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return inviteLink;
    }
}
