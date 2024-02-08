package com.turtleteam.core_view.theme

import androidx.annotation.DrawableRes
import androidx.compose.runtime.compositionLocalOf
import com.turtleteam.core_view.R

val LocalImages = compositionLocalOf<Images> { error("images wasnt provided") }

data class Images(

    // Register
    @DrawableRes val selectGroup: Int,
    @DrawableRes val selectInstitution: Int,
    @DrawableRes val selectTheme: Int,

    // Loading State
    @DrawableRes val error: Int,
    @DrawableRes val notFound: Int,
    @DrawableRes val selectGroupEmpty: Int
)

val images = Images(

    // Register
    selectGroup = R.drawable.ic_choose_group,
    selectInstitution = R.drawable.ic_choose_institution,
    selectTheme = R.drawable.ic_choose_theme,

    // Loading State
    error = R.drawable.ic_error,
    notFound = R.drawable.ic_not_found,
    selectGroupEmpty = R.drawable.ic_select_group_empty
)
val darkImages = Images(

    // Register
    selectGroup = R.drawable.ic_choose_group_dark,
    selectInstitution = R.drawable.ic_choose_institution_dark,
    selectTheme = R.drawable.ic_choose_theme_dark,

    // Loading State
    error = R.drawable.ic_error_dark,
    notFound = R.drawable.ic_not_found_dark,
    selectGroupEmpty = R.drawable.ic_select_group_empty_dark
)
