package me.black9p.issueservice.domain

import jakarta.persistence.*
import me.black9p.issueservice.domain.enumeration.IssuePriority
import me.black9p.issueservice.domain.enumeration.IssueStatus
import me.black9p.issueservice.domain.enumeration.IssueType

@Entity
@Table
class Issue (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    var userId: Long,

    @OneToMany(fetch = FetchType.EAGER)
    val comments : MutableList<Comment> = mutableListOf(),

    @Column
    var summary: String,

    @Column
    var description: String,

    @Column
    @Enumerated(EnumType.STRING)
    var type: IssueType,

    @Column
    @Enumerated(EnumType.STRING)
    var priority: IssuePriority,

    @Column
    @Enumerated(EnumType.STRING)
    var status: IssueStatus,

    ) : BaseEntity()
