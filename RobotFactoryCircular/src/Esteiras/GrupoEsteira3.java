package Esteiras;
import Log.Logger;
import Robot.ParteRobo;
import Robot.Robo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class GrupoEsteira3 implements Runnable {
    private static final int NUM_FUNCIONARIOS = 5; // Número de funcionários no grupo
    private static final AtomicInteger contadorFuncionario = new AtomicInteger(0); // Contador de funcionários
    private int grupo; // Identificador do grupo
    private ArrayBlockingQueue<ParteRobo> esteira1; // Buffer circular para armazenar as partes da esteira 1
    private ArrayBlockingQueue<ParteRobo> esteira2; // Buffer circular para armazenar as partes da esteira 2
    private Lock[] ferramentas; // Ferramentas para montagem dos robôs

    public GrupoEsteira3(int grupo, ArrayBlockingQueue<ParteRobo> esteira1, ArrayBlockingQueue<ParteRobo> esteira2, Lock[] ferramentas) {
        this.grupo = grupo;
        this.esteira1 = esteira1;
        this.esteira2 = esteira2;
        this.ferramentas = ferramentas;
    }

    @Override
    public void run() {
        while (true) {
            // Obter o número do funcionário de forma circular
            int numeroFuncionario = contadorFuncionario.getAndUpdate(n -> (n + 1) % NUM_FUNCIONARIOS);
            try {
                // Bloquear ferramentas adjacentes para montagem
                ferramentas[numeroFuncionario].lock();
                ferramentas[(numeroFuncionario + 1) % NUM_FUNCIONARIOS].lock();

                // Coletar partes do robô das esteiras 1 e 2
                ParteRobo bracoEsquerdo = esteira1.take();
                ParteRobo bracoDireito = esteira1.take();
                ParteRobo pernaEsquerda = esteira1.take();
                ParteRobo pernaDireita = esteira1.take();
                ParteRobo carcaca = esteira2.take();
                ParteRobo cabeca = esteira2.take();

                // Montar o robô e logar o evento
                Robo robo = new Robo(bracoEsquerdo, bracoDireito, pernaEsquerda, pernaDireita, carcaca, cabeca, grupo, numeroFuncionario);
                Logger.log(robo.toString());

                // Simular tempo de montagem
                try {
                    Thread.sleep(2000); // 2 segundos
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                // Desbloquear ferramentas
                ferramentas[(numeroFuncionario + 1) % NUM_FUNCIONARIOS].unlock();
                ferramentas[numeroFuncionario].unlock();
            }
        }
    }
}
