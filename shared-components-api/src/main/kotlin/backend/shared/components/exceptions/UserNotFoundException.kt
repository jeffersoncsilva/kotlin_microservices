package backend.shared.components.exceptions

class UserNotFoundException(val msg: String) : RuntimeException(msg) {
}