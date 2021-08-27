package pix.br.com.zup.cadastra

import io.micronaut.validation.Validated


import pix.br.com.zup.client.ItauClient
import pix.br.com.zup.excecao.ChavePixExistenteException
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
    fun registra (@Valid novaChave:NovaChavePix): ChavePix {

        if (repository.existsByChave(novaChave.chave!!))
            throw ChavePixExistenteException(s = "Chave já existe no sistema")

        val dadosItauResponse = itauClient.buscarTipoDeConta(novaChave.clienteId!!, novaChave.tipoDeConta!!.name)

        val conta = dadosItauResponse.body()?.toConta() ?:let{
            throw IllegalStateException("Cliente não encontrado")
        }

        val chave = novaChave.toConta(conta)

        repository.save(chave)
        return chave
    }



}





