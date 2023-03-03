package me.black9p.issueservice.domain.enumeration

enum class IssueType {
    BUG,
    TASK,
    ;

    companion object {
        operator fun invoke(type: String) = valueOf(type.uppercase())
    }
}
