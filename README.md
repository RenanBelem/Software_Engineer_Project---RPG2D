# ⚔️ Projeto de Engenharia de Software: RPG 2D (Java)

Este é um jogo de RPG baseado em console desenvolvido em Java. O projeto simula a exploração de uma masmorra utilizando **Backtracking** para movimentação automática e **Design Patterns** (Factory Method e Iterator) para garantir uma arquitetura de software modular, desacoplada e extensível.

## 👥 Autores (Grupo Eng\_Software 1)

  * **Alan Oliveira Sampaio Joffily**
  * **João Victor Balvedi**
  * **Nathan Zordenones Santos**
  * **Renan Belem Biavati**

-----

## 🏗️ Arquitetura e Design Patterns

O projeto foi refatorado para incluir dois padrões de projeto principais do GoF (Gang of Four), visando resolver problemas específicos de criação e acesso a dados.

### 1\. Padrão Iterator (Novidade)

Implementado para permitir que o jogo percorra os itens da mochila do herói sem expor a estrutura interna da lista (`ArrayList`).

  * **Interface `Iterator<T>`:** Define os métodos padronizados `hasNext()` e `next()`.
  * **Concrete Iterator (`MochilaIterator`):** Implementa a lógica de navegação específica para a coleção de itens.
  * **Aggregate (`MochilaDeItens`):** Atua como o contêiner dos itens. Possui o método `criarIterator()` que retorna uma instância de `MochilaIterator`, permitindo que classes externas (como `Mapa`) listem o inventário sem tocar na lista privada.
  * **Cliente (`Mapa`):** No loop principal do jogo, o mapa solicita o iterador para exibir o conteúdo da mochila a cada passo do herói.

### 2\. Padrão Factory Method

Utilizado para desacoplar a lógica do mapa da criação de objetos concretos.

  * **Registro Central (`ConstrutorDeEntidades`):** Mapeia caracteres do mapa (ex: `'?'`, `'e'`, `'^'`) para suas respectivas fábricas, eliminando condicionais complexas.
  * **Famílias de Fábricas:**
      * `MonstroFactory` (cria `BichoPapao`, `Curupira`).
      * `ItemFactory` (cria `Espada`, `Escudo`).
      * `AjudanteFactory` (cria `Anao`, `Duende`).

-----

## 🎮 Mecânicas do Jogo

O jogo opera como um **"Dungeon Crawler" semi-automático** com gerenciamento estratégico de recursos.

### Exploração e Mapa

  * **Movimentação:** O herói utiliza um algoritmo recursivo (`encontraSaida`) para navegar pelo labirinto (`mapa.txt`), evitando paredes (`#`) e rastreando caminhos visitados.
  * **Visualização:** A cada passo, o console é limpo e o mapa atualizado é desenhado, seguido pelo status do herói e **listagem da mochila via Iterator**.

### Inventário e Mochila

Ao encontrar um item (`Espada` ou `Escudo`), o jogador tem três escolhas estratégicas:

1.  **Equipar na Direita ('d'):** Substitui o item atual e aplica o bônus.
2.  **Equipar na Esquerda ('e'):** Substitui o item atual e aplica o bônus.
3.  **Guardar na Mochila ('g'):** Armazena o item na `MochilaDeItens` para uso futuro ou apenas para coleção. Estes itens são listados no HUD do jogo.

### Combate e NPCs

  * **Monstros:** O combate é em turnos. `BichoPapao` foca em ataque, enquanto `Curupira` possui defesa elevada.
  * **Ajudantes (Risco vs. Recompensa):**
      * **Anão:** Aumenta muito seu ataque, mas fortalece a vida do próximo monstro.
      * **Duende:** Reduz a vida do monstro pela metade, mas cobra um custo de vida do herói imediatamente.

-----

## 🗺️ Legenda do Mapa

O arquivo `mapa.txt` é interpretado pelo `ConstrutorDeEntidades`:

| Símbolo | Entidade | Descrição |
| :---: | :--- | :--- |
| **8** | **Herói** | Posição atual do jogador. |
| **=** | **Saída** | Objetivo final. |
| **\#** | **Parede** | Obstáculo. |
| **?** | **Bicho Papão** | Inimigo (Ataque Médio). |
| **\*** | **Curupira** | Inimigo (Defesa Alta). |
| **&** | **Anão** | Ajudante (+Ataque / +Vida Monstro). |
| **^** | **Duende** | Ajudante (-Vida Monstro / -Vida Herói). |
| **e** | **Espada** | Item (+10 Ataque). |
| **d** | **Escudo** | Item (+8 Defesa). |
| **c** | **Poção** | Consumível (+30 Vida). |

-----

## 🚀 Como Executar

### Pré-requisitos

  * Java Development Kit (JDK) instalado.

### Configuração de Caminho

⚠️ **Atenção:** O arquivo `Main.java` utiliza um caminho para carregar o mapa. Verifique a linha abaixo antes de rodar:

```java
// Em Main.java
String caminhoMapa = Paths.get("Mapa.txt").toString(); 
// Certifique-se de que o arquivo Mapa.txt está na raiz do projeto ao executar
```

### Compilação e Execução

No terminal, na pasta onde estão os arquivos `.java`:

```bash
javac *.java
java Main
```

-----

## 🧠 Diagrama de Classes

A estrutura do projeto segue o diagrama `diagrama_de_requisitos.drawio.png`, onde:

  * `MochilaDeItens` compõe `Heroi`.
  * `MochilaIterator` implementa `Iterator`.
  * As classes Factory criam as instâncias de `Entidade`.

-----

**Status:** Projeto refatorado com Factory Method e Iterator Pattern para modularidade, desacoplamento e extensibilidade.
