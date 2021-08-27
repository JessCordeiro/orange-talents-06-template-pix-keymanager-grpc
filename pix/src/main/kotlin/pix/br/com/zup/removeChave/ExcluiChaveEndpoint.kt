package pix.br.com.zup.removeChave

import io.grpc.stub.ClientResponseObserver
import io.grpc.stub.StreamObserver
import javax.inject.Inject
import javax.inject.Singleton
import pix.br.com.zup.*
import pix.br.com.zup.cadastra.toExcluirRequest

@Singleton
class ExcluiChaveEndpoint (
    @Inject private val service: ExcluirChaveService)
    :ExcluiChaveGrpcServiceGrpc.ExcluiChaveGrpcServiceImplBase(){
    override fun exclui(request: ExcluirRequest,
                        responseObserver: StreamObserver<ExcluiChaveResponse>
    ){
        val excluiRequest = request.toExcluirRequest()
        val chaveExcluida = service.exclui(excluiRequest)

        responseObserver.onNext(ExcluiChaveResponse.newBuilder()
            .setClienteId(chaveExcluida.clienteId.toString())
            .setPixId(chaveExcluida.id.toString())
            .build())

        responseObserver.onCompleted()

    }



    }
