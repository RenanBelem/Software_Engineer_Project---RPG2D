# ⚔️ Projeto de Engenharia de Software: RPG 2D (Java)

Este é um jogo de RPG baseado em console desenvolvido em Java. O projeto simula a exploração de uma masmorra utilizando um algoritmo de busca (Backtracking) para movimentação, combinado com uma arquitetura robusta orientada a objetos para gerenciar a criação de entidades (Monstros, Itens e Ajudantes).

## 👥 Autores (Grupo Eng\_Software 1)

  * **Alan Oliveira Sampaio Joffily**
  * **João Victor Balvedi**
  * **Nathan Zordenones Santos**
  * **Renan Belem Biavati**

-----

## 🏗️ Arquitetura e Design Patterns

A principal atualização desta versão é a implementação do padrão **Factory Method**. O objetivo foi desacoplar a lógica do mapa da criação de objetos, facilitando a manutenção e a adição de novos elementos ao jogo.

### 1\. Centralização (`ConstrutorDeEntidades`)

A classe `ConstrutorDeEntidades` atua como um registro central (Registry). Ela mapeia os caracteres do mapa (`char`) para suas respectivas fábricas.

  * Isso elimina as longas estruturas de decisão (`if/else` ou `switch`) de dentro da classe `Mapa`.
  * Para adicionar um novo monstro, basta criar sua classe, sua fábrica e registrar no construtor.

### 2\. Fábricas (Factories)

O projeto define interfaces para a criação de famílias de objetos:

  * **`MonstroFactory`**: Implementada por `BichoPapaoFactory` e `CurupiraFactory`.
  * **`AjudanteFactory`**: Implementada por `AnaoFactory` e `DuendeFactory`.
  * **`ItemFactory`**: Implementada por `EspadaFactory` e `EscudoFactory`.
  * **`PocaoFactory`**: Implementada por `CuraFactory`.

### 3\. Diagrama de Classes

O projeto segue o diagrama estrutural `diagrama_de_requisitos.drawio.png`, onde `Heroi` e `Monstro` compartilham atributos base (vida, ataque, defesa), e as interações são mediadas pelas interfaces das fábricas.

-----

## 🎮 Funcionalidades do Jogo

O jogo opera como um **"Dungeon Crawler" semi-automático**:

1.  **Movimentação (Backtracking):** O herói utiliza um algoritmo recursivo (`encontraSaida` em `Mapa.java`) para navegar pelo labirinto, evitando paredes (`#`) e caminhos já visitados, até encontrar a saída (`=`).
2.  **Combate em Turnos:** Ao encontrar um inimigo, o jogo entra em loop de batalha. O jogador decide se continua ou desiste após a vitória.
3.  **Sistema de Equipamentos:**
      * **Mão Direita/Esquerda:** O herói pode equipar itens em mãos específicas. Equipar um novo item remove o bônus do anterior.
      * **Bônus Fixos:** Definidos no `EnumBonusItem` (Espada +10, Escudo +8, Cura +30).
4.  **Ajudantes com "Trade-offs":** NPCs oferecem ajuda com um custo estratégico.

-----

## 🗺️ Legenda do Mapa (`mapa.txt`)

O arquivo `mapa.txt` é a representação visual do nível. O `ConstrutorDeEntidades` interpreta estes símbolos:

| Símbolo | Entidade | Tipo | Descrição |
| :---: | :--- | :--- | :--- |
| **8** | **Herói** | Jogador | Posição inicial. |
| **=** | **Saída** | Objetivo | Ponto final do labirinto. |
| **\#** | **Parede** | Cenário | Bloqueio intransponível. |
| **?** | **Bicho Papão** | Monstro | Inimigo com ataque balanceado. |
| **\*** | **Curupira** | Monstro | Inimigo com defesa alta. |
| **&** | **Anão** | Ajudante | Aumenta Ataque do Herói / Aumenta Vida do Monstro. |
| **^** | **Duende** | Ajudante | Reduz Vida do Monstro pela metade / Dano no Herói. |
| **e** | **Espada** | Item | Aumenta Ataque (+10). |
| **d** | **Escudo** | Item | Aumenta Defesa (+8). |
| **c** | **Poção** | Consumível | Recupera Vida (+30). |

-----

## 🚀 Como Executar

### Pré-requisitos

  * Java Development Kit (JDK) instalado.

### Configuração Importante

Antes de executar, verifique a classe `Main.java`. O caminho do arquivo de mapa está absoluto e **precisa ser alterado** para o caminho da sua máquina:

```java
// Em Main.java, altere esta linha:
Mapa map = new Mapa("C:\\Seu\\Caminho\\Para\\src\\mapa.txt", 17, 21);
```

### Compilação e Execução

No terminal, navegue até a pasta dos arquivos (`src`) e execute:

```bash
javac *.java
java Main
```

-----

## 🧠 Detalhes das Interações

### Monstros

  * **Bicho Papão:** Focado em causar dano direto.
  * **Curupira:** Possui defesa elevada, exigindo mais turnos ou maior ataque para ser derrotado.

### Ajudantes (Estratégia)

A interação com ajudantes foi movida para métodos encapsulados na interface `Ajudante`:

> **Anão:** "Aumento seu ataque em 85%, mas o próximo monstro terá 35% a mais de vida."
>
> **Duende:** "Corto a vida do monstro pela metade, mas cobro 10% da sua vida agora."

-----

**Status:** Projeto refatorado com Factory Method para modularidade e extensibilidade.
