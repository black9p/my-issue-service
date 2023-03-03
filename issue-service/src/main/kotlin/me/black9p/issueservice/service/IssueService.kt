package me.black9p.issueservice.service

import jakarta.transaction.Transactional
import me.black9p.issueservice.domain.Issue
import me.black9p.issueservice.domain.IssueRepository
import me.black9p.issueservice.domain.enumeration.IssueStatus
import me.black9p.issueservice.exception.NotFoundException
import me.black9p.issueservice.model.IssueRequest
import me.black9p.issueservice.model.IssueResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class IssueService(
    private val issueRepository: IssueRepository,
) {

    @Transactional
    fun create(userId: Long, request: IssueRequest): IssueResponse {
        val issue = Issue(
            summary = request.summary,
            description = request.description,
            userId = userId,
            type = request.type,
            priority = request.priority,
            status = request.status,
        )
        return IssueResponse(issueRepository.save(issue))
    }

    fun getAll(status: IssueStatus) =
        issueRepository.findAllByStatusOrderByCreatedAtDesc(status)
            ?.map { IssueResponse(it) }

    fun get(id: Long): IssueResponse {
        val issue = issueRepository.findByIdOrNull(id) ?: throw NotFoundException("Issue not exists")
        return IssueResponse(issue)
    }

    @Transactional
    fun edit(userId: Long, id: Long, request: IssueRequest): Any {
        val issue = issueRepository.findByIdOrNull(id) ?: throw NotFoundException("Issue not exists")

        return with(issue) {
            summary = request.summary
            description = request.description
            this.userId = userId
            priority = request.priority
            status = request.status

            IssueResponse(issueRepository.save(this))
        }
    }

    fun delete(id: Long) {
        issueRepository.deleteById(id)

    }
}