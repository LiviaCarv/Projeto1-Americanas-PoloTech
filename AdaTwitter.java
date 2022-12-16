package Projeto_AdaTwitter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AdaTwitter {
    public static void main(String[] args) {
        Usuario[] arrayUsuarios = new Usuario[20];
        String[] timeLine = new String[20];
        int escolha = menuLoginOuCadastro();
        int posicao_usuario_logado;
        if (escolha == 2) {
            cadastroUsuario(arrayUsuarios);
        }
        posicao_usuario_logado = loginUsuario(arrayUsuarios);
        do {
            escolha = menuTimeLine();
            if (escolha == 1) {
                timeLine(timeLine);
            } else if (escolha == 2) {
                tweetar(timeLine, arrayUsuarios[posicao_usuario_logado]);
            }
        } while (escolha != 3);

        System.out.println("Até mais!");
    }

    public static int menuLoginOuCadastro() {
        String mensagem = "=".repeat(30) +
                "\nBEM VINDO(A) AO ADA TWITTER!\n"+
                "=".repeat(30);
        String menu = "\nEscolha sua opção:" +
                      "\n 1 - Login " +
                      "\n 2 - Cadastro";
        System.out.println(mensagem + menu);
        Scanner scanner = new Scanner(System.in);
        int escolha = scanner.nextInt();
        while (escolha != 1 && escolha != 2) {
            System.out.println("Opcao Invalida. Tente Novamente!");
            escolha = scanner.nextInt();
        }
        return escolha;
    }
    public static void cadastroUsuario(Usuario[] arrayUsuarios) {
        System.out.println("========= CADASTRO =========");
        Usuario user = new Usuario();
        String usuario = verificadorDeusuario();
        String senha = verificadorDeSenha();
        int contador = 0;
        while (contador < arrayUsuarios.length) {
            if (arrayUsuarios[contador] == null) {
                user.usuario = usuario;
                user.senha = senha;
                user.posicao_array = contador;
                arrayUsuarios[contador] = user;
                System.out.println("Usuario cadastrado com sucesso!");
                break;
            }
            contador++;
        }
    }


    public static int loginUsuario(Usuario[] arrayUsuarios) {

        System.out.println("========= LOGIN =========");
        System.out.println("Para começar entre com seu usuário e senha: ");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        int contador = 0;
        while (contador < arrayUsuarios.length) {
            if (arrayUsuarios[contador] != null && arrayUsuarios[contador].usuario.equals(usuario) && arrayUsuarios[contador].senha.equals(senha)) {
                System.out.println("Login feito com sucesso!");
                return contador;
            }
            contador++;
        }
        System.out.println("Usuario nao encontrado! Escolha: " +
                            "\n1 - Tentar novamente " +
                            "\n2 - Realizar Cadastro");
        int escolha = scanner.nextInt();
        int posicao = 0;
        if (escolha == 1) {
            posicao = loginUsuario(arrayUsuarios);
        } else if (escolha == 2) {
            cadastroUsuario(arrayUsuarios);
            posicao = loginUsuario(arrayUsuarios);
        }
        return posicao;
    }

    public static int menuTimeLine() {
        String menu = "\n 1 - TimeLine " +
                      "\n 2 - Tweetar" +
                      "\n 3 - Sair";
        System.out.println(menu);
        Scanner scanner = new Scanner(System.in);
        int escolha = scanner.nextInt();
        while (escolha != 1 && escolha != 2 && escolha != 3) {
            System.out.println("Opcao Invalida. Tente Novamente!");
            escolha = scanner.nextInt();
        }
        return escolha;
    }

    public static void tweetar (String[] timeline, Usuario user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("O que você está pensando? ");
        String mensagem = scanner.nextLine();

        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String data_hora = agora.format(formatter);
        Tweet tweet = new Tweet();
        int contador = 0;
        while (contador < timeline.length) {
            if (timeline[contador] == null) {
                tweet.mensagem = mensagem;
                tweet.user = user.usuario;
                tweet.data_hora = data_hora;
                timeline[contador] = Tweet.tweet(tweet);
                System.out.println("TWEET PUBLICADO COM SUCESSO!");
                break;
            } else {
                contador++;
            }
        }
    }

    public static void timeLine (String[] timeline) {

        if (timeline[0] == null) {
            System.out.println("Não há nenhum tweet para ver no momento.");
        } else {
            System.out.println("========= TIMELINE =========");
            for (String tweet: timeline) {
                if (tweet != null) {
                    System.out.println(tweet);
                }
            }
        }
    }

    public static String verificadorDeusuario () {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Escolha seu usuário: ");
        String usuario = scanner.nextLine();
        while (usuario.isBlank()) {
            System.out.print("Usuario Inválido. Digite novamente: ");
            usuario = scanner.nextLine();
        }
        return usuario;
    }
    public static String verificadorDeSenha() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Escolha sua senha: ");
        String senha = scanner.nextLine();
        while (senha.isBlank()) {
            System.out.print("Senha Inválida. Digite novamente: ");
            senha = scanner.nextLine();
        }
        System.out.print("Confirme sua senha: ");
        String confirma_senha = scanner.nextLine();
        while (!senha.equals(confirma_senha)) {
            System.out.print("Esta nao foi a senha escolhida. Confirme novamente: ");
            confirma_senha = scanner.nextLine();
        }
        return senha;
    }
}
