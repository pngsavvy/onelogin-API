
public class Start {
    public static void main(String[] args) {
        // create instances of all classes
        GenerateAccessToken accessToken = new GenerateAccessToken();
        GetEmail getEmail = new GetEmail();
        SendInviteLink sendInviteLink = new SendInviteLink();

        String studentEmail = "onelogin_email";
        // wont need this
         String personalEmail = "personal_email";

        try {
            // Generate Access Token
            String token = accessToken.getToken();

            // pass in student email that is given by powershell script in order to return the students personal email
            String [] userData = getEmail.getEmailAndName(token, personalEmail);
            String userEmail = userData[0];
            String userName = userData[1];

            System.out.println("user email: " + userEmail);
            System.out.println("user name: " + userName);

            //send email using api
            sendInviteLink.sendInviteLink(token,studentEmail,userEmail);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


