package backend.user.api.controller

import backend.user.api.dtoconverters.toDto
import backend.user.api.model.User
import backend.user.api.service.UserService
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
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
import org.springframework.web.context.WebApplicationContext
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
//        mockMvc.get("/user"){
//            contentType = org.springframework.http.MediaType.APPLICATION_JSON
//        }.andExpect {
//            status { isOk() }
//            content { contentType(org.springframework.http.MediaType.APPLICATION_JSON) }
//        }
        val result = listOf(getUser(), getUser(), getUser())
        every { userService.getAll() } returns result
        assertThat(controller.getAll().body).isNotEmpty
    }
/*
    @Test
    fun `quando buscar usuario por id, deve retornar httpresponse de codigo 200 com o usuario encontrado`(){
//        val user = getUser()
//        Mockito.`when`(userService.getById(any())).thenReturn(Optional.of(user))
//        mockMvc.get("/user/1"){
//            contentType = org.springframework.http.MediaType.APPLICATION_JSON
//        }.andExpect {
//            status { isOk() }
//            content { contentType(org.springframework.http.MediaType.APPLICATION_JSON) }
//        }
    }

    @Test
    fun `quando buscar usuario por id, e nao existir usuario, deve retornar codigo de erro 404 not found`(){
        mockMvc.get("/user/1"){
            contentType = org.springframework.http.MediaType.APPLICATION_JSON
        }.andExpect {
            status { isNotFound() }
        }
    }

    @Test
    fun `quando criar um usuario novo, deve ser retornado um usuario criado com uma url com id do novo usuario`(){
//        val user = getUser()
//        Mockito.`when`(userService.save(any())).thenReturn(user)
//        mockMvc.post("/user"){
//            contentType = org.springframework.http.MediaType.APPLICATION_JSON
//            content = objectMapper.writeValueAsString(user.toDto())
//        }.andExpect {
//            status { isCreated() }
//            header { exists("Location") }
//        }
    }
*/


}
