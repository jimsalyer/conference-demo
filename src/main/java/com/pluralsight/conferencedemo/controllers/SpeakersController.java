package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {
    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public List<Speaker> list() {
        return speakerRepository.findAll();
    }

    @GetMapping("{id}")
    public Speaker get(@PathVariable Long id) {
        if (speakerRepository.existsById(id)) {
            return speakerRepository.getOne(id);
        } else {
            String message = String.format("Speaker with speakerId %d not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Speaker create(@RequestBody Speaker speaker) {
        return speakerRepository.saveAndFlush(speaker);
    }

    @PutMapping("{id}")
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
        if (speakerRepository.existsById(id)) {
            Speaker existingSpeaker = speakerRepository.getOne(id);
            BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
            return speakerRepository.saveAndFlush(existingSpeaker);
        } else {
            String message = String.format("Speaker with speakerId %d not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        }
    }

    @DeleteMapping("{id}")
    public Speaker delete(@PathVariable Long id) {
        if (speakerRepository.existsById(id)) {
            Speaker speaker = speakerRepository.getOne(id);
            speakerRepository.delete(speaker);
            return speaker;
        } else {
            String message = String.format("Speaker with speakerId %d not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        }
    }
}
