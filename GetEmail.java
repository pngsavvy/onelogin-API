import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetEmail {

    GetEmail() {
    }

    // pass in student email that is given by powershell script in order to return personal email and full name (which will be used for email)
    public String [] getEmailAndName(String token, String studentEmail) {

        String fieldToSearch = "company"; // this will be the company field which is where the personal email will be stored.
        String [] returnData = {"null","null"};

        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();

            HttpGet request = new HttpGet("https://api.us.onelogin.com/api/1/users?email="+studentEmail);

            request.setHeader("Authorization", "bearer:" + token);
            request.addHeader("Content-Type", "application/json");

            CloseableHttpResponse response = client.execute(request);
            String content = EntityUtils.toString(response.getEntity());
            JSONObject json = new JSONObject(content);

            JSONArray dataArray = json.getJSONArray("data");

            // get email
            returnData[0] = dataArray.getJSONObject(0).getString(fieldToSearch);
            String userName = dataArray.getJSONObject(0).getString("username");
            System.out.println("user name is: " + userName);


            // get full name
            returnData[1] = dataArray.getJSONObject(0).getString("firstname");
            returnData[1] = returnData[1] + " " + dataArray.getJSONObject(0).getString("lastname");



        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnData;
    }
}
