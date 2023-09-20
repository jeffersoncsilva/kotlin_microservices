package backend.shared.components.dtos

import jakarta.validation.constraints.NotNull

data class CategoryDTO(
    @NotNull
    val id: Long,
    val nome: String
)
