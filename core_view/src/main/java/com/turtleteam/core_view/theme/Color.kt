package com.turtleteam.core_view.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val LocalColors = compositionLocalOf<Colors> { error("colors wasnt provided") }

data class Colors(

    val primary: Color,
    val accent: Color,

    val blocks: Color,
    val stroke: Color,

    val topBar: Brush,

    val groupSheetTopBar: Color,

    // Text
    val textPrimary: Color,
    val textAccent: Color,

    // Button
    val commonButtonBackground: Color,
    val commonButtonTextColor: Color,

    val selectButtonBackground: Color,
    val selectButtonStrokeEnabled: Color,
    val selectButtonStrokeDisabled: Color,

    val selectGroupButtonBackground: Color,
    val selectGroupButtonStroke: Color,
    val selectGroupButtonArrow: Color,

    val additionalButtonBackground: Color,
    val additionalButtonStroke: Color,
    val additionalButtonArrow: Color,

    // Sheet
    val sheetBackground: Color,

    // Snackbar
    val snackBarBackground: Color,
    val snackBarText: Color,

    // Navigation Bar
    val systemNavigationBar: Color,

    // StageBar
    val stageBarSelectTextColor: Color,
    val stageBarSelectBackgroundColor: Color,
    val stageBarUnselectTextColor: Color,
    val stageBarUnselectBackgroundColor: Color,

    // Divider
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

    // Calls colors
    val callTypeColor: Color,
    val numberBackground: Color,
    val callTimeColor: Color,

    // items background
    val dateBackground: Color,
    val baseItemBackground: Color,
    val doctrineBackground: Color,
    val pairInfo: Color,

    val turtleImageBackground: Color,
    val buttonSelectBackground: Color,
    val buttonSelectTurtle: Color,
    val buttonNextBackground: Brush,
)

val colors = Colors(

    primary = Color(0xFF417B65),
    accent = Color(0xFF9E9C9F),
    blocks = Color(0x76F5F6F1),
    stroke = Color(0x35417B65),

    topBar = Brush.horizontalGradient(listOf(Color(0xFF488166), Color(0xFFA1C97A))),

    groupSheetTopBar = Color(0xFFFCFDD3),

    // Text
    textPrimary = Color(0xFF417B65),
    textAccent = Color(0xFF9E9C9F),

    // Button
    commonButtonBackground = Color(0xFF417B65),
    commonButtonTextColor = Color(0xFFFFFFFF),

    selectButtonBackground = Color(0x25FFFFFF),
    selectButtonStrokeEnabled = Color(0xFF417B65),
    selectButtonStrokeDisabled = Color(0x35417B65),

    selectGroupButtonBackground = Color(0x76F5F6F1),
    selectGroupButtonStroke = Color(0xFF417B65),
    selectGroupButtonArrow = Color(0xFF417B65),

    additionalButtonBackground = Color(0x76F5F6F1),
    additionalButtonStroke = Color(0x35417B65),
    additionalButtonArrow = Color(0xFF417B65),

    // Sheet
    sheetBackground = Color(0xFFF0F6E7),

    // Snackbar
    snackBarBackground = Color(0xFFFFFFFF),
    snackBarText = Color(0xFF417B65),

    // Divider
    divider = Color(0xFF9E9C9F),

    // Navigation Bar
    systemNavigationBar = Color(0xFF000000),

    // Stage Bar
    stageBarSelectTextColor = Color(0xFFFFFFFF),
    stageBarSelectBackgroundColor = Color(0xFF417B65),
    stageBarUnselectTextColor = Color(0xFFD9D9D9),
    stageBarUnselectBackgroundColor = Color(0xFFFFFFFF),

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
        listOf(Color(0xFF86C8A7), Color(0xFFB3E3AE)),
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

    dateBackground = Color(0xFFF2F6E8),
    baseItemBackground = Color(0xFFEEF5E5),
    doctrineBackground = Color(0x3BA7CE7B),
    pairInfo = Color(0xFF9E9C9F),

    turtleImageBackground = Color.White,
    buttonSelectBackground = Color.White,
    buttonSelectTurtle = Color(0xFFE8F0DF),
    buttonNextBackground = Brush.horizontalGradient(listOf(Color(0xFF417B65), Color(0xFFA7CE7B))),
    backgroundTurtle = Color(0x47417B65),
)

