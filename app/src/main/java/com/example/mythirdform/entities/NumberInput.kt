package com.example.mythirdform.entities

class NumberInput(
    id: Int,
    value: Float,
    label: String,
    var measure: String,
    var inputNumber: Int = -1
) : Option(id, value, label) {
}
