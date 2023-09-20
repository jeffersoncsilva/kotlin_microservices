package backend.user.api.controller

import backend.shared.components.dtos.UserDTO
import backend.user.api.dtoconverters.toDto
import backend.user.api.dtoconverters.toUser
import backend.user.api.service.UserService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/user")
class UserController(val service: UserService) {

    @GetMapping
    fun getAll() : ResponseEntity<List<UserDTO>> {
        return ResponseEntity.ok(service.getAll().map { it.toDto() })
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<UserDTO> {
        val user = service.getById(id)
        if(user.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(user.get().toDto())
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    fun createNewUser(@RequestBody @Valid user: UserDTO, uriBuilder: UriComponentsBuilder): ResponseEntity<UserDTO> {
        val result = service.save(user.toUser())
        val uri = uriBuilder.path("/user/${result?.id}").build().toUri()
        return ResponseEntity.created(uri).body(result.toDto())
    }

    @GetMapping("/cpf/{cpf}")
    fun findByCpf(@RequestParam(name="key", required = true) key: String, @PathVariable cpf: String): ResponseEntity<UserDTO>{
        val response = service.findByCpfAndKey(cpf, key) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(response?.toDto())
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deleta(@PathVariable id: Long) : ResponseEntity<Unit>{
        service.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @GetMapping("/search")
    fun buscarPeloNome(@RequestParam(name = "nome", required = true) nome: String): ResponseEntity<List<UserDTO>>{
        val response = service.queryByName(nome)
        return ResponseEntity.ok(response.map { it.toDto() })
    }

}