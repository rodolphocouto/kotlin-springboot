package org.kotlin.springboot.domain.entity

enum class Industry(val id: Int) {

    E_COMMERCE(1),
    SEARCH(2),
    SOCIAL(3),
    CLOUD(4),
    ENTERTAINMENT(5),
    CHAT(6);

    companion object {
        fun of(id: Int): Industry = values().first { it.id == id }
    }
}