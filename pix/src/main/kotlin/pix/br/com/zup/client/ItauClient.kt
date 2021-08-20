package pix.br.com.zup.client

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client
import pix.br.com.zup.TipoDeConta


@Client(value = "\${client.itau}")
interface ItauClient {
    @Get(value="/api/v1/clientes/{clienteId}/contas{?tipo}")
    fun buscarTipoDeConta(@PathVariable clientId: String, @QueryValue tipoDeConta: TipoDeConta)
    : HttpResponse<DadosResponse>



}