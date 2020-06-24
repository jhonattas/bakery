1. Dado o JSON [https://sampletestingproject-4a8fc.web.app/teste.json], escreva uma aplicação
simples que exiba em uma lista todos os “toppings” (ver JSON).



2. Escreva a saída do algoritmo descrito abaixo
```    
    var numero : inteiro
    inicio
        funcao facaAlgo(numero)
            se (numero <= 1)
                retorne numero
            senao
                retorne numero * facaAlgo(numero - 1)
            fim-se
        fim

    mostre facaAlgo(6)
    fim
```

Uma representação em kotlin funcional pode ser vista a seguir, o resultado da mesma inclusive, seria 720 (neste caso especifico recebendo o valor 6 como variavel da funcao)
```
fun main() {
    println(facaAlgo(6))
}

fun facaAlgo(numero: Int) : Int {
    if(numero <= 1){
        return numero
    } else {
        return (numero * facaAlgo(numero -1))
    } 
}
```



3. Uma operação de rotação à esquerda em array desloca cada unidade de elementos do array
para a esquerda. Por exemplo, se 2 rotações à esquerda forem executadas no array [1,2,3,4,5] o
array se tornara [3,4,5,1,2]. Dado um array de números inteiros e um número n, escreva o código
que executa n rotações a esquerda no array e retorna o array na nova ordem.
int[] rotacaoEsquerda(int[] a, int d) {
}

```
fun main(){
    var lista: IntArray = intArrayOf(1,2,3,4,5)
 	println(lista.contentToString())
    lista = rotacionaEsquerda(lista, 1)
    println(lista.contentToString())
}

fun rotacionaEsquerda(lista: IntArray, rotacoes: Int): IntArray {
    val novaLista = lista.copyOf()
    var interador = rotacoes
    if (interador > lista.size) interador %= lista.size
    lista.forEachIndexed { index, valor ->
        val novoIndex = (index + (lista.size - interador)) % lista.size
        novaLista[novoIndex] = valor
    }
    return novaLista
}
```


4. Dado a seguinte lista de veículos escreva um algoritmo que faça a ordenação do veículo de
menor valor para o veículo de maior valor, explique o seu algoritmo.
Veículo
Preço
Brasília R$ 8.000,00
Fusca R$ 5.000,00
Gol R$ 23.000,00
Kombi R$ 39.000,00



5. Quais são as possibilidade para se obter uma referência a uma instância de uma view descrita
em XML com o id = “textViewNomeCompleto”?

```
view.findViewById(R.id.textViewNomeCompleto)
```
Acima é um exemplo de como obter a referencia / acessar de uma instancia com este identificador em código, mas a nivel literal a possíbilidade geralmente é apenas uma. Já que um id até pode ser duplicado dentro de um projeto (voce pode ter um id de mesmo nome, em views diferentes, em componentes e layouts diferentes, etc), mas todo ID deste tipo de elemento no android É ORIENTADO A CONTEXTO, então em um mesmo nível duas textViews com esse mesmo id... Não vai rolar. Se eu tiver uma layout cadastro_usuario.xml poderá ser observado que os ids serão / tendem a ser unicos. Alguns tipos de forçar duplicatas, sequer permitem que o app gere build.


6. É possível obter uma referência a uma text view com id = “textViewNomeCompleto” usando a
string “textViewNomeCompleto" em runtime?

Sim é possível, inclusive depois de pensar um pouco até consigo imaginar casos em que o mesmo seja necessário (não fico muito confortável com a idéia, mas consigo visualizar tranquilamente em um caso de teste automatizado de tela por exemplo, para validar se uma tela populou dados provenientes do servidor corretamente na tela para o usuário), MAS salvo essas manipulações automatizadas, dependender de captar referencias asssim em tempo de runtime não considero interessantes por haver meios melhores. Toda a construção de contexto do Android, e ids relacionadas a mesma, foi estruturada e ainda hoje evolui bastante para que seja feito diferente.


7. Qual a diferença entre dp, sp e px?

Ambos são utilizados para medidas, mas o px especificamente é literalmente a abreviação de pixel, geralmente é utilizado para medidas, ou construções em que algo necessita obrigatóriamente possuir um tamanho ou uma distancia absoluta em um layout. Já o dp e sp sao medidas com foco na densidade de tela, layouts mais homogemeos e responsivos os utilizam. Sendo sempre utilizados o SP para elementos de texto, e o DP para os outros elementos. Um layout que abusa muito de PX geralmente não tende a ser responsivo, ou abusa de diversos hacks para faze-lo em resoluções diferentes. Em contra partida, um bom layout escrito corretamente com dp e sp, torna o retrabalho e detalhamento de um layout responsivo muito mais simples, robusto, e eficiente.

8. Defina o papel da ViewModel da Android Architecture Components no ciclo de vida de uma
activity.

O ViewModel é um componente de um dos padrões de desenvolvimento mais amplamente utilizados atualmente no Android, graças a ele é possível implementar dentro do código uma estrutura de organização que permite separar de maneira decente um modelo e uma View. Um ViewModel opera como uma camada entre ambos, e existem inúmeras vantagens da existencia do mesmo, afim de tornar o código mais desacoplado, e assim por sua vez, mais legivel, e até mais facilmente testável, já que quando corretamente (e bem implementado) permite que justamente essas camadas de view e model não precisem de um "conhecimento" ou dependencia muito alta entre si. Em um exemplo do mundo real, podemos compara-lo (ainda que de maneira leiga) a um daqueles filtros de energia que utilizamos no escritorio, este se conecta a rede elétrica, e tambem por sua vez se conecta a um dispositivo que precisa de energia, como o notebook em que escrevo neste momento. Posso trocar na tomada do mesmo o notebook pelo celular, e este desacoplamento de camada não afeta o restante da estrutura (inclusive foi uma das criações em patterns que mais me fascinou desde a criação, quando a google inseriu os fragments no android 3, haviam um bocado de gambiarras para lidar com dados que travegavam entre um fragment que era algo extremamente novo e uma activity complexa).