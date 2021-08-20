package pix.br.com.zup.cadastra

import io.micronaut.validation.Validated
import pix.br.com.zup.client.ItauClient
import pix.br.com.zup.excecao.ChavePixExistenteException
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Singleton
class NovaChavePixService(@Inject val repository:ChavePixRepository,
                          @Inject val itauClient:ItauClient


) {
    @Throws(ChavePixExistenteException::class)
    @Transactional
    fun registra (@Valid novaChave:NovaChavePix){

        if (repository.existsById(novaChave.chave))
            throw ChavePixExistenteException(s = "Chave já existe no sistema")

        val dadosItauResponse = itauClient.buscarTipoDeConta(novaChave.clienteId!!, novaChave.tipoDeConta!!)

        val conta = dadosItauResponse.body().toModel();  throw IllegalStateException("Cliente não encontrado")

        val chave = novaChave.toModel(conta)
        repository.save(chave)
        return chave
    }



}
//Extension Functions

fun NovaChavePix.toModel() : ChavePix {

    return ChavePix(
        clienteId = UUID.fromString(clienteId),
        tipo = this.tipo,
        chave = this.chave,
        tipoDeConta = this.tipoDeConta,
        )
}




