package pix.br.com.zup.removeChave

import org.slf4j.LoggerFactory
import pix.br.com.zup.cadastra.ChavePix
import pix.br.com.zup.cadastra.ChavePixRepository
import pix.br.com.zup.excecao.ChaveNaoEncontradaException
import pix.br.com.zup.excecao.ClienteNaoEDonoDaChaveException
import java.util.*
import java.util.logging.Logger
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid


@Singleton
class ExcluirChaveService ( @Inject val repository: ChavePixRepository
        ) {
    private val LOGGER = LoggerFactory.getLogger(this::class.java)
    @Transactional
    fun exclui(@Valid request: ExcluirRequest): ChavePix {

        val possivelChave: Optional<ChavePix> = repository.findById(UUID.fromString(request.pixId))
        if(possivelChave.isEmpty){
            LOGGER.error("Chave não encontrada")
            throw ChaveNaoEncontradaException("Chave não encontrada")
        }
        return possivelChave.get()
         val chave: ChavePix = possivelChave.get()
        clienteDono(chave, request.clientId)

        repository.delete(chave)
        return chave



    }
    private fun clienteDono(chave : ChavePix, clienteId: String?){
        if(!chave.eDoCliente(clienteId!!)){
            throw ClienteNaoEDonoDaChaveException("Solicitante não é o dono da chave")
        }
    }

}

