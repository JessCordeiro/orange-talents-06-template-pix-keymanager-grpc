package pix.br.com.zup.consultaChave

import pix.br.com.zup.TipoDeChave
import pix.br.com.zup.TipoDeConta
import pix.br.com.zup.cadastra.ChavePix
import pix.br.com.zup.cadastra.ContaAssociada
import java.time.LocalDateTime
import java.util.*

class Detalhes
    (
    val clienteId: UUID?,
    val id: UUID?,
    val criadaEm: LocalDateTime,
    val tipoDeChave:  TipoDeChave,
    val chave: String,
    val tipoDeConta: TipoDeConta,
    val conta: ContaAssociada
) {

    companion object {
        fun of(chavePix: ChavePix): Detalhes {

            return Detalhes(
                clienteId = chavePix.clienteId,
                id = chavePix.id!!,
                criadaEm = chavePix.criadaEm,
                tipoDeChave = chavePix.tipo!!,
                chave = chavePix.chave!!,
                tipoDeConta = chavePix.tipoDeConta!!,
                conta = chavePix.conta
            )
        }
    }
}