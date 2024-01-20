package com.simplon.labxpert.controller;

import com.simplon.labxpert.model.dto.SampleDTO;
import com.simplon.labxpert.service.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/samples")
public class SampleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);
    private SampleService sampleService;

    @Autowired
    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }


    @GetMapping
    public ResponseEntity<List<SampleDTO>> getAllSamples() {
        try {
            LOGGER.info("Fetching all simples");
            List<SampleDTO> sampleDTOS = sampleService.getAllSimple();
            return new ResponseEntity<>(sampleDTOS, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("cannot get all simples an error has occurred ");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<SampleDTO> createSample(@Valid @RequestBody SampleDTO sampleDTO) {
        LOGGER.info("the sampleDTO" + sampleDTO);
        SampleDTO createdSample = sampleService.createSample(sampleDTO);
        LOGGER.info("sample added successfully");
        return new ResponseEntity<>(createdSample, HttpStatus.OK);
    }

    @GetMapping("/{sampleId}")
    public ResponseEntity<SampleDTO> getSampleByID(@PathVariable long sampleId) {
        try {
            SampleDTO sample = sampleService.getSampleById(sampleId);
            LOGGER.info("sample has been fetched successfully ");
            return new ResponseEntity<>(sample, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            LOGGER.error("sample not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            LOGGER.error("a problem has occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @DeleteMapping("/{sampleId}")
    public ResponseEntity<Void> deleteSample(@PathVariable long sampleId) {
        try {
            sampleService.deleteSample(sampleId);
            LOGGER.info("sample has been deleted successfully ");
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            LOGGER.error("sample not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            LOGGER.error("a problem has occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
