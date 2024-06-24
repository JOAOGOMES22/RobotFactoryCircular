package Robot;
public class Robo {
    private ParteRobo bracoEsquerdo;
    private ParteRobo bracoDireito;
    private ParteRobo pernaEsquerda;
    private ParteRobo pernaDireita;
    private ParteRobo carcaca;
    private ParteRobo cabeca;
    private int grupoMontagem;
    private int funcionarioMontagem;

    public Robo(ParteRobo bracoEsquerdo, ParteRobo bracoDireito, ParteRobo pernaEsquerda, ParteRobo pernaDireita, ParteRobo carcaca, ParteRobo cabeca, int grupoMontagem, int funcionarioMontagem) {
        this.bracoEsquerdo = bracoEsquerdo;
        this.bracoDireito = bracoDireito;
        this.pernaEsquerda = pernaEsquerda;
        this.pernaDireita = pernaDireita;
        this.carcaca = carcaca;
        this.cabeca = cabeca;
        this.grupoMontagem = grupoMontagem;
        this.funcionarioMontagem = funcionarioMontagem;
    }

    @Override
    public String toString() {
        // Retorna uma representação textual do robô montado, incluindo detalhes sobre cada parte e quem as produziu
        return "Robo montado por funcionario " + funcionarioMontagem + " do grupo " + grupoMontagem + ":\n" +
                " - " + bracoEsquerdo + "\n" +
                " - " + bracoDireito + "\n" +
                " - " + pernaEsquerda + "\n" +
                " - " + pernaDireita + "\n" +
                " - " + carcaca + "\n" +
                " - " + cabeca;
    }
}
