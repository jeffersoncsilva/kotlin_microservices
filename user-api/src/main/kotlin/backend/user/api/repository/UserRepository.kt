package backend.user.api.repository

import backend.user.api.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {

    fun findByCpfAndKey(cpf: String, key: String): User?

    fun queryByNomeLike(nome: String): List<User>
}