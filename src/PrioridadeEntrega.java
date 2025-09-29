public enum PrioridadeEntrega {
    ALTA(3),
    MEDIA(2),
    BAIXA(1);

    private final int valor;

    private PrioridadeEntrega (int valor){
        this.valor = valor;
    }

    public int getValor(){
        return valor;
    }
}
