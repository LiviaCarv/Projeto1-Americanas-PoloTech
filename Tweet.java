package Projeto_AdaTwitter;



public class Tweet {
    String mensagem;
    String data_hora;
    String user;

    public String tweet () {
        return String.format("[%s] @%s: %s", this.data_hora, this.user, this.mensagem);
    }

}
