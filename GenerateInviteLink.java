import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class GenerateInviteLink {

    public String getInviteLink(String token, String studentEmail) {
        String inviteLink = "null";

        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("https://api.us.onelogin.com/api/1/invites/get_invite_link");

        request.setHeader("Authorization", "bearer:"+token);
        request.addHeader("Content-Type", "application/json");

        // set body of request
        request.setEntity(new StringEntity("{ \"email\": \"" + studentEmail + "\" }", "UTF-8"));

        try {
            CloseableHttpResponse reponse = client.execute(request);

            String content = EntityUtils.toString(reponse.getEntity());

            JSONObject json = new JSONObject(content);

            inviteLink = json.getString("data");

        } catch (Exception e) {
            e.printStackTrace();
        }

        // reformat invite link without brackets
        inviteLink = inviteLink.substring(1,inviteLink.length()-1);

        return inviteLink;
    }
}
