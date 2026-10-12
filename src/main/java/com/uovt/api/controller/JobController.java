
package com.uovt.api.controller;

import com.uovt.api.model.Job;
import com.uovt.api.repository.JobsRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "http://localhost:3000")
public class JobController {

    private final JobsRepository jobRepository;

    public JobController(JobsRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // GET: All jobs
    @GetMapping
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // GET: One job
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Integer id) {
        return jobRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Create job
    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {

        Job savedJob = jobRepository.save(job);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedJob);
    }

    // PUT: Update job
    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(
            @PathVariable Integer id,
            @RequestBody Job job) {

        return jobRepository.findById(id)
                .map(existingJob -> {
                    existingJob.setTitle(job.getTitle());
                    existingJob.setCompany(job.getCompany());
                    existingJob.setCity(job.getCity());
                    existingJob.setSalary(job.getSalary());
                    existingJob.setJob_type(job.getJob_type());

                    Job updatedJob = jobRepository.save(existingJob);

                    return ResponseEntity.ok(updatedJob);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE: Remove job
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Integer id) {
        if (!jobRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        jobRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
