# ⚔️ Projeto de Engenharia de Software: RPG 2D (Java)

Este é um projeto de um jogo de RPG baseado em console, desenvolvido em Java. O jogo simula a exploração de um herói em uma masmorra (labirinto), onde ele deve encontrar a saída utilizando um algoritmo de busca (Backtracking), enquanto enfrenta monstros do folclore e interage com itens e ajudantes misteriosos.

## 👥 Autores (Grupo Eng\_Software 1)

  * **Alan Oliveira Sampaio Joffily**
  * **João Victor Balvedi**
  * **Nathan Zordenones Santos**
  * **Renan Belem Biavati**

-----

## 🎮 Sobre o Jogo

O jogo opera como um **"Dungeon Crawler" semi-automático**. O movimento do herói é controlado por um algoritmo recursivo que tenta encontrar a saída (`=`). No entanto, as **interações** (combates, escolhas de itens e negociações com NPCs) exigem a decisão do jogador.

### Principais Funcionalidades

1.  **Exploração Automática (Backtracking):** O herói navega pelo labirinto (`mapa.txt`) recursivamente, marcando o caminho percorrido.
2.  **Sistema de Combate em Turnos:** Batalhas contra monstros com atributos de Ataque, Defesa e Vida.
3.  **Gerenciamento de Inventário:** O herói possui slots para **Mão Direita** e **Mão Esquerda**, podendo equipar Espadas e Escudos.
4.  **Sistema de Ajudantes (Risco vs. Recompensa):**
      * **Anão:** Aumenta drasticamente o ataque do herói, mas fortalece o próximo monstro.
      * **Duende:** Reduz a vida do monstro pela metade, mas cobra um preço na vida do herói.
5.  **Folclore Brasileiro:** Inimigos temáticos como **Curupira** e **Bicho-Papão**.

-----

## 🗺️ Legenda do Mapa (`mapa.txt`)

O arquivo `mapa.txt` representa o tabuleiro do jogo. Cada caractere tem um significado:

| Símbolo | Significado | Descrição |
| :---: | :--- | :--- |
| **8** | **Herói** | Posição inicial do jogador. |
| **=** | **Saída** | Objetivo final do jogo. |
| **\#** | **Parede** | Obstáculo intransponível. |
| **.** | **Caminho** | Espaço livre (ou caminho já visitado). |
| **e** | **Espada** | Item que aumenta o Ataque. |
| **d** | **Escudo** | Item que aumenta a Defesa. |
| **c** | **Poção/Cura** | Restaura pontos de vida. |
| **?** | **Bicho Papão** | Monstro com ataque equilibrado. |
| **\*** | **Curupira** | Monstro com defesa alta. |
| **&** | **Anão** | Ajudante que oferece bônus de ataque. |
| **^** | **Duende** | Ajudante que enfraquece monstros. |

-----

## 🏗️ Estrutura do Projeto (Arquitetura)

O projeto segue os princípios da Orientação a Objetos (POO), utilizando **Herança**, **Polimorfismo**, **Interfaces** e **Classes Abstratas**.

### 1\. Núcleo (`Core`)

  * **`Main.java`**: Ponto de entrada. Carrega o mapa e inicia a busca pela saída.
  * **`Mapa.java`**: Cérebro do jogo.
      * Lê o arquivo `mapa.txt`.
      * Implementa o algoritmo recursivo `encontraSaida`.
      * Gerencia o loop de batalha e interações.
  * **`Entidade.java`**: (Conceitual) Base para atributos comuns como Ataque, Defesa e Vida.

### 2\. Personagens (`Characters`)

  * **`Heroi.java`**: O protagonista. Gerencia inventário (duas mãos), lista de ajudantes e status atuais.
  * **`Monstro.java`** *(Abstrata)*: Define o comportamento básico dos inimigos.
      * **`BichoPapao.java`**: Inimigo focado em dano direto.
      * **`Curupira.java`**: Inimigo com alta defesa.
  * **`Ajudante.java`** *(Interface)*: Contrato para NPCs.
      * **`Anao.java`**: Bônus de Ataque (+85%) / Penalidade: Monstro mais forte (+35% Vida).
      * **`Duende.java`**: Bônus no Inimigo (Vida / 2) / Penalidade: Dano no Herói (10% Vida atual).

### 3\. Itens e Equipamentos (`Items`)

  * **`Item.java`** *(Abstrata)*: Classe base para equipamentos.
      * **`Espada.java`**: Aumenta o Ataque.
      * **`Escudo.java`**: Aumenta a Defesa.
  * **`Pocao.java`**: Classe base para consumíveis.
      * **`Cura.java`**: Recupera vida fixa (+30).
  * **`EnumBonusItem.java`**: Define os valores constantes dos bônus.

-----

## 🚀 Como Executar

### Pré-requisitos

  * Java Development Kit (JDK) instalado.

### Passos

1.  Compile todos os arquivos `.java`:
    ```bash
    javac *.java
    ```
2.  Certifique-se de que o arquivo `mapa.txt` esteja dentro de uma pasta chamada `src` (conforme definido no código `Main.java`) ou ajuste o caminho no `Main.java` se estiver na raiz.
      * *Estrutura recomendada:*
        ```
        /projeto
          /src
            mapa.txt
          Main.java
          Heroi.java
          ... (outros arquivos java)
        ```
3.  Execute o jogo:
    ```bash
    java Main
    ```

-----

## 🧠 Lógica de Decisão (Ajudantes)

O jogo apresenta dilemas estratégicos ao encontrar ajudantes:

> **Encontro com Anão:**
>
>   * *Oferta:* "Aumento seu ataque em 85%."
>   * *Preço:* "O próximo monstro terá 35% a mais de vida."
>   * *Estratégia:* Bom se você tiver pouca vida e precisar acabar com a luta rápido, mas perigoso contra monstros "tanques" (como o Curupira).

> **Encontro com Duende:**
>
>   * *Oferta:* "Reduzo a vida do próximo monstro pela metade."
>   * *Preço:* "Você perde 10% da sua vida atual."
>   * *Estratégia:* Excelente contra chefes ou monstros fortes, desde que você tenha vida suficiente para sacrificar.

-----

**Status do Projeto:** Finalizado para fins acadêmicos da disciplina de Engenharia de Software da Graduação em Ciência da Computação.
