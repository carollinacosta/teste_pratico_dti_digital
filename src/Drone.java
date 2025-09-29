public class Drone {
    double capacidadeKg;
    int distanciaKm;
    int coordenadaXDrone;
    int coordenadaYDrone;
    double velocidadeKmPorMinuto;
    EstadoDrone estadoDrone;

    public Drone(double capacidadeKg, int distanciaKm, int coordenadaXDrone, int coordenadaYDrone,
                 double velocidadeKmPorMinuto,EstadoDrone estadoDrone){
        this.capacidadeKg = capacidadeKg;
        this.coordenadaXDrone = coordenadaXDrone;
        this.coordenadaYDrone = coordenadaYDrone;
        this.velocidadeKmPorMinuto = velocidadeKmPorMinuto;
        this.distanciaKm = distanciaKm;
        this.estadoDrone = estadoDrone;
    }

    public EstadoDrone getEstadoDrone() {
        return estadoDrone;
    }

    public void setEstadoDrone(EstadoDrone estadoDrone) {
        this.estadoDrone = estadoDrone;
    }

    public double getCapacidadeKg() {
        return capacidadeKg;
    }

    public int getCoordenadaXDrone() {
        return coordenadaXDrone;
    }

    public void setCoordenadaXDrone(int coordenadaXDrone) {
        this.coordenadaXDrone = coordenadaXDrone;
    }

    public int getCoordenadaYDrone() {
        return coordenadaYDrone;
    }

    public void setCoordenadaYDrone(int coordenadaYDrone) {
        this.coordenadaYDrone = coordenadaYDrone;
    }

    public int getDistanciaKm() {
        return distanciaKm;
    }

    public double getVelocidadeKmPorMinuto() {
        return velocidadeKmPorMinuto;
    }

    @Override
    public String toString() {
        return "Drone #" + id + " (" + estadoDrone + ")";
    }
}
