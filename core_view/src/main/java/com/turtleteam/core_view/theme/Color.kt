package com.turtleteam.core_view.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val LocalColors = compositionLocalOf<Colors> { error("colors wasnt provided") }

data class Colors(

    //StageBar
    val stageBarSelectTextColor: Color,
    val stageBarSelectBackgroundColor: Color,
    val stageBarUnselectTextColor: Color,
    val stageBarUnselectBackgroundColor: Color,

    //Buttons
    val commonButtonBackground: Color,
    val commonButtonTextColor: Color,

    //Schedule Frame
    val selectScheduleArrowColor: Color,
    val selectScheduleTitleColor: Brush,
    val selectTextColor: Color,
    val sheetBackground: Color,
    val institutionBackgroundColor: Color,

    //Divider
    val divider: Color,

    val transparentBackground: Color,
    val btnGroupTeacherText: Color,
    val btnDoneText: Color,
    val bottomDialogBackItemColor: Color,
    val titleText: Color,
    val secondText: Color,
    val simpleText: Color,
    val moreScreenIconsTint: Color,
    val bottomNavMenuColors: BottomNavMenuColors,
    val toolbarGradient: Brush,
    val bottomNavBarGradient: Brush,
    val backgroundBrush: Brush,
    val themeChangeButton: Color,
    val bottomSheetView: Color,
    val nameItemBackground: Color,

    val backgroundTurtle: Color,

    //Calls colors
    val callTypeColor: Color,
    val numberBackground: Color,
    val callTimeColor: Color,

    //text color
    val textColor: Color,

    //items background
    val dateBackground: Color,
    val baseItemBackground: Color,
    val doctrineBackground: Color,
    val pairInfo: Color,

    val turtleImageBackground: Color,
    val buttonSelectBackground: Color,
    val buttonSelectTurtle: Color,
    val buttonNextBackground: Brush
)

val darkColors = Colors(

    stageBarSelectTextColor = Color(0xFFFFFFFF),
    stageBarSelectBackgroundColor = Color(0xFF417B65),
    stageBarUnselectTextColor = Color(0xFFD9D9D9),
    stageBarUnselectBackgroundColor = Color(0xFFFFFFFF), //TODO change for dark theme

    commonButtonBackground = Color(0xFF417B65),
    commonButtonTextColor = Color(0xFFFFFFFF), //TODO change for dark theme

    //Schedule Frame
    selectScheduleTitleColor = Brush.horizontalGradient(
        listOf(Color(0xFF2DA06D), Color(0xFF93D062).copy(0.9f))
    ),
    selectScheduleArrowColor = Color(0xFF2DA06D),
    selectTextColor = Color(0xFF9E9C9F),
    sheetBackground = Color(0xFFF0F6E7),
    institutionBackgroundColor = Color(0xFFF3F6EE),

    //Divider
    divider = Color(0xFF9E9C9F),

    transparentBackground = Color(0xD9464F6B),
    btnGroupTeacherText = Color(0xFF8D91D1),
    btnDoneText = Color(0xFF8D91D1),
    bottomDialogBackItemColor = Color(0xBF575756),
    titleText = Color(0xFF8D91D1),
    secondText = Color(0xFF8D91D1),
    simpleText = Color(0xFF8D91D1),
    moreScreenIconsTint = Color(0xFF8D91D1),
    bottomNavMenuColors = BottomNavMenuColors.NightColors,
    toolbarGradient = Brush.horizontalGradient(
        listOf(
            Color(0xFF112240),
            Color(0xFF112240),
        )
    ),
    bottomNavBarGradient = Brush.linearGradient(
        listOf(Color(0xFF112240), Color(0xFF112240))
    ),
    backgroundBrush = Brush.horizontalGradient(listOf(Color(0xFF0A192F), Color(0xFF0A192F))),
    themeChangeButton = Color(0xFF8D91D1),
    bottomSheetView = Color(0xFF8D91D1),
    nameItemBackground = Color(0xD93D4762),
    callTypeColor = Color.White,
    numberBackground = Color(0xFF8D91D1),
    callTimeColor = Color(0xFFCCD6F6),

    textColor = Color(0xFFCCD6F6),

    dateBackground = Color(0xFF3D4762),
    baseItemBackground = Color(0xD93D4762),
    doctrineBackground = Color(0x59112240),
    pairInfo = Color(0xFFCFCFCF),

    turtleImageBackground = Color(0xFF3D4762),
    buttonSelectBackground = Color(0x590A192F),
    buttonSelectTurtle = Color(0xFF313A55),
    buttonNextBackground = Brush.horizontalGradient(listOf(Color(0xFF0A192F), Color(0xFF0A192F))),
    backgroundTurtle = Color(0xD9464F6B)
)

