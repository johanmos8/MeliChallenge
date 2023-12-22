package com.johanmos8.data.mapper

import com.johanmos8.data.networking.model.Category
import com.johanmos8.domain.model.ItemCategory

fun Category.toCategoryDomain() = ItemCategory(
    id = id,
    name = name
)
