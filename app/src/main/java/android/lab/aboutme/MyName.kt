package android.lab.aboutme

/**
 * _data class_ para fazer o bind de dados. Como queremos apenas mudar o nome e
 * o apelido (nickname)
 */
data class MyName(
    var name: String = "",
    var nickname: String = ""
)