package pix.br.com.zup.cadastra

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface ChavePixRepository:JpaRepository<ChavePix, UUID> {

    fun existsByChave(valorChave: String?): Boolean
    fun findAllByClienteId(clienteId: UUID): MutableList<ChavePix>
    fun findByIdCliente(clienteId: String): List<ChavePix>


}