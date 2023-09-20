package backend.shared.components.dtos

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class ItemDTO(
    @NotBlank
    val productIdentifier: String,
    @NotNull
    val price: BigDecimal
) {
}