package pix.br.com.zup.client.bc

import pix.br.com.zup.TipoDeChave
import pix.br.com.zup.TipoDeConta
import pix.br.com.zup.cadastra.ChavePix
import pix.br.com.zup.cadastra.ContaAssociada
import pix.br.com.zup.cadastra.NovaChavePix
import java.time.LocalDateTime
import java.util.*

data class CreatePixKeyResponse (
    val keyType: String,
    val key: String,
    val bankAccount: BankAccount,
    val owner: Owner,
    val createdAt: LocalDateTime
        ){

fun toChavePix(request: NovaChavePix, conta: ContaAssociada): ChavePix {

    return ChavePix(
        clienteId = UUID.fromString(request.clienteId),
        tipo = TipoDeChave.valueOf(keyType),
        chave = key,
        tipoDeConta = TipoDeConta.valueOf(request.tipoDeConta!!.name),
        conta = conta
    )
}

override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as CreatePixKeyResponse

    if (key != other.key) return false

    return true
}

override fun hashCode(): Int {
    return key.hashCode()
}
}

