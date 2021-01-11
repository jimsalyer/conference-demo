package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    @GetMapping("{id}")
    public Session get(@PathVariable Long id) {
        if (sessionRepository.existsById(id)) {
            return sessionRepository.getOne(id);
        } else {
            String message = String.format("Session with sessionId %d not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Session create(@RequestBody final Session session) {
        return sessionRepository.saveAndFlush(session);
    }

    @PutMapping("{id}")
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        if (sessionRepository.existsById(id)) {
            Session existingSession = sessionRepository.getOne(id);
            BeanUtils.copyProperties(session, existingSession, "session_id");
            return sessionRepository.saveAndFlush(existingSession);
        } else {
            String message = String.format("Session with sessionId %d not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        }
    }

    @DeleteMapping("{id}")
    public Session delete(@PathVariable Long id) {
        if (sessionRepository.existsById(id)) {
            Session session = sessionRepository.getOne(id);
            sessionRepository.delete(session);
            return session;
        } else {
            String message = String.format("Session with sessionId %d not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        }
    }
}
