package pix.br.com.zup.removeChave

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
class ExcluirRequest (
    @field:NotBlank
    val clientId:String?,

    @field:NotBlank
    val pixId:String?
        )
