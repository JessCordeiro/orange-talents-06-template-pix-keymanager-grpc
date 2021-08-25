package pix.br.com.zup.cadastra

import pix.br.com.zup.TipoDeChave
import pix.br.com.zup.TipoDeConta
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
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
    val tipoDeConta: TipoDeConta?

){

    @Id
    @GeneratedValue
    val id: UUID? = null
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