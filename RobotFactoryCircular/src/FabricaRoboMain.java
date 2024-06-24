import Esteiras.GrupoEsteira1;
import Esteiras.GrupoEsteira2;
import Esteiras.GrupoEsteira3;
import Robot.ParteRobo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FabricaRoboMain {
    public static void main(String[] args) {
        // Definindo a capacidade das esteiras 1 e 2
        int capacidadeEsteira1 = 100;
        int capacidadeEsteira2 = 100;

        // Buffers circulares para armazenar as partes produzidas nas esteiras 1 e 2
        ArrayBlockingQueue<ParteRobo> esteira1 = new ArrayBlockingQueue<>(capacidadeEsteira1);
        ArrayBlockingQueue<ParteRobo> esteira2 = new ArrayBlockingQueue<>(capacidadeEsteira2);

        // Inicializando as ferramentas para evitar deadlocks na esteira 3
        Lock[] ferramentasEsteira3 = new ReentrantLock[5];
        for (int i = 0; i < 5; i++) {
            ferramentasEsteira3[i] = new ReentrantLock();
        }

        // Iniciando threads para os 4 grupos da primeira esteira
        for (int i = 1; i <= 4; i++) {
            Thread esteira1Thread = new Thread(new GrupoEsteira1(i, esteira1));
            esteira1Thread.start();
        }

        // Iniciando threads para os 2 grupos da segunda esteira
        for (int i = 1; i <= 2; i++) {
            Thread esteira2Thread = new Thread(new GrupoEsteira2(i, esteira2));
            esteira2Thread.start();
        }

        // Iniciando threads para os 3 grupos da terceira esteira
        for (int i = 1; i <= 3; i++) {
            Thread esteira3Thread = new Thread(new GrupoEsteira3(i, esteira1, esteira2, ferramentasEsteira3));
            esteira3Thread.start();
        }
    }
}
