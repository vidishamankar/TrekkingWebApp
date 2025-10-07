package com.treksafe.treksafe.controller;

import com.treksafe.treksafe.model.Issue;
import com.treksafe.treksafe.service.IssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public List<Issue> getAllIssues() {
        return issueService.getAllIssues();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Issue> getById(@PathVariable Long id) {
        return issueService.getIssueById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Issue> create(@RequestBody Issue issue) {
        Issue created = issueService.createIssue(issue);
        return ResponseEntity.created(URI.create("/api/issues/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Issue> update(@PathVariable Long id, @RequestBody Issue issue) {
        return issueService.updateIssue(id, issue)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = issueService.deleteIssue(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/status/{status}")
    public List<Issue> byStatus(@PathVariable String status) {
        return issueService.findByStatus(status);
    }

    @GetMapping("/priority/{priority}")
    public List<Issue> byPriority(@PathVariable String priority) {
        return issueService.findByPriority(priority);
    }

    @GetMapping("/reporter/{name}")
    public List<Issue> byReporter(@PathVariable String name) {
        return issueService.findByReporter(name);
    }
}
