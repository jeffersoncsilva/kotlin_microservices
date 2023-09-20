package backend.user.api.dtoconverters

import backend.shared.components.dtos.UserDTO
import backend.user.api.model.User
import java.time.LocalDateTime

fun User.toDto() : UserDTO {
    return UserDTO(
        nome = this.nome,
        cpf = this.cpf,
        endereco = this.endereco,
        email = this.email,
        telefone = this.telefone,
        dataCadastro = this.dataCadastro,
        key = this.key
    )
}

fun UserDTO.toUser() : User {
    return User(
        nome = this.nome,
        cpf = this.cpf,
        endereco = this.endereco,
        email = this.email,
        telefone = this.telefone,
        dataCadastro = this.dataCadastro ?: LocalDateTime.now(),
        key = this.key
    )
}