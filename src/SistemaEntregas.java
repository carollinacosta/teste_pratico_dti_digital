import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SistemaEntregas {

    private List<Drone> drones;
    private List<Pacote> pacotes;
    private List<Entrega> missoesAtivas;

    public SistemaEntregas() {
        this.drones = new ArrayList<>();
        this.pacotes = new ArrayList<>();
        this.missoesAtivas = new ArrayList<>();
    }

    public void adicionarDrone(Drone drone) {
        this.drones.add(drone);
    }

    public void receberPacote(Pacote pacote) {
        this.pacotes.add(pacote);
        processarFilaPacotes();
    }

    public void processarFilaPacotes() {
        if (pacotes.isEmpty() || drones.isEmpty()) return;
        this.pacotes.sort(
                Comparator.comparing(Pacote::getPesoPacote).reversed()
                        .thenComparing(pacote -> pacote.getPrioridadeEntrega().getValor()).reversed()
        );

        // Passo 2: Encontrando um drone disponível
        Drone droneLivre = null;
        for (Drone drone : this.drones) {
            // Verifica o estado correto (IDLE)
            if (drone.getEstadoDrone() == EstadoDrone.IDLE) {
                droneLivre = drone;
                break;
            }
        }

        // Checagem de Disponibilidade
        if (droneLivre != null) {
            List<Pacote> pacotesDaEntrega = new ArrayList<>();
            double cargaAtual = 0.0;
            double capacidadeMax = droneLivre.getCapacidadeKg();

            // Loop verificar Alocação
            for (Pacote pacoteCandidato : this.pacotes){
                if (cargaAtual + pacoteCandidato.getPesoPacote() <= capacidadeMax){
                    pacotesDaEntrega.add(pacoteCandidato);
                    cargaAtual += pacoteCandidato.getPesoPacote();
                }
            }


            if (!pacotesDaEntrega.isEmpty()){
                Entrega novaEntrega = new Entrega(droneLivre, pacotesDaEntrega);

                if (novaEntrega.getDistanciaPercurso() <= droneLivre.getDistanciaKm()) {
                    iniciarSimulacao(novaEntrega);
                    this.pacotes.removeAll(pacotesDaEntrega);

                } else {
                    System.out.println("ALOCAÇÃO REJEITADA: Missão excede o alcance de " + droneLivre.getDistanciaKm() + "km.");
                }

            } else {
                System.out.println("Nenhum pacote coube no drone disponível.");
            }

        } else {
            System.out.println("Drones ocupados! Fila de pacotes com " + pacotes.size() + " itens.");
        }
    }



    public void iniciarSimulacao(Entrega missao) {

        missoesAtivas.add(missao);
        Drone drone = missao.getDroneDesignado();

        System.out.println("\n[SIMULADOR] INICIANDO: " + drone.toString() + ". Rota: " + missao.getDistanciaPercurso() + "km.");


        new Thread(() -> {
            try {
                // 1. CARREGANDO
                drone.setEstadoDrone(EstadoDrone.CARREGANDO);
                simularPausa(2); // Simula 2 segundos de carregamento

                // 2. EM VOO
                drone.setEstadoDrone(EstadoDrone.EM_VOO);
                System.out.println("[SIMULADOR] " + drone.toString() + " DECOLAGEM! Carga: " + missao.getCargaTotal() + "kg.");

                // Cálculo e Simulação do Tempo de Voo
                double tempoEmHoras = missao.getDistanciaPercurso() / (drone.getVelocidadeKmPorMinuto() * 60);
                long tempoEmMilisegundos = (long) (tempoEmHoras * 60 * 60 * 1000);

                simularPausa(Math.max(1, tempoEmMilisegundos / 10000)); // Simulação de 1s a 10s para visualização

                // 3. RETORNANDO e IDLE
                drone.setEstadoDrone(EstadoDrone.RETORNANDO);
                System.out.println("[SIMULADOR] " + drone.toString() + " Entregas Concluídas. Tempo de voo: " + (tempoEmHoras * 60) + " minutos.");

                simularPausa(1); // Simula 1 segundo de retorno

                drone.setEstadoDrone(EstadoDrone.IDLE);
                drone.setCoordenadaXDrone(0);
                drone.setCoordenadaYDrone(0);
                missoesAtivas.remove(missao);

                // Após a missão, tenta processar a fila novamente!
                processarFilaPacotes();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Simulação do drone " + drone.toString() + " interrompida.");
            }
        }).start();
    }

    private void simularPausa(long segundos) throws InterruptedException {
        // Usa TimeUnit para melhor legibilidade
        TimeUnit.SECONDS.sleep(segundos);
    }

    public List<Drone> getDrones() {
        return drones;
    }

    public List<Pacote> getPacotes() {
        return pacotes;
    }
}