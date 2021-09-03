package pix.br.com.zup.listaChave

import pix.br.com.zup.TipoDeChave
import pix.br.com.zup.TipoDeConta
import pix.br.com.zup.cadastra.ChavePix
import java.time.LocalDateTime
import java.util.*


class ListaChaveRequest (
    chave: ChavePix
        )

{
    var pixId: String = chave.chave!!
    var clienteId: UUID = chave.clienteId
    var tipoDaChave: TipoDeChave = chave.tipo!!
    var valorChave: String = chave.chave!!
    var tipoDaConta: TipoDeConta = chave.tipoDeConta!!
    var dataCriacao: LocalDateTime? = chave.criadaEm
}