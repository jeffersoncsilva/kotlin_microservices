package backend.user.api.service

import backend.user.api.dtoconverters.toDto
import backend.user.api.model.User
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random

class UserServiceTest {

    val service = mockk<UserService>()

    @Test
    fun `quando listar todos os usuarios, deve retornar uma lista de usuarios`(){
        every { service.getAll() } returns listOf(getUser(), getUser(), getUser())

        val result = service.getAll()

        verify { service.getAll() }

        assertEquals(3, result.size)
    }

    @Test
    fun `quando salvar um usuario, o id do usuario salvo deve ser retornado`(){
        val user = getUser()
        every { service.save(user) } returns user
        val result = service.save(user)
        assertEquals(user.id, result.id)
        assertEquals(user, result)
    }

    @Test
    fun `quando buscar usuario por id, o usuario encontrado deve ser retornado`(){
        val user = getUser()
        every { service.getById(2) } returns Optional.of(user)
        val result = service.getById(2)
        assertTrue(result.isPresent)
        assertEquals(user, result.get())
        assertEquals(user.id, result.get().id)
    }

    @Test
    fun `quando buscar usuario por id, e nao existir usuario com o id informado, deve retornar um optional vazio`(){
        every { service.getById(2) } returns Optional.empty()
        val result = service.getById(2)
        assertTrue(result.isEmpty)
    }

    @Test
    fun `quando usuario for editado, deve retornar o usuario editado`(){
        val user = getUser()
        every { service.editUser(2, user.toDto()) } returns user.toDto()
        val result = service.editUser(2, user.toDto())
        assertEquals(user.toDto(), result)

    }

    @Test
    fun `quando usuario for buscado por cpf e key, um usuario com o mesmo cpf e key deve ser retornado`(){
        val user = getUser()
        every { service.findByCpfAndKey("12345678910","12345678910") } returns user
        val ressult = service.findByCpfAndKey("12345678910","12345678910")
        assertEquals(user, ressult)
        assertEquals(user.cpf, ressult?.cpf)
        assertEquals(user.key, ressult?.key)
    }

    @Test
    fun `quando um nome de usuario for buscado, deve retornar todos usuarios com esse nome`(){
        val user = getUser()
        every { service.queryByName("Teste") } returns listOf(user, getUser(), getUser())
        val result = service.queryByName("Teste")
        assertEquals(3, result.size)
    }

    fun getUser(): User{
        return User(
            id = Random.nextLong(),
            nome = "Teste",
            cpf = "12345678910",
            key = "12345678910",
            endereco = "Teste",
            email = "email@email",
            dataCadastro = LocalDateTime.now(),
            telefone = "12345678910")
    }
}