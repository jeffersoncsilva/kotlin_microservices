package backend.shared.components.dtos

import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

data class UserDTO(
    @NotBlank(message = "Nome não pode ser vazio")
    val nome: String,
    @NotBlank(message = "CPF não pode ser vazio")
    val cpf: String,
    val endereco: String? = "",
    @NotBlank(message = "Email não pode ser vazio")
    val email: String?  = "",
    val telefone: String? = "",
    val dataCadastro: LocalDateTime? = LocalDateTime.now(),
    val key: String? = ""
)