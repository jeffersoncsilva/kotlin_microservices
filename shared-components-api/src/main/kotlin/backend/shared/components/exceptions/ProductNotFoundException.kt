package backend.shared.components.exceptions

class ProductNotFoundException(val msg: String) : RuntimeException(msg) {
}