package Projeto_AdaTwitter;



public class Tweet {
    String mensagem;
    String data_hora;
    String user;

    public static String tweet (Tweet object) {
        return String.format("[%s] @%s: %s", object.data_hora, object.user, object.mensagem);
    }

}
