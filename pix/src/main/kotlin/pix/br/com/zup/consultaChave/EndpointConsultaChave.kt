package pix.br.com.zup.consultaChave

import io.grpc.stub.StreamObserver
import io.micronaut.http.HttpResponse
import pix.br.com.zup.ConsultaChaveGrpcServiceGrpc
import pix.br.com.zup.ConsultaChaveResponse
import pix.br.com.zup.cadastra.ChavePixRepository
import io.micronaut.validation.validator.Validator
import pix.br.com.zup.cadastra.BCBClient
import pix.br.com.zup.cadastra.ChavePix
import pix.br.com.zup.client.bc.BcPixClient
import pix.br.com.zup.excecao.ClienteNaoEDonoDaChaveException
import pix.br.com.zup.excecao.NotFoundException
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class EndpointConsultaChave (
    @Inject private val repository: ChavePixRepository,
    @Inject private val bcPixClient: BcPixClient,
    @Inject private val validator: Validator,
    @Inject private val resposta: BCBClient,
    @Inject private val detalhes: Detalhes
) : ConsultaChaveGrpcServiceGrpc.ConsultaChaveGrpcServiceImplBase() {

    override fun consulta(request: Detalhes,
                          responseObserver: StreamObserver<ConsultaChaveResponse>
    ) {

       val clientNoExists = !repository.findByIdCliente(request.clienteId.toString())
        if (clientNoExists){
            throw NotFoundException("Id não existe")
        }

        val chave:ChavePix = repository.findById(request.id).orElseThrow(){
            NotFoundException("Pix id não existe")
        }
       val chaveNaoDoCliente = !chave.eDoCliente(request.clienteId.toString())
        if(chaveNaoDoCliente){
            throw ClienteNaoEDonoDaChaveException("Cliente não é o dono da chave")
        }
        val bcResponse  = bcPixClient.consultaChavePix(chave.id.toString())
        responseObserver?.onNext(
            ConsultaChaveResponse.newBuilder()
                .setIdCliente(detalhes.clienteId.toString())
                .setIdPix(detalhes.id.toString())


                .build())

                 responseObserver.onCompleted()









    }
}


