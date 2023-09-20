package backend.user.api.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var nome: String,
    var cpf: String,
    var key: String? = null,
    var endereco: String?,
    var email: String?,
    var telefone: String?,
    val dataCadastro:  LocalDateTime? = null
)
