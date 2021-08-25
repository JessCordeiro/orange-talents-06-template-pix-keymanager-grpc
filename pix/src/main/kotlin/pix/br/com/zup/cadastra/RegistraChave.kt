package pix.br.com.zup.cadastra

import io.grpc.stub.ClientResponseObserver
import io.grpc.stub.StreamObserver
import pix.br.com.zup.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegistraChaveEndpoint(@Inject private val service: NovaChavePixService)
    : RegistrarChaveGrpc.RegistrarChaveImplBase()
    {
         fun registra (
            request: CadastraChaveRequest,
            responseObserver: StreamObserver<CadastraChaveResponse>
        ){
            val cadastraRequest = request.toModel()
            val chaveCriada= service.registra(cadastraRequest)

            responseObserver.onNext(CadastraChaveResponse.newBuilder()
                .setClienteId(chaveCriada.clienteId.toString())
                .setPixId(chaveCriada.id.toString())
                .build())
            responseObserver.onCompleted()



        }



    }









