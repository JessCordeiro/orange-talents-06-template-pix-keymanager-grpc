package pix.br.com.zup.client.bc

import pix.br.com.zup.TipoDeChave
import pix.br.com.zup.TipoDeConta
import pix.br.com.zup.cadastra.ContaAssociada
import java.time.LocalDateTime

data class PixKeyDetailsResponse (
    val keyType: TipoDeChave,
    val key: String,
    val bankAccount: BankAccount,
    val owner: Owner,
    val createdAt: LocalDateTime
        ) {



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
