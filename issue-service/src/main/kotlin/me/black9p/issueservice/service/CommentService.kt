package me.black9p.issueservice.service

import jakarta.transaction.Transactional
import me.black9p.issueservice.domain.Comment
import me.black9p.issueservice.domain.CommentRepository
import me.black9p.issueservice.domain.IssueRepository
import me.black9p.issueservice.exception.NotFoundException
import me.black9p.issueservice.model.CommentRequest
import me.black9p.issueservice.model.CommentResponse
import me.black9p.issueservice.model.toResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentService (
    private val commentRepository: CommentRepository,
    private val issueRepository: IssueRepository,
){

    @Transactional
    fun create(issueId: Long, userId: Long, username: String, request: CommentRequest) : CommentResponse {
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw NotFoundException("Issue not exists")

        val comment = Comment(
            issue = issue,
            userId = userId,
            username = username,
            body = request.body,
        )

        issue.comments.add(comment)
        return commentRepository.save(comment).toResponse()
    }

    fun edit(id: Long, userId: Long, request: CommentRequest): CommentResponse? {
        return commentRepository.findByIdAndUserId(id, userId)?.run {
            body = request.body
            commentRepository.save(this).toResponse()
        }
    }

    @Transactional
    fun delete(issueId: Long, id: Long, userId: Long) {
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw NotFoundException("Issue not exists")
        commentRepository.findByIdAndUserId(id, userId)?.let {
            issue.comments.remove(it)
        }
    }
}