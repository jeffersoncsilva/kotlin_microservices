package backend.user.api.controller

import backend.user.api.dtoconverters.toDto
import backend.user.api.model.User
import backend.user.api.service.UserService
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.mockk
import org.apache.coyote.Response
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import java.util.*
import kotlin.random.Random

@ExtendWith(SpringExtension::class)
@WebMvcTest(UserController::class)
class UserControllerTest {

    @MockkBean
    lateinit var userService: UserService
    @Autowired
    private lateinit var controller: UserController

    private fun getUser(): User {
        return User(
            id = Random.nextLong(0, Long.MAX_VALUE),
            nome = "Teste",
            cpf = "12345678910",
            key = "12345678910",
            endereco = "Teste",
            email = "email@email",
            dataCadastro = java.time.LocalDateTime.now(),
            telefone = "12345678910")
    }

    @Test
    fun `quando listar todos os usuarios, deve retornar uma lista de usuarios`(){
        val users = listOf(getUser(), getUser(), getUser())
        every { userService.getAll() } returns users
        val result = controller.getAll()
        assertThat(result.body).isNotEmpty
        assertThat(result.body?.size).isEqualTo(3)
    }

    @Test
    fun `quando buscar usuario por id, deve retornar httpresponse de codigo 200 com o usuario encontrado`(){
        val user = getUser()
        every { userService.getById(any()) } returns Optional.of(user)
        val result = controller.getById(1)
        assertThat(result.statusCode.value()).isEqualTo(200)
        assertThat(result.body).isNotNull
        assertThat(result.body?.cpf).isEqualTo(user.cpf)
    }

    @Test
    fun `quando buscar usuario por id, e nao existir usuario, deve retornar codigo de erro 404 not found`(){
        every { userService.getById(any()) } returns Optional.empty()
        val result = controller.getById(1234)
        assertThat(result.statusCode.value()).isEqualTo(404)
        assertThat(result.body).isNull()
    }

    @Test
    fun `quando criar um usuario novo, deve ser retornado um usuario criado com uma url com id do novo usuario`(){
        val newUser = getUser()
        val uriBuilder = mockk<UriComponentsBuilder>()
        every { uriBuilder.path(any()) } returns uriBuilder
        every { uriBuilder.build().toUri() } returns URI("user/${newUser.id}")
        every { userService.save(any()) } returns newUser
        val result = controller.createNewUser(newUser.toDto(), uriBuilder)
        assertThat(result.statusCode.value()).isEqualTo(201)
        assertThat(result.body).isNotNull
        assertThat(result.body?.cpf).isEqualTo(newUser.cpf)
        assertThat(result.headers["Location"]).isEqualTo(listOf("user/${newUser.id}"))
    }

    @Test
    fun `quando buscar um usuario por cpf ou key invalida, deve ser retornado erro de usuario nao encontrado`(){
        every { userService.findByCpfAndKey(any(), any()) } returns null
        val result = controller.findByCpf("12345678910", "12345678910")
        assertThat(result.statusCode.value()).isEqualTo(404)
        assertThat(result.body).isNull()
    }

    @Test
    fun `quando for deletar um usuario, nenhum conteudo e devolvido`(){
        every { userService.delete(any()) } returns Unit
        val result = controller.deleta(1)
        assertThat(result.statusCode.value()).isEqualTo(204)
        assertThat(result.body).isNull()
    }

    @Test
    fun `quando buscar usuario com um nome, deve ser retornado todos com o mesmo nome`(){
        val users = listOf(getUser(), getUser(), getUser())
        every { userService.queryByName(any()) } returns users
        val result = controller.buscarPeloNome("Teste")
        assertThat(result.statusCode.value()).isEqualTo(200)
        assertThat(result.body).isNotNull
        assertThat(result.body?.size).isEqualTo(3)
    }
}
