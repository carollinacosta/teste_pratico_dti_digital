public class Pacote {
    double pesoPacote;
    PrioridadeEntrega prioridadeEntrega;
    int coordenadaXDestino;
    int coordenadaYDestino;

    public Pacote(double pesoPacote, PrioridadeEntrega prioridadeEntrega,
                  int coordenadaXDestino, int coordenadaYDestino){
        this.pesoPacote = pesoPacote;
        this.prioridadeEntrega = prioridadeEntrega;
        this.coordenadaXDestino =  coordenadaXDestino;
        this.coordenadaYDestino = coordenadaYDestino;
    }

    public double getPesoPacote() {
        return pesoPacote;
    }

    public int getCoordenadaXDestino() {
        return coordenadaXDestino;
    }

    public int getCoordenadaYDestino() {
        return coordenadaYDestino;
    }

    public PrioridadeEntrega getPrioridadeEntrega() {
        return prioridadeEntrega;
    }

    @Override
    public String toString() {
        return "Pacote{" + "X=" + coordenadaXDestino + ", Y=" + coordenadaYDestino + ", Peso=" + pesoPacote + ", Prioridade=" + prioridadeEntrega + '}';
    }
}
