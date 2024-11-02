package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.ConcurrentModel;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cinema.dto.FileDto;
import ru.job4j.cinema.service.file.FileService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FileControllerTest {
/*
    private FileService fileService;

    private FileController fileController;

    private MultipartFile testFile;

    @BeforeEach
    public void initServices() {
        fileService = mock(FileService.class);
        fileController = mock(FileController.class);
        testFile = new MockMultipartFile("testFile.img", new byte[] {1, 2, 3});
    }

    @Test
    void whenRequestFileByIdThenGetStatusOK() throws Exception {
        var fileDto = new FileDto(testFile.getOriginalFilename(), testFile.getBytes());
        when(fileService.findById(any(Integer.class))).thenReturn(Optional.of(fileDto));
        var expected = ResponseEntity.ok(fileDto.getContent());
        assertThat(fileController.getById(1)).isEqualTo(expected);
    }

    @Test
    public void whenRequestFileContentByIdThenGetHttpStatusNotFound() {
        when(fileService.findById(any(Integer.class))).thenReturn(Optional.empty());
        var expected = fileController.getById(any(Integer.class));
        assertThat(expected.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }*/
}