val colors = Colors(

    stageBarSelectTextColor = Color(0xFFFFFFFF),
    stageBarSelectBackgroundColor = Color(0xFF417B65),
    stageBarUnselectTextColor = Color(0xFFD9D9D9),
    stageBarUnselectBackgroundColor = Color(0xFFFFFFFF),//TODO change for dark theme

    //Schedule Frame
    selectScheduleTitleColor = Brush.horizontalGradient(
        listOf(Color(0xFF2DA06D), Color(0xFF93D062).copy(0.9f))
    ),
    selectScheduleArrowColor = Color(0xFF2DA06D),//TODO change for dark theme
    selectTextColor = Color(0xFF9E9C9F),
    sheetBackground = Color(0xFFF0F6E7),
    institutionBackgroundColor = Color(0xFFF3F6EE),

    //Divider
    divider = Color(0xFF9E9C9F),

    commonButtonBackground = Color(0xFF417B65),
    commonButtonTextColor = Color(0xFFFFFFFF),

    transparentBackground = Color(0xFFECF4E4),
    btnGroupTeacherText = Color.Black,
    btnDoneText = Color.White,
    bottomDialogBackItemColor = Color.White,
    titleText = Color(0xFF96D162),
    secondText = Color(0xFF417B65),
    simpleText = Color.Gray,
    moreScreenIconsTint = Color.Black,
    bottomNavMenuColors = BottomNavMenuColors.DayColors,
    toolbarGradient = Brush.horizontalGradient(listOf(Color(0xFF417B65), Color(0xFFA7CE7B))),
    bottomNavBarGradient = Brush.linearGradient(
        listOf(Color(0xFF86C8A7), Color(0xFFB3E3AE))
    ),
    backgroundBrush = Brush.verticalGradient(
        listOf(Color(0xFFFCFDD7), Color(0xFFB5E7AB)),
    ),
    themeChangeButton = Color.White,
    bottomSheetView = Color(0xFF15956F),
    nameItemBackground = Color(0xFFFFFFFF),

    callTypeColor = Color(0xFFA7CE7B),
    numberBackground = Color(0xFF417B65),
    callTimeColor = Color(0xFF9E9C9F),

    textColor = Color(0xFF417B65),

    dateBackground = Color(0xFFF2F6E8),
    baseItemBackground = Color(0xFFEEF5E5),
    doctrineBackground = Color(0x3BA7CE7B),
    pairInfo = Color(0xFF9E9C9F),

    turtleImageBackground = Color.White,
    buttonSelectBackground = Color.White,
    buttonSelectTurtle = Color(0xFFE8F0DF),
    buttonNextBackground = Brush.horizontalGradient(listOf(Color(0xFF417B65), Color(0xFFA7CE7B))),
    backgroundTurtle = Color(0x47417B65)
)


sealed class BottomNavMenuColors(
    private val isCheckedTrue: Color,
    private val isCheckedFalse: Color,
) {
    fun getColor(isChecked: Boolean): Color = if (isChecked) isCheckedTrue else isCheckedFalse

    object NightColors : BottomNavMenuColors(
        isCheckedTrue = Color(0xFF8D91D1), isCheckedFalse = Color(0xFF9E9C9F)
    )

    object DayColors : BottomNavMenuColors(
        isCheckedTrue = Color(0xFFFFFFFF), isCheckedFalse = Color(0xFF575756)
    )
}