val darkColors = Colors(

    primary = Color(0xFFCCD6F6),
    accent = Color(0xFFCFCFCF),

    blocks = Color(0x85464F6B),
    stroke = Color(0x35CCD6F6),

    topBar = Brush.horizontalGradient(listOf(Color(0xFF112240), Color(0xFF112240))),

    groupSheetTopBar = Color(0xFF0A192F),

    // Text
    textPrimary = Color(0xFFCCD6F6),
    textAccent = Color(0xFFCFCFCF),

    // Button
    commonButtonBackground = Color(0xFF1D3256),
    commonButtonTextColor = Color(0xFFCCD6F6),

    selectButtonBackground = Color(0x35112240),
    selectButtonStrokeEnabled = Color(0xFFCCD6F6),
    selectButtonStrokeDisabled = Color(0x35CCD6F6),

    selectGroupButtonBackground = Color(0xFF112240),
    selectGroupButtonStroke = Color(0x35CCD6F6),
    selectGroupButtonArrow = Color(0xFFCCD6F6),

    additionalButtonBackground = Color(0x85464F6B),
    additionalButtonStroke = Color(0x35CCD6F6),
    additionalButtonArrow = Color(0xFFCCD6F6),

    // Sheet
    sheetBackground = Color(0xFF0A192F),

    // Snackbar
    snackBarBackground = Color(0xFF464F6B),
    snackBarText = Color(0xFFCCD6F6),

    // Divider
    divider = Color(0xFFCCD6F6),

    // Navigation Bar
    systemNavigationBar = Color(0xFF000000),

    // Stage Bar
    stageBarSelectTextColor = Color(0xFFFFFFFF),
    stageBarSelectBackgroundColor = Color(0xFF8D91D1),
    stageBarUnselectTextColor = Color(0xFFCFCFCF),
    stageBarUnselectBackgroundColor = Color(0x85464F6B),

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
        ),
    ),
    bottomNavBarGradient = Brush.linearGradient(
        listOf(Color(0xFF112240), Color(0xFF112240)),
    ),
    backgroundBrush = Brush.horizontalGradient(listOf(Color(0xFF0A192F), Color(0xFF0A192F))),
    themeChangeButton = Color(0xFF8D91D1),
    bottomSheetView = Color(0xFF8D91D1),
    nameItemBackground = Color(0xD93D4762),
    callTypeColor = Color.White,
    numberBackground = Color(0xFF8D91D1),
    callTimeColor = Color(0xFFCCD6F6),

    dateBackground = Color(0xFF3D4762),
    baseItemBackground = Color(0xD93D4762),
    doctrineBackground = Color(0x59112240),
    pairInfo = Color(0xFFCFCFCF),

    turtleImageBackground = Color(0xFF3D4762),
    buttonSelectBackground = Color(0x590A192F),
    buttonSelectTurtle = Color(0xFF313A55),
    buttonNextBackground = Brush.horizontalGradient(listOf(Color(0xFF0A192F), Color(0xFF0A192F))),
    backgroundTurtle = Color(0xD9464F6B),
)

sealed class BottomNavMenuColors(
    private val isCheckedTrue: Color,
    private val isCheckedFalse: Color,
) {
    fun getColor(isChecked: Boolean): Color = if (isChecked) isCheckedTrue else isCheckedFalse

    object NightColors : BottomNavMenuColors(
        isCheckedTrue = Color(0xFF8D91D1),
        isCheckedFalse = Color(0xFF9E9C9F),
    )

    object DayColors : BottomNavMenuColors(
        isCheckedTrue = Color(0xFFFFFFFF),
        isCheckedFalse = Color(0xFF575756),
    )
}
