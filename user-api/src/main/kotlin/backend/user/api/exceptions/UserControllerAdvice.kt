package backend.user.api.exceptions

import backend.shared.components.dtos.ErroDTO
import backend.shared.components.exceptions.UserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import java.time.LocalDate
import java.time.LocalDateTime

@ControllerAdvice(basePackages = ["backend.user.api"])
class UserControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFound(ex: UserNotFoundException): ErroDTO {
        return ErroDTO(HttpStatus.NOT_FOUND.value(), "Usuário não foi encontrado", LocalDate.now())
    }
}