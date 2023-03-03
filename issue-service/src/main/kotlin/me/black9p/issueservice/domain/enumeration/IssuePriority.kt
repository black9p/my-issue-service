package me.black9p.issueservice.domain.enumeration

enum class IssuePriority {
    LOW,
    MEDIUM,
    HIGH,
    ;

    companion object {
        operator fun invoke(priority: String) = valueOf(priority.uppercase())
    }
}
