import java.util.List;

public class Entrega {
    Drone droneDesignado;
    List<Pacote> pacotes;
    double cargaTotal;
    double distanciaPercurso;


    public Entrega(Drone droneDesignado, List<Pacote> pacotes){
        this.droneDesignado = droneDesignado;
        this.pacotes = pacotes;
        this.cargaTotal = calcularCargaTotal();
        this.distanciaPercurso = calcularDistanciaPercurso();
    }

    public double calcularCargaTotal(){
        double total = 0.0;
        for (Pacote p : pacotes){
            total += p.getPesoPacote();
        }
        return total;
    }

    public double calcularDistanciaPercurso(){
        double totalDistancia = 0.0;
        // Começa na base, ponto 0
        double pontoAtualX = 0.0;
        double pontoAtualY = 0.0;

        // Loop para somar a distância de cada trecho (Base -> P1, P1 -> P2, etc.)
        for (Pacote proximoPacote : this.pacotes){
            double destinoX = proximoPacote.getCoordenadaXDestino();
            double destinoY = proximoPacote.getCoordenadaYDestino();

            // Cálculo da distância do trecho: pontoAtual -> destino
            double distanciaTrecho = Math.sqrt(Math.pow(destinoX - pontoAtualX,2)+
                    Math.pow(destinoY - pontoAtualY,2));

            totalDistancia += distanciaTrecho;

            // O próximo trecho começará no destino atual:
            pontoAtualX = destinoX;
            pontoAtualY = destinoY;
        }
        // Cálculo do Retorno para a Base (0, 0)
        double distanciaRetorno = Math.sqrt(Math.pow(pontoAtualX,2)+
                Math.pow(pontoAtualY,2));

        totalDistancia += distanciaRetorno;

        return  totalDistancia;
    }


    public double getCargaTotal() {
        return cargaTotal;
    }

    public double getDistanciaPercurso() {
        return distanciaPercurso;
    }

    public List<Pacote> getPacotes() {
        return pacotes;
    }

    public Drone getDroneDesignado() {
        return droneDesignado;
    }
}
