package com.studentinfo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {


private final StudentRepository repo;


public StudentController(StudentRepository repo) {
this.repo = repo;
}


// Create
@PostMapping
public ResponseEntity<Student> addStudent(@RequestBody Student student) {
return ResponseEntity.ok(repo.save(student));
}


// Read all
@GetMapping
public ResponseEntity<List<Student>> getAllStudents() {
return ResponseEntity.ok(repo.findAll());
}


// Read one
@GetMapping("/{id}")
public ResponseEntity<Student> getStudent(@PathVariable Integer id) {
return repo.findById(id)
.map(ResponseEntity::ok)
.orElse(ResponseEntity.notFound().build());
}


// Update
@PutMapping("/{id}")
public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @RequestBody Student updated) {
return repo.findById(id)
.map(existing -> {
existing.setName(updated.getName());
existing.setAge(updated.getAge());
existing.setCourse(updated.getCourse());
return ResponseEntity.ok(repo.save(existing));
})
.orElse(ResponseEntity.notFound().build());
}
}