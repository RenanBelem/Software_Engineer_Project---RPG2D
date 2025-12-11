# ⚔️ Projeto de Engenharia de Software: RPG 2D (Java)

Este é um jogo de RPG baseado em console desenvolvido em Java. O projeto combina algoritmos de busca (**Backtracking**) com uma arquitetura de software avançada, implementando três padrões de projeto do GoF (**Factory Method**, **Iterator** e **Proxy**) para garantir modularidade, segurança e rastreabilidade.

## 👥 Autores (Grupo Eng\_Software 1)

  * **Alan Oliveira Sampaio Joffily**
  * **João Victor Balvedi**
  * **Nathan Zordenones Santos**
  * **Renan Belem Biavati**

-----

## 🏗️ Arquitetura e Padrões de Projeto

O projeto foi desenhado para demonstrar a aplicação prática de padrões de Engenharia de Software:

### 1\. Padrão Proxy (Gerenciamento de Logs)

Implementado para adicionar funcionalidades de **log (registro de eventos)** sem modificar a lógica de negócio das entidades principais.

  * **Funcionamento:** Classes como `ProxyMonstro`, `ProxyItem` e `ProxyAjudante` envolvem os objetos reais. Elas interceptam as chamadas de métodos para registrar ações no arquivo `log_jogo.txt` antes ou depois de delegar a execução para o objeto real.
  * **Fábricas com Proxy:** As fábricas também são proxificadas (ex: `ProxyMonstroFactory`). Quando o mapa solicita um monstro, a fábrica retorna um `ProxyMonstro` em vez da instância crua.

### 2\. Padrão Iterator (Gestão de Inventário)

Permite percorrer os itens da mochila do herói sem expor a estrutura interna da lista (`ArrayList`).

  * **Componentes:**
      * `Iterator<T>`: Interface comum de navegação.
      * `MochilaIterator`: Implementação concreta que controla o cursor.
      * `MochilaDeItens`: O agregado que cria o iterador para o cliente (`Mapa`).

### 3\. Padrão Factory Method (Criação de Objetos)

Desacopla a lógica do jogo da instanciação direta de classes.

  * **Registro Central:** A classe `ConstrutorDeEntidades` atua como um registro que mapeia caracteres do mapa (ex: `'?'`) para suas respectivas fábricas (agora encapsuladas em proxies).
  * **Extensibilidade:** Para adicionar um novo inimigo, basta criar a classe do monstro, sua fábrica e registrá-la, sem alterar o loop principal do jogo.

-----

## 🎮 Mecânicas do Jogo

O jogo é um **Dungeon Crawler semi-automático**:

1.  **Exploração:** O herói move-se automaticamente usando um algoritmo recursivo (Backtracking) para encontrar a saída (`=`).
2.  **Combate e Interação:** Ao encontrar uma entidade, o jogo pausa e aguarda decisão do jogador ou resolve o combate em turnos.
3.  **Logs:** Todas as ações críticas (encontros, itens pegos, início de batalhas) são salvas automaticamente em `log_jogo.txt` com data e hora.

### Entidades e Símbolos (`mapa.txt`)

| Símbolo | Entidade | Tipo | Descrição / Efeito |
| :---: | :--- | :--- | :--- |
| **8** | **Herói** | Jogador | Posição atual. |
| **=** | **Saída** | Objetivo | Ponto final do labirinto. |
| **\#** | **Parede** | Cenário | Bloqueio intransponível. |
| **?** | **Bicho Papão** | Monstro | Ataque médio, focado em dano. |
| **\*** | **Curupira** | Monstro | Defesa alta, difícil de acertar. |
| **&** | **Anão** | Ajudante | **Bônus:** +85% Ataque do Herói.<br>**Custo:** +35% Vida do próximo monstro. |
| **^** | **Duende** | Ajudante | **Bônus:** Reduz vida do monstro pela metade.<br>**Custo:** Remove 10% da vida atual do Herói. |
| **e** | **Espada** | Item | Aumenta o Ataque (+10). |
| **d** | **Escudo** | Item | Aumenta a Defesa (+8). |
| **c** | **Poção** | Consumível | Recupera Vida (+30). |

-----

## 📂 Estrutura de Arquivos e UML

O diagrama abaixo ilustra a relação entre as classes base (`Entidade`, `Item`), as classes concretas e as interfaces de Fábrica:

*Nota: Na implementação final, as classes `Proxy...` envolvem as classes concretas (ex: `BichoPapao`, `Espada`) representadas no diagrama.*

-----

## 🚀 Como Executar

### Pré-requisitos

  * Java JDK 8 ou superior instalado.

### Passo a Passo

1.  **Configuração do Mapa:**
    Verifique o arquivo `Main.java`. Ele busca o arquivo de mapa. Certifique-se de que `mapa.txt` está no diretório correto ou ajuste o caminho:

    ```java
    String caminhoMapa = Paths.get("Mapa.txt").toString();
    ```

2.  **Compilação:**
    Abra o terminal na pasta contendo os arquivos `.java` e execute:

    ```bash
    javac *.java
    ```

3.  **Execução:**

    ```bash
    java Main
    ```

### Verificando os Logs

Após a execução, um arquivo `log_jogo.txt` será gerado na raiz do projeto. Ele conterá o histórico da partida no seguinte formato:

```text
18/11/2025 19:01:23 Herói encontrou um(a) Espada
18/11/2025 19:01:25 Herói vai batalhar contra um BichoPapao
18/11/2025 19:01:28 Herói aceitou a ajuda do Duende.
```

-----

**Status:** Projeto finalizado e refatorado com Factory Method, Iterator Pattern e Proxy implementados para modularidade, desacoplamento e extensibilidade.
