package me.black9p.issueservice.domain.enumeration

enum class IssueStatus {
    TODO,
    IN_PROGRESS,
    RESOLVED,
    ;

    companion object {
        operator fun invoke(status: String) = valueOf(status.uppercase())
    }
}
