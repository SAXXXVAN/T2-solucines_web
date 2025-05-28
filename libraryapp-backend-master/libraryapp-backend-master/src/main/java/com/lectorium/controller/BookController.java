package com.lectorium.controller;

import com.lectorium.dto.BookDTO;
import com.lectorium.model.Book;
import com.lectorium.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BookController {

    private final IBookService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll() throws Exception {
        List<BookDTO> list = service.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable("id") Integer id) throws Exception {
        BookDTO dto = convertToDto(service.findById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody BookDTO dto) throws Exception {
        Book obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getIdBook())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable("id") Integer id, @RequestBody BookDTO dto) throws Exception {
        Book obj = service.update(convertToEntity(dto), id);
        BookDTO dtoUpdated = convertToDto(obj);
        return ResponseEntity.ok(dtoUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<BookDTO> findByIdHateoas(@PathVariable("id") Integer id) throws Exception {
        Book obj = service.findById(id);
        BookDTO dto = convertToDto(obj);

        EntityModel<BookDTO> resource = EntityModel.of(dto);
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());

        resource.add(link1.withRel("book-self-info"));
        resource.add(link2.withRel("book-all-info"));

        return resource;
    }

    private BookDTO convertToDto(Book obj) {
        return modelMapper.map(obj, BookDTO.class);
    }

    private Book convertToEntity(BookDTO dto) {
        return modelMapper.map(dto, Book.class);
    }
}
