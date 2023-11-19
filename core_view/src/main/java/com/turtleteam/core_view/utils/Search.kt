package com.turtleteam.core_view.utils

/**
 * Нужна для поиска элементов из массива
 * @return возвращает список найденых элементов
 */
fun List<String>.searchItem(value: String): List<String> {
    if (value == "") return this
    val groups = this.toMutableList()
    val filteredItems = mutableListOf<String>()
    groups.map { if (!it.lowercase().contains(value.lowercase())) filteredItems.add(it) }
    groups.removeAll(filteredItems)
    return groups
}
