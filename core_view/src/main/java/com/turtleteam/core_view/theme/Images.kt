package com.turtleteam.core_view.theme

import androidx.annotation.DrawableRes
import androidx.compose.runtime.compositionLocalOf
import com.turtleteam.core_view.R

val LocalImages = compositionLocalOf<Images> { error("images wasnt provided") }

data class Images(
    @DrawableRes val selectGroup: Int,
    @DrawableRes val selectTeacher: Int,
    @DrawableRes val windowBackground: Int,
    @DrawableRes val topBarIcon: Int,
)

val images = Images(
    selectGroup = R.drawable.selectgroup,
    selectTeacher = R.drawable.selectteacher,
    windowBackground = R.drawable.toolbar_gradient,
    topBarIcon = R.drawable.moon
)
val darkImages = Images(
    selectGroup = R.drawable.selectgroupnight,
    selectTeacher = R.drawable.selectteachernight,
    windowBackground = R.drawable.toolbar_gradient_night,
    topBarIcon = R.drawable.sun
)

