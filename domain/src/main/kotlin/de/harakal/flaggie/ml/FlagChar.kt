package de.harakal.flaggie.ml

enum class SignType {
    CHAR,
    CONTROL
}

enum class ControlSign {
    NONE,
    LETTER_TO_FOLOW,
    NUMBER_TO_FOLLOW,
    ERROR_ATTENTION,
    REST_SPACE, //
    CANCEL
}

data class FlagChar (val signType:SignType, val controlSign: ControlSign)
