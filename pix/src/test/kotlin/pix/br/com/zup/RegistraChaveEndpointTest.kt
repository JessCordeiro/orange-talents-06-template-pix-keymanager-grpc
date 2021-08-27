/*package pix.br.com.zup

import io.micronaut.data.annotation.Repository
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import pix.br.com.zup.cadastra.ChavePixRepository
import pix.br.com.zup.cadastra.NovaChavePix
import pix.br.com.zup.cadastra.TipoChave
import pix.br.com.zup.client.DadosDaContaResponse
import pix.br.com.zup.client.ItauClient
import java.net.http.HttpClient
import java.net.http.HttpResponse
import javax.inject.Inject

@MicronautTest(transactional = false)
internal class RegistraChaveEndpointTest (
    private val repository: ChavePixRepository,
    private val grpc:RegistrarChaveGrpc.RegistrarChaveBlockingStub
        )
{

    private val idClienteTeste = "5ac59211-21b2-4158-898fb-d99rtd9f8ccb"

    @Inject
    lateinit var itauClient: ItauClient

    @BeforeEach
    internal fun setUp() {

        repository.deleteAll()
    }

    @Test
    internal fun `deve registrar nova chave`() {

        Mockito.`when`(itauClient.buscarTipoDeConta(idClienteTeste, TipoDeConta.CORRENTE.toString()))
            .thenReturn(io.micronaut.http.HttpResponse.ok())


        val response = grpc.cadastrar(CadastraChaveRequest.newBuilder()
            .setClienteId(idClienteTeste)
            .setTipoDeChave(TipoDeChave.ALEATORIA)
            .setTipoDeConta(TipoDeConta.CORRENTE)
            .build()
        )


        with(response) {
            assertEquals(idClienteTeste, clienteId)

        }
    }








}*/