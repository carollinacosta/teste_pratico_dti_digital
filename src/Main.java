public class Main {
    public static void main(String[] args) {
        System.out.println("--- DTI Digital: Simulador de Entregas por Drone ---");
        SistemaEntregas sistema = new SistemaEntregas();

        // 1. ADICIONAR FROTA DE DRONES (ID, Capacidade, Alcance, X, Y, Velocidade km/min)
        // Drone 1: Maior capacidade, alcance e mais rápido.
        sistema.adicionarDrone(new Drone(1, 15.0, 50, 0, 0, 2.0));
        // Drone 2: Menor capacidade, usado para desempate.
        sistema.adicionarDrone(new Drone(2, 8.0, 30, 0, 0, 1.5));

        System.out.println("Frota inicializada com " + sistema.getDrones().size() + " drones.");

        // 2. RECEBER PEDIDOS (TESTANDO A OTIMIZAÇÃO: Peso -> Prioridade)

        // Pacote A: ALTA Prioridade, Peso MÉDIO (Deve ir primeiro como desempate)
        sistema.receberPacote(new Pacote(4.0, PrioridadeEntrega.ALTA, 5, 5));

        // Pacote B: BAIXA Prioridade, Peso PESADO (Deve ir primeiro por Peso)
        sistema.receberPacote(new Pacote(7.0, PrioridadeEntrega.BAIXA, 10, 1));

        // Pacote C: MÉDIA Prioridade, Peso Leve (Deve ser agrupado com o A se couber)
        sistema.receberPacote(new Pacote(2.0, PrioridadeEntrega.MEDIA, 2, 8));

        // Pacote D: Pacote que só cabe no Drone 1 (Teste de limite de capacidade)
        sistema.receberPacote(new Pacote(5.0, PrioridadeEntrega.ALTA, 15, 15));

        System.out.println("Pedidos recebidos. O Sistema está processando a alocação...");

        sistema.processarFilaPacotes();


    }
}