package Randomico;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * Created by thial on 17/08/2017.
 */
public class RandomPrincipal {
    static RandomAccessFile criado2 = null;
    static int codigo, quantidade;
    static double valorUnitario, subTotal;
    static final int TAMANHO_REGISTRO = 25;
    static boolean apagado;

    public static void main(String[] args) throws IOException {

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("+---------------------------------------------+");
            System.out.println("|                    Menu                     |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| C |              Criar / Selecionar Arquivo |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| N |                        Inserir Registro |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| A |                        Alterar Registro |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| D |                         Apagar Registro |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| P |         Inserir Registro Em Uma Posição |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| L |                        Listar Registros |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| X |              Listar Registros Deletados |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| S |                      Pesquisar Registro |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| R |                     Recuperar Registros |");
            System.out.println("+---+-----------------------------------------+");
            System.out.println("| E |                                    Exit |");
            System.out.println("+---+-----------------------------------------+");

            char op = (char) System.in.read();
            op = Character.toUpperCase(op);

            scan.nextLine();

            switch (op) {
                case 'C':
                    criado2 = criarArquivo();
                    break;

                case 'N':
                    inserirNatural();
                    break;
                case 'R':
                    recuperarRegistro();
                    break;
                case 'L':
                    mostraDados();
                    break;
                case 'P':
                    inserirPosicao();
                    break;
                case 'S':
                    pesquisar();
                    break;
                case 'D':
                    apagarRegistro();
                    break;
                case 'A':
                    alterarRegistro();
                    break;
                case 'X':
                    listarApagados();
                    break;
                case 'E':
                    System.exit(0);
                    break;
                default:
                    System.out.println("Erro");
                    break;
            }
        }
    }

    public static RandomAccessFile criarArquivo()throws IOException{
        RandomAccessFile raf = new RandomAccessFile("estoque.dat", "rw");
        System.out.println("Arquivo Criado...");
        return raf;
    }

    public static void inserirNatural()throws IOException {

        Scanner scan = new Scanner(System.in);

        byte continuar = 1;

        while(continuar != 0)
        {
            System.out.println("Digite o código: ");
            codigo = scan.nextInt();
            System.out.println("Digite a quantidade: ");
            quantidade = scan.nextInt();
            System.out.println("Digite o valor unitário: ");
            valorUnitario = scan.nextDouble();
            subTotal = quantidade * valorUnitario;
            criado2.writeBoolean(false);
            criado2.writeInt(codigo);
            criado2.writeInt(quantidade);
            criado2.writeDouble(valorUnitario);
            criado2.writeDouble(subTotal);
            System.out.println("Se quiser sair digite 0.");
            continuar = scan.nextByte();
        }
    }

    public static void inserirPosicao()throws IOException{

    }

    public static void alterarRegistro()throws IOException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o codigo do arquivo que quer modificar...: ");
        int cdgRecebido = scan.nextInt();
        criado2.seek(0);
        for(int i = 0; i < (criado2.length()/TAMANHO_REGISTRO); i++)
        {
            apagado = criado2.readBoolean();
            codigo = criado2.readInt();
            quantidade = criado2.readInt();
            valorUnitario = criado2.readDouble();
            subTotal = criado2.readDouble();

            if(codigo == cdgRecebido) {
                criado2.seek(i * TAMANHO_REGISTRO);
                System.out.println("Digite o código: ");
                codigo = scan.nextInt();
                System.out.println("Digite a quantidade: ");
                quantidade = scan.nextInt();
                System.out.println("Digite o valor unitário: ");
                valorUnitario = scan.nextDouble();
                subTotal = quantidade * valorUnitario;
                criado2.writeBoolean(false);
                criado2.writeInt(codigo);
                criado2.writeInt(quantidade);
                criado2.writeDouble(valorUnitario);
                criado2.writeDouble(subTotal);
                System.out.println("Arquivo modificado...");
            }
        }
    }

    public static void mostraDados() throws IOException {

        System.out.println("Imprimindo o arquivo do estoque");
        criado2.seek(0);
        for(int i = 0; i < (criado2.length()/TAMANHO_REGISTRO); i++)
        {
            apagado = criado2.readBoolean();
            codigo = criado2.readInt();
            quantidade = criado2.readInt();
            valorUnitario = criado2.readDouble();
            subTotal = criado2.readDouble();

            if(!apagado)
            System.out.println("Item: "+ codigo +" Estoque: "+quantidade+" R$(un):" + valorUnitario+" R$(sub-total):"+subTotal);
        }
    }

    public static void listarApagados()throws IOException{
        System.out.println("Imprimindo registros apagados...");
        criado2.seek(0);
        for(int i = 0; i < (criado2.length()/TAMANHO_REGISTRO); i++)
        {
            apagado = criado2.readBoolean();
            codigo = criado2.readInt();
            quantidade = criado2.readInt();
            valorUnitario = criado2.readDouble();
            subTotal = criado2.readDouble();

            if(apagado)
                System.out.println("Item: "+ codigo +" Estoque: "+quantidade+" R$(un):" + valorUnitario+" R$(sub-total):"+subTotal);
        }
    }

    public static void apagarRegistro() throws IOException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o codigo do arquivo que quer apagar: ");
        int cdgRecebido = scan.nextInt();
        System.out.println("Arquivo apagado...");
        criado2.seek(0);
        for(int i = 0; i < (criado2.length()/TAMANHO_REGISTRO); i++)
        {
            apagado = criado2.readBoolean();
            codigo = criado2.readInt();
            quantidade = criado2.readInt();
            valorUnitario = criado2.readDouble();
            subTotal = criado2.readDouble();

            if(codigo == cdgRecebido) {
                criado2.seek(i * TAMANHO_REGISTRO);
                criado2.writeBoolean(true);
                criado2.seek((i + 1)* TAMANHO_REGISTRO);
            }
        }
    }

    public static void recuperarRegistro()throws IOException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o codigo do arquivo que quer recuperar: ");
        int cdgRecebido = scan.nextInt();
        System.out.println("Arquivo recuperado...");
        criado2.seek(0);
        for(int i = 0; i < (criado2.length()/TAMANHO_REGISTRO); i++)
        {
            apagado = criado2.readBoolean();
            codigo = criado2.readInt();
            quantidade = criado2.readInt();
            valorUnitario = criado2.readDouble();
            subTotal = criado2.readDouble();

            if(codigo == cdgRecebido) {
                criado2.seek(i * TAMANHO_REGISTRO);
                criado2.writeBoolean(false);
                criado2.seek((i + 1)* TAMANHO_REGISTRO);
            }
        }
    }

    public static void pesquisar()throws IOException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o codigo do arquivo que quer pesquisar: ");
        int cdgRecebido = scan.nextInt();
        System.out.println("Imprimindo o arquivo pesquisado");
        criado2.seek(0);
        for(int i = 0; i < (criado2.length()/TAMANHO_REGISTRO); i++)
        {
            apagado = criado2.readBoolean();
            codigo = criado2.readInt();
            quantidade = criado2.readInt();
            valorUnitario = criado2.readDouble();
            subTotal = criado2.readDouble();

            if(codigo == cdgRecebido)
                System.out.println("Item: "+ codigo +" Estoque: "+quantidade+" R$(un):" + valorUnitario+" R$(sub-total):"+subTotal);
        }
    }
}
