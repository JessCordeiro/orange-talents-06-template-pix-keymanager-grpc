package pix.br.com.zup

import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pix.br.com.zup.cadastra.ChavePix
import pix.br.com.zup.cadastra.ChavePixRepository
import pix.br.com.zup.cadastra.ContaAssociada
import java.util.*

@MicronautTest
internal class ExcluiChaveEndpointTest (
    private val repository:ChavePixRepository,
    private val grpcClient: ExcluiChaveGrpcServiceGrpc.ExcluiChaveGrpcServiceStub
        ){


    private val chave = ChavePix(
        clienteId = UUID.fromString("c56dfef4-7901-44fb-84e2-a2cefb157890"),
        tipo = TipoDeChave.ALEATORIA,
        chave = UUID.randomUUID().toString(),
        tipoDeConta = TipoDeConta.CORRENTE,
        conta = ContaAssociada(
            instituicao = "ITAÚ UNIBANCO S.A.",
            nomeDoTitular = "Rafael M C Ponte",
            cpfDoTitular = "02467781054",
            agencia = "0001",
            numero = "291900",
            ispb = "60701190"
        )
    )

    @BeforeEach
    internal fun setUp() {

        repository.deleteAll()
        repository.save(chave)
    }

    @Test
    internal fun `deve excluir chave`(){

        val response = grpcClient.exclui(ExcluiChaveRequest.newBuilder()
            .setClienteId(chave.clienteId.toString())
            .setPixId(chave.id.toString())
            .build())
        with(response){
            assertEquals(chave.id, UUID.fromString(pixId))
            assertEquals(chave.clienteId, UUID.fromString(clienteId))
        }
        assertEquals(0, repository.count())
    }


    @Test
    internal fun `deve lancar excecao quando cliente nao e dono da chave`() {

        val response = assertThrows<StatusRuntimeException> {
            grpcClient.exclui(ExcluiChaveRequest.newBuilder()
                .setClienteId(UUID.randomUUID().toString())
                .setPixId(chave.id.toString())
                .build())
        }

        with(response) {
            assertEquals(Status.PERMISSION_DENIED.code, status.code)
            assertEquals("Solicitante não é o dono da chave", status.description)
        }
    }
}






