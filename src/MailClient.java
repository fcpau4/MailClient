/**
 * Created by Dionis on 05/02/2016.
 */
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Abans de fer servir l'aplicació heu de permetre l'autenticació poc segura des de GMAIL.
 * Per això heu d'iniciar el navegador amb la sessió, i després posar aquest enllaç.
 * https://www.google.com/settings/security/lesssecureapps
 */

public class MailClient {

    private Properties mailServerProperties;
    private Session mailSession;
    private MimeMessage mailMessage;
    private PasswordAuthentication PA;

    private static String sender = "marinagar46@hotmail.com";
    private static String passwdSender = "ronaldinha10";
    private String mailReceptor ="fcpau4@gmail.com";

    public MailClient(){
        init();
    }


    /**
     * Inicialitzo el gestor de correu electrònic.
     */
    public void init(){

        //Estableixo el repositori propietats del client.
        mailServerProperties = System.getProperties();

        //Afeigeixo el port del client Hotmail que és el 587.
        mailServerProperties.put("mail.smtp.port", "587");

        //The SMTP of Hotmail is 587 using TLS/SSL.

        mailServerProperties.setProperty("mail.host", "smtp.live.com");

        //Afegeixo l'opció d'autenticació.
        mailServerProperties.put("mail.smtp.auth", "true");

        //Opcions relatives a la seguretat.
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        System.out.println("Mail Server Properties have been setup successfully..");

        mailServerProperties.setProperty("mail.pop3s.port",  "995");
        //Per últim inicialitzo la sessió que la necessitaré per generar el missatge.

        mailSession = Session.getDefaultInstance(mailServerProperties);
    }





    /**
     * Mètode que permet enviar missatges per mitjà del client de Hotmail.
     * @param subject
     * @param body
     * @param file
     */
    public void setMessage(String subject, String body, String file){

        mailMessage = new MimeMessage(mailSession);

        try {
            mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailReceptor));
            mailMessage.setFrom(new InternetAddress(sender));

            //Estableixo el head del correu.
            mailMessage.setSubject(subject);


            /*Si hi ha path a fitxer adjunt faig que inclogui tant el cos del missatge com
             el fitxer. En cas contrari que només inclogui el text.
             */
            if(file!=null){

                //Creem el cos del missatge.
                BodyPart messageBodyPart = new MimeBodyPart();

                //Afegeixo, ara sí, el cos del missatge
                messageBodyPart.setText( body );

                //Creo un Multipart per guardar el text i el fitxer.
                Multipart multipart = new MimeMultipart();

                // Afegeixo el cos del missatge al Multipart i afegeixo el Fitxer
                multipart.addBodyPart(messageBodyPart);
                messageBodyPart = new MimeBodyPart();

                DataSource source = new FileDataSource(file);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(source.getName());
                multipart.addBodyPart(messageBodyPart);

                //Per últim afegeixo tant el missatge com el fitxer del Multipart al Contingut del Message.

                mailMessage.setContent(multipart);

            }else{

                mailMessage.setContent(body, "text/html");
            }

            System.out.println("Mail Session has been created successfully..");


            Transport transport = mailSession.getTransport("smtp");
            transport.connect("smtp.live.com", sender, passwdSender);
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients() );
            transport.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }



}
