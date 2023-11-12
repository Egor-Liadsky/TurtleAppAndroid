package com.turtleteam.core_view.utils

/**
 * Нужна для поиска элементов из массива
 * @return возвращает список найденых элементов
 */
fun List<String>.searchItem(value: String): List<String?> = this.map {
    if (it.lowercase().contains(value.lowercase())) {
        it
    } else {
        null
    }
}
