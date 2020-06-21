package cenemas.core_enums

enum class sign_up_field_enum(val fieldName: String) {
    USER_NAME("User Name"),
    EMAIL_ADDRESS("email"),
    PASSWORD("Password"),
    RE_PASSWORD("confirm password"),
    ALIAS("alias")
}