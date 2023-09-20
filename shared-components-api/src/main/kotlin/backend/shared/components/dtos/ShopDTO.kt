package backend.shared.components.dtos

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDateTime

data class ShopDTO(
    @NotBlank
    val userIdentifier: String,
    @NotNull
    val total: BigDecimal,
    @NotNull
    val date : LocalDateTime,
    @NotNull
    val products : List<ItemDTO>
)
