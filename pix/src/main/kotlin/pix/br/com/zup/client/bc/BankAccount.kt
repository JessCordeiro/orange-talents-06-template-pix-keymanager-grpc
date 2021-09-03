package pix.br.com.zup.client.bc

data class BankAccount (
    val participant: String,
    val branch: String,
    val accountNumber: String,
    val accountType: AccountType
        )


