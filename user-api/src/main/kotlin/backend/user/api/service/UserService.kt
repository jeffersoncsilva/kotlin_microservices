package backend.user.api.service

import backend.shared.components.dtos.UserDTO
import backend.user.api.dtoconverters.toDto
import backend.user.api.model.User
import backend.user.api.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import java.util.UUID

@Service
@RequiredArgsConstructor
class UserService(val userRepository: UserRepository) {

    fun getAll() = userRepository.findAll()

    fun getById(id: Long) = userRepository.findById(id)

    fun save(user: User) : User{
        user.key ?: UUID.randomUUID().toString()
        val u2 = userRepository.save(user)
        return u2
    }

    fun delete(id: Long) = userRepository.deleteById(id)

    fun findByCpfAndKey(cpf: String, key: String) = userRepository.findByCpfAndKey(cpf, key)

    fun queryByName(name: String) = userRepository.queryByNomeLike(name)

    fun editUser(id: Long, userDto: UserDTO): UserDTO {
        var user = userRepository.findById(id).get()
        if(user.email != null && user.email != userDto.email){
            user.email = userDto.email
        }
        if(user.telefone != null && user.telefone != userDto.telefone){
            user.telefone = userDto.telefone
        }
        if(user.endereco != null && user.endereco != userDto.endereco){
            user.endereco = userDto.endereco
        }
        user = userRepository.save(user)
        return user.toDto()
    }
}