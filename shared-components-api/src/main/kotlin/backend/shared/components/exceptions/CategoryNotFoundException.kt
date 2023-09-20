package backend.shared.components.exceptions

class CategoryNotFoundException(val msg: String) : RuntimeException(msg) {
}