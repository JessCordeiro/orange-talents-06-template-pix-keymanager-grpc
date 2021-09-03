package pix.br.com.zup.client.bc

import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client
import java.net.http.HttpResponse
import javax.validation.constraints.NotBlank

@Client("\${bc.pix.url}")
abstract class BcPixClient {

    @Post(value = "/api/v1/pix/keys")
    abstract fun cadastraChaveNoBC(@Body request: CreatePixKeyRequest)
            : HttpResponse<CreatePixKeyResponse>


    @Delete(value = "/api/v1/pix/keys/{key}")
    abstract fun excluiChaveNoBC(@PathVariable key: String, @Body request: DeletePixKeyRequest)
            : HttpResponse<DeletePixKeyResponse>


    @Get(value = "/api/v1/pix/keys/{key}")
    abstract fun consultaChavePix(@PathVariable @NotBlank key: String)
    : HttpResponse<PixKeyDetailsResponse>


}