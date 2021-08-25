package pix.br.com.zup.cadastra

import io.micronaut.core.annotation.Introspected
import pix.br.com.zup.TipoDeChave
import pix.br.com.zup.TipoDeConta
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Introspected
data class NovaChavePix (
    @field:NotBlank
    val clienteId:String?,
    @field:NotNull
    val tipo: TipoDeChave?,
    @field:Size(max=77)
    val chave: String?,
    @field:NotNull
    val tipoDeConta: TipoDeConta?

    ){
    @Id
    @GeneratedValue
    val id: UUID? = null

    fun toModel(conta:Conta): ChavePix{
        return ChavePix(
            clienteId = UUID.fromString(this.clienteId),
            tipo = TipoDeChave.valueOf(this.tipo!!.name),
            chave = if(this.tipo == TipoDeChave.ALEATORIA) UUID.randomUUID().toString() else TipoChave.valueOf(this.chave!!).toString(),
            tipoDeConta = TipoDeConta.valueOf(this.tipoDeConta!!.name),



        )
    }


}