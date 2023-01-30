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
            1 -> "강화도조약,개항"
            2 -> "임오군란,갑신정변"
            3 -> "동학농민운동,갑오개혁"
            4 -> "을미개혁,대한재국"
            else -> throw IllegalArgumentException("Dynasty Type Modern Detail Error")
        }
    }),
    JAPANESE("일제강점기", {
        when (it) {
            1 -> "국권피탈vs민족대항"
            2 -> "일제통치체제\n  &국내독립운동"
            3 -> "국외독립운동"
            else -> throw IllegalArgumentException("Dynasty Type Japanese Detail Error")
        }
    }),
    CONTEMPORARY("현대사",{
        when(it) {
            1 -> "광복 & 6.25 전쟁"
            2 -> "이승만~박정희"
            3 -> "정두환~노무현"
            else -> throw IllegalArgumentException("Dynasty Type Contemporary Detail Error")
        }
    });

    fun checkedModernAfter(): Boolean {
        return this == MODERN || this == JAPANESE || this == CONTEMPORARY
    }

    fun combineValue(num: Int): String {
        return "${this.value}\n${num}.${this.detail(num)}"
    }
}