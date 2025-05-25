# Lista de Compras - Aplicativo Android

## Descrição

**Lista de Compras** é um aplicativo Android simples, desenvolvido em Kotlin, que permite aos usuários criar e gerenciar listas de produtos. Os usuários podem adicionar itens com nome, quantidade, valor e uma imagem opcional. O aplicativo exibe a lista de produtos e calcula o valor total da compra.

Este projeto foi desenvolvido como um exercício prático, demonstrando conceitos fundamentais do desenvolvimento Android com Kotlin e o sistema de Views baseado em XML.

## Funcionalidades Principais

* **Adicionar Produtos:** Permite cadastrar novos produtos fornecendo:
    * Nome do produto
    * Quantidade
    * Valor unitário
    * Imagem (opcional, selecionada da galeria do dispositivo)
* **Visualizar Lista:** Exibe todos os produtos adicionados em uma lista rolável, mostrando a foto, nome, quantidade e valor total de cada item (preço x quantidade).
* **Cálculo do Total:** Calcula e exibe o valor total de todos os produtos na lista.
* **Remover Produtos:** Permite remover itens da lista através de um clique longo no item desejado, com uma caixa de diálogo de confirmação.
* **Validação de Entrada:** Verifica se os campos obrigatórios (nome, quantidade, valor) foram preenchidos antes de adicionar um produto.
* **Formatação de Moeda:** Exibe os valores monetários formatados em Reais (BRL).
* **Seleção de Imagem:** Integração com a galeria do dispositivo para selecionar imagens para os produtos.

## Tecnologias e Conceitos Demonstrados

* **Linguagem:** Kotlin
* **Arquitetura de UI:** Sistema de Views baseado em XML
* **Componentes Android:**
    * `Activity` (`MainActivity`, `CadastroActivity`)
    * `AppCompatActivity` para compatibilidade e temas
    * `ListView` para exibir a lista de produtos
    * `ArrayAdapter` customizado (`ProdutoAdapter`) para popular a `ListView` com layouts de item personalizados
    * `ImageView`, `TextView`, `EditText`, `Button`
    * `AlertDialog` para diálogos de confirmação
* **Layouts:** Definição de interfaces de usuário com XML (`LinearLayout`, `RelativeLayout`).
* **Navegação:** Uso de `Intent` para navegar entre Activities.
* **Tratamento de Resultados de Activity:** Uso de `startActivityForResult` (obsoleto, mas presente no material original) e `onActivityResult` para seleção de imagens.
    * _Nota: Em projetos mais novos, a API `ActivityResultLauncher` é preferida._
* **Gerenciamento de Dados Simples:** Uso de uma lista mutável global (`produtosGlobal` em `AppGlobals.kt`) para armazenar os dados dos produtos (para fins didáticos).
* **Formatação:** `NumberFormat` e `Locale` para formatação de moeda.
* **Recursos Android:** Uso de drawables, layouts, strings e temas (`themes.xml` com `Theme.MaterialComponents`).
* **Manifesto:** Configuração de Activities e permissões (`AndroidManifest.xml`), como `READ_EXTERNAL_STORAGE` / `READ_MEDIA_IMAGES`.
* **Gradle:** Gerenciamento de dependências e construção do projeto.

## Estrutura do Projeto (Principais Arquivos)

* **`MainActivity.kt`**: Activity principal que exibe a lista de produtos, o total da compra, e permite a remoção de itens e navegação para a tela de cadastro.
* **`CadastroActivity.kt`**: Activity responsável por receber os dados do novo produto (nome, quantidade, valor, foto) e adicioná-lo à lista.
* **`Produto.kt`**: Classe de dados (`data class`) que representa um item da lista de compras.
* **`AppGlobals.kt`**: Arquivo que contém a lista global `produtosGlobal` onde os produtos são armazenados temporariamente.
* **`ProdutoAdapter.kt`**: Adaptador customizado que liga os dados da lista de `Produto` à `ListView`, formatando cada item da lista.
* **`res/layout/activity_main.xml`**: Layout XML da tela principal.
* **`res/layout/activity_cadastro.xml`**: Layout XML da tela de cadastro.
* **`res/layout/list_view_item.xml`**: Layout XML para cada item individual na `ListView`.
* **`res/values/themes.xml`**: Define o tema visual do aplicativo.
* **`AndroidManifest.xml`**: Define os componentes do aplicativo, permissões e outras configurações essenciais.

## Como Configurar e Executar

1.  **Clone ou baixe o projeto.**
2.  **Abra no Android Studio:**
    * Selecione "Open an existing Android Studio project" (Abrir um projeto existente do Android Studio).
    * Navegue até a pasta do projeto e selecione-a.
3.  **Sincronize com o Gradle:** O Android Studio deve solicitar uma sincronização do Gradle automaticamente. Se não, vá em "File" > "Sync Project with Gradle Files".
4.  **Construa o Projeto:** Vá em "Build" > "Rebuild Project".
5.  **Execute o Aplicativo:**
    * Conecte um dispositivo Android ou inicie um Emulador Android.
    * Clique no botão "Run 'app'" (ícone de Play ▶️) no Android Studio.

## Fonte do Material

Este projeto foi construído com base nos exemplos e explicações de um material de estudo sobre desenvolvimento de aplicativos Android utilizando a linguagem Kotlin.

---
