/**
 * Created by Arfera on 29/03/2017.
 */
public class Main {

    public static void main(String[] args) {
        MailClient mailClient = new MailClient();
        mailClient.generateMessage("Marina. Tema Bragas", "Hola Marcos, soy Marina.\n\n Ayer " +
                "me dejé mi ropa interior en tu cajonera. Espero que no lo viera tu novia." +
                " \n\nUn beso guapi.", null);
    }

}
