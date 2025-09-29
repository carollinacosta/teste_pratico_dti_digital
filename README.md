# Teste Prático DTI Digital: Simulador de Entrega por Drones Otimizado

Este repositório contém a solução proposta para o Desafio Técnico de Estágio da DTI Digital, focado na simulação de operações de logística com drones em ambiente urbano 2D.

A solução é construída em Java, utilizando Programação Orientada a Objetos (POO) e implementando um algoritmo Greedy (Guloso) para otimizar a alocação de pacotes e minimizar o número de viagens.

---

## Objetivo Principal

O objetivo central do sistema é alocar o maior número de pacotes por drone e por viagem, respeitando as restrições físicas (capacidade de peso e alcance máximo), utilizando o **menor número de viagens possível**.

### Critérios de Otimização (Hierarquia Greedy)

Para a alocação, o sistema utiliza a seguinte regra de priorização ao agrupar pacotes (Knapsack Problem simplificado):

1.  **Peso (Descendente):** Prioriza os pacotes mais pesados primeiro para tentar maximizar o uso da capacidade do drone.
2.  **Prioridade (Descendente):** Utilizado como critério de desempate para garantir que pedidos `ALTA` prioridade sejam atendidos antes de `MÉDIA` ou `BAIXA`.
3.  **Distância (Otimização da Rota):** Calculada para cada missão multi-pacote para garantir que o percurso total (incluindo o retorno à base) não exceda o alcance do drone.

---

## Arquitetura e Modelagem POO

O projeto é estruturado em entidades claras, que se relacionam para gerenciar a simulação:

| Classe | Função Central | Lógica Implementada |
| :--- | :--- | :--- |
| **`SistemaEntregas`** | O Motor e o Gerenciador Central. | Contém o algoritmo `processarFilaPacotes()` que executa a ordenação, a busca por drones `IDLE` e o loop de alocação **Greedy**. |
| **`Drone`** | A unidade de transporte. | Armazena a capacidade (X kg), o alcance (Y km), a velocidade e o `EstadoDrone` (o modelo de simulação). |
| **`Pacote`** | O pedido de entrega. | Armazena o peso, a coordenada de destino (X, Y) e o `PrioridadeEntrega` (implementado como Enum com valor numérico para facilitar a ordenação). |
| **`Entrega`** | A missão multi-pacote. | Objeto criado após a otimização. Seu construtor calcula automaticamente a **Carga Total** e a **Distância do Percurso** (usando a fórmula da Distância Euclidiana). |
| **`EstadoDrone`** | Enum que gerencia o ciclo de vida do drone (ex: `IDLE`, `CARREGANDO`, `EM_VOO`). | |

---

##  Simulação e Diferenciais

O projeto atende aos diferenciais técnicos solicitados:

### 1. Modelo de Simulação Orientado a Eventos

A simulação de tempo é gerenciada em Threads separadas para cada missão, permitindo que vários drones operem simultaneamente sem travar o sistema principal:
* A missão muda o estado do drone (`CARREGANDO` $\rightarrow$ `EM_VOO` $\rightarrow$ `RETORNANDO`).
* O tempo de voo é simulado usando a fórmula **Tempo = Distância / Velocidade**, implementado com `Thread.sleep()` (usando `TimeUnit.SECONDS` para clareza).

### 2. Validação Lógica e Testes

* O requisito de **Testes Automatizados** foi abordado através da implementação de **Testes Manuais Embutidos** na `Classe Main`, que validam a precisão do cálculo de **Distância Euclidiana** e a funcionalidade do algoritmo de **Alocação Greedy**.

---

## Como Executar o Projeto

O projeto utiliza Java padrão e não requer ferramentas de build como Maven ou Gradle.

1.  **Compilação:** Certifique-se de que todas as classes (`Drone.java`, `Pacote.java`, `Entrega.java`, `SistemaEntregas.java`, `Main.java` e Enums) estejam no mesmo diretório ou configuradas no mesmo classpath.
2.  **Execução:** Execute a classe principal (`Main.java`).

```bash
# Exemplo de compilação e execução via linha de comando (CLI)
javac *.java
java Main
