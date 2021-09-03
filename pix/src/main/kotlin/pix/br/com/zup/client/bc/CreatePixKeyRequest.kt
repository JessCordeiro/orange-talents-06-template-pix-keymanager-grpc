package pix.br.com.zup.client.bc

data class CreatePixKeyRequest (
    val keyType: String,
    val key: String,
    val bankAccount: BankAccount,
    val owner: Owner
        )
