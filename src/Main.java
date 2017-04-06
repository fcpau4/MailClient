/**
 * Created by Arfera on 29/03/2017.
 */
public class Main {

    public static void main(String[] args) {
        MailClient mailClient = new MailClient();
        mailClient.setMessage("MAIL CLIENT", "Someone there? I'm here trying to send messages \n" +
                "from Hotmail Client", null);
    }

}
