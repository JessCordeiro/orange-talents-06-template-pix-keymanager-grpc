package pix.br.com.zup.listaChave

import io.grpc.Context.key
import io.grpc.stub.StreamObserver
import jdk.internal.org.jline.keymap.KeyMap.key
import pix.br.com.zup.ConsultaChaveRequest
import pix.br.com.zup.ListaChaveGrpcServiceGrpc
import pix.br.com.zup.ListaChaveResponse
import pix.br.com.zup.cadastra.ChavePixRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListaChaveEndpoint (
    @Inject private val repository: ChavePixRepository)
    : ListaChaveGrpcServiceGrpc.ListaChaveGrpcServiceImplBase() {

    override fun lista(request: ListaChaveRequest,
                       responseObserver: StreamObserver<ListaChaveResponse>
    ){
        val clientId = request.clienteId
        val chave = repository.findAllByClienteId(clientId).map{
            ListaChaveResponse.DetalheChave.newBuilder()
                .setIdCliente(it.clienteId.toString())
                .setIdPix(it.id.toString())
                .setChave(it.chave)
                .setTipoChave(it.tipo)
                .setTipoConta(it.tipoDeConta)
                .setCriadaEm(LocalDateTimeConverter.toProtobufTimestamp(it.criadaEm))
                .build()

            responseObserver?.onNext(ListaChaveResponse.newBuilder().addAllDetalheChave(key).build())
            responseObserver?.onCompleted()
        }
    }

}