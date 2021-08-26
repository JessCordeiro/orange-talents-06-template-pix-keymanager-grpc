package pix.br.com.zup.cadastra

import pix.br.com.zup.CadastraChaveRequest
import pix.br.com.zup.TipoDeChave
import pix.br.com.zup.TipoDeConta
import java.util.*

fun CadastraChaveRequest.toModel(): NovaChavePix{
    return NovaChavePix(
        clienteId = clienteId,
        tipo = when(tipoDeChave){
            TipoDeChave.UNKNOWN_TIPO_CHAVE -> null
            else -> TipoDeChave.valueOf(tipoDeChave.name)
        },
        chave = chave,
        tipoDeConta = when (tipoDeConta){
            TipoDeConta.UNKNOWN_TIPO_CONTA -> null
            else -> TipoDeConta.valueOf(tipoDeConta.name)
        }
    )
}

//fun NovaChavePix.toConta(conta: Conta): ChavePix {
//
//    return ChavePix(
//        clienteId = UUID.fromString(clienteId),
//        tipo = this.tipo,
//        chave = this.chave,
//        tipoDeConta = this.tipoDeConta,
//    )
//}