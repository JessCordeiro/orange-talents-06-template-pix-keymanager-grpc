package pix.br.com.zup.cadastra

import io.micronaut.http.HttpStatus
import io.micronaut.http.client.exceptions.HttpClientResponseException
import pix.br.com.zup.CadastraChaveRequest
import pix.br.com.zup.TipoDeChave
import pix.br.com.zup.TipoDeConta
import pix.br.com.zup.client.bc.BcPixClient
import pix.br.com.zup.client.bc.PixKeyDetailsResponse
import pix.br.com.zup.excecao.InternalServerErrorException
import pix.br.com.zup.excecao.NotFoundException
import pix.br.com.zup.removeChave.ExcluirChaveService
import pix.br.com.zup.removeChave.ExcluirRequest
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

fun ExcluirRequest.toExcluirRequest(): ExcluirRequest{
    return ExcluirRequest(clientId, pixId)
}

class BCBClient(
    private val client:BcPixClient
) {
    fun findPixKeyDetails(key: String): PixKeyDetailsResponse {
        try {
            val response = client.consultaChavePix(key)

            if (response.statusCode().equals(HttpStatus.NOT_FOUND) ) {
                throw NotFoundException("Chave pix não encontrada")
            }

            return response.body()!!
        } catch (e: HttpClientResponseException) {

            throw InternalServerErrorException("Erro ao buscar chave pix")
        }
    }
}

