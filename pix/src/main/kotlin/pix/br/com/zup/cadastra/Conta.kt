package pix.br.com.zup.cadastra

import io.micronaut.data.annotation.Embeddable

@Embeddable
class Conta (
    val instituicao: String,
    val nomeDoTitular: String,
    val cpfDoTitular: String,
    val agencia: String,
    val numero: String,
    val ispb: String
        )
