package backend.shared.components.dtos

import java.time.LocalDate

data class ErroDTO(
    val status: Int,
    val message: String,
    val timestamp: LocalDate
)
