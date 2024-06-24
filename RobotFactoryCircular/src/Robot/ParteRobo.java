package Robot;
public class ParteRobo {
    private String tipo; // Tipo da parte do robô (braço, perna, etc.)
    private int posicaoEsteira; // Posição da parte na esteira
    private int grupo; // Grupo que produziu a parte
    private int funcionario; // Funcionário que produziu a parte

    public ParteRobo(String tipo, int posicaoEsteira, int grupo, int funcionario) {
        this.tipo = tipo;
        this.posicaoEsteira = posicaoEsteira;
        this.grupo = grupo;
        this.funcionario = funcionario;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPosicaoEsteira() {
        return posicaoEsteira;
    }

    public int getGrupo() {
        return grupo;
    }

    public int getFuncionario() {
        return funcionario;
    }

    @Override
    public String toString() {
        // Retorna uma representação textual da parte do robô
        return "Parte: " + tipo + ", Posicao na esteira: " + posicaoEsteira + ", Grupo: " + grupo + ", Funcionario: " + funcionario;
    }
}
