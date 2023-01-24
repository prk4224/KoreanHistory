package com.jaehong.domain.local.model.enum_type

enum class DynastyType(
    val value: String,
    val detail: (Int) -> String,
) {
    SAM_GUG("삼국시대", { "" }),
    SIN_LA("통일신라 & 후삼국", { "" }),
    GO_LYEO("고려시대", { "" }),
    JO_SEON("조선시대", { "" }),
    MY_KEYWORD("나의 키워드 복습 노트", { "" }),
    MODERN("근현대사", {
        when (it) {
            1 -> ModernType.ONE.value
            2 -> ModernType.TWO.value
            3 -> ModernType.THREE.value
            4 -> ModernType.FOUR.value
            else -> ""
        }
    }),
    JAPANESE("일제강점기", {
        when (it) {
            1 -> JapaneseType.ONE.value
            2 -> JapaneseType.TWO.value
            3 -> JapaneseType.THREE.value
            else -> ""
        }
    }),
    CONTEMPORARY("현대사",{
        when(it) {
            1 -> ContemporaryType.ONE.value
            2 -> ContemporaryType.TWO.value
            3 -> ContemporaryType.THREE.value
            else -> ""
        }
    });

    fun checkedType(): Boolean {
        return this == MODERN || this == JAPANESE || this == CONTEMPORARY
    }
}