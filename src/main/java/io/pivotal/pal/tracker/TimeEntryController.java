package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {
    private final TimeEntryRepository timeEntryRepsitory;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepsitory = timeEntryRepository;

    }
    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

        return new ResponseEntity(timeEntryRepsitory.create(timeEntryToCreate), HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId) {

        TimeEntry timeEntry = timeEntryRepsitory.find(timeEntryId);
        if(timeEntry == null){
            return new ResponseEntity( HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(timeEntry, HttpStatus.OK);
    }
    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {

        return new ResponseEntity(timeEntryRepsitory.list(), HttpStatus.OK);
    }
    @PutMapping("/time-entries/{id}")
    public ResponseEntity update( @PathVariable("id") long timeEntryId, @RequestBody TimeEntry timeEntry) {
        TimeEntry entry = timeEntryRepsitory.update(timeEntryId, timeEntry);
        if (entry == null) {
           return new ResponseEntity(entry, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(entry, HttpStatus.OK);
    }
    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable("id") long timeEntryId) {

        timeEntryRepsitory.delete(timeEntryId);
        return new ResponseEntity( HttpStatus.NO_CONTENT);
    }
}
