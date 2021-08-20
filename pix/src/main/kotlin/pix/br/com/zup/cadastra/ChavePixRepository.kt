package pix.br.com.zup.cadastra

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface ChavePixRepository:JpaRepository<ChavePix, UUID> {

    override fun existsById(clienteId: UUID): Boolean


}