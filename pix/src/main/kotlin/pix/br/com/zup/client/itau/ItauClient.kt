package pix.br.com.zup.client.itau

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client



@Client(value = "\${itau.contas.url}")
interface ItauClient {
    @Get(value = "/api/v1/clientes/{clienteId}/contas{?tipo}")
    fun buscarTipoDeConta(@PathVariable clienteId: String, @QueryValue tipo: String)
    : HttpResponse<DadosDaContaResponse>



}