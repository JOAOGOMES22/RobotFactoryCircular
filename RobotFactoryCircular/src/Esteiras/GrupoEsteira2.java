package Esteiras;
import Log.Logger;
import Robot.ParteRobo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class GrupoEsteira2 implements Runnable {
    private static final int NUM_FUNCIONARIOS = 9; // Número de funcionários no grupo
    private static final AtomicInteger contadorFuncionario = new AtomicInteger(0); // Contador de funcionários
    private int grupo; // Identificador do grupo
    private ArrayBlockingQueue<ParteRobo> esteira; // Buffer circular para armazenar as partes produzidas

    public GrupoEsteira2(int grupo, ArrayBlockingQueue<ParteRobo> esteira) {
        this.grupo = grupo;
        this.esteira = esteira;
    }

    @Override
    public void run() {
        while (true) {
            // Obter o número do funcionário de forma circular
            int numeroFuncionario = contadorFuncionario.getAndUpdate(n -> (n + 1) % NUM_FUNCIONARIOS);
            String parte;
            switch (grupo) {
                case 1:
                    parte = "carcaca";
                    break;
                case 2:
                    parte = "cabeca";
                    break;
                default:
                    parte = "desconhecida";
            }

            // Criar a parte do robô e adicionar na esteira
            ParteRobo parteRobo = new ParteRobo(parte, esteira.size(), grupo, numeroFuncionario);
            try {
                esteira.put(parteRobo); // Adiciona a parte produzida no buffer da esteira
                Logger.log("Funcionario " + numeroFuncionario + " do grupo " + grupo + " da Esteira 2 produziu " + parteRobo);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Simular tempo de produção
            try {
                Thread.sleep(1000); // 1 segundo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
