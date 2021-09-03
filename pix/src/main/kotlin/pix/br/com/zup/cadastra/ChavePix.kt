package pix.br.com.zup.cadastra

import com.google.protobuf.Timestamp
import pix.br.com.zup.ListaChaveResponse
import pix.br.com.zup.TipoDeChave
import pix.br.com.zup.TipoDeConta
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


@Entity
class ChavePix (

    @field:NotBlank
    val clienteId: UUID,
    @field:NotNull
    @Column(nullable = false)
    val tipo: TipoDeChave?,
    @field:Size(max=77)
    @Column(nullable = false)
    val chave: String?,
    @field:NotNull
    @Column(nullable = false)
    val tipoDeConta: TipoDeConta?,

    @field:Valid
    @Embedded
    val conta: ContaAssociada


){

    @Id
    @GeneratedValue
    val id: UUID? = null

    @Column(nullable = false)
    val criadaEm: LocalDateTime = LocalDateTime.now()

    fun eDoCliente(clienteId: String): Boolean{
        return UUID.fromString(clienteId) == this.clienteId
    }


}

enum class TipoConta(val itau: String) {
    CONTA_CORRENTE("CONTA_CORRENTE"),
    CONTA_POUPANCA("CONTA_POUPANCA")
}
enum class TipoChave(val request: TipoChave){
    CPF(TipoChave.CPF),
    CELULAR(TipoChave.CELULAR),
    EMAIL(TipoChave.EMAIL),
    ALEATORIA(TipoChave.ALEATORIA);
}



