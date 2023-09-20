package backend.shared.components.dtos

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class ProductDTO(
    @NotBlank
    val productIdentifier: String,
    @NotBlank
    val nome: String,
    @NotBlank
    val descricao: String,
    @NotNull
    val preco: BigDecimal,
    @NotNull
    val categoryDTO : CategoryDTO
)
