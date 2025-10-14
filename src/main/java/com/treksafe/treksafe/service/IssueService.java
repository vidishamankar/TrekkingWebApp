package com.treksafe.treksafe.service;

import com.treksafe.treksafe.model.Issue;
import com.treksafe.treksafe.repository.IssueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class IssueService {

    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public Optional<Issue> getIssueById(Long id) {
        return issueRepository.findById(id);
    }

    @Transactional
    public Issue createIssue(Issue issue) {
        issue.setCreatedAt(LocalDateTime.now());
        issue.setUpdatedAt(LocalDateTime.now());
        if (issue.getStatus() == null) issue.setStatus("OPEN");
        if (issue.getPriority() == null) issue.setPriority("MEDIUM");
        return issueRepository.save(issue);
    }

    @Transactional
    public Optional<Issue> updateIssue(Long id, Issue updated) {
        return issueRepository.findById(id).map(issue -> {
            if (updated.getTitle() != null) issue.setTitle(updated.getTitle());
            if (updated.getDescription() != null) issue.setDescription(updated.getDescription());
            if (updated.getReporterName() != null) issue.setReporterName(updated.getReporterName());
            if (updated.getStatus() != null) issue.setStatus(updated.getStatus());
            if (updated.getPriority() != null) issue.setPriority(updated.getPriority());
            issue.setUpdatedAt(LocalDateTime.now());
            return issueRepository.save(issue);
        });
    }

    @Transactional
    public boolean deleteIssue(Long id) {
        return issueRepository.findById(id).map(issue -> {
            issueRepository.delete(issue);
            return true;
        }).orElse(false);
    }

    public List<Issue> findByStatus(String status) {
        return issueRepository.findByStatus(status);
    }

    public List<Issue> findByPriority(String priority) {
        return issueRepository.findByPriority(priority);
    }

    public List<Issue> findByReporter(String reporterName) {
        return issueRepository.findByReporterName(reporterName);
    }
}
