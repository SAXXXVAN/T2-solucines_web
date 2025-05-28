package com.lectorium.controller;

import java.net.URI;
import java.util.List;

import com.lectorium.dto.PublisherDTO;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lectorium.model.Publisher;
import com.lectorium.service.IPublisherService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// https://localhost:9090/publishers
@RestController
@RequestMapping("/publishers")
//@AllArgsConstructor
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PublisherController {
	//@Autowired
	private final IPublisherService service;
	private final ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<List<PublisherDTO>> findAll() throws Exception{
		// ModelMapper modelMapper = new ModelMapper();
		//return service.findAll();
		//List<PublisherDTO> list = service.findAll().stream().map(e -> new PublisherDTO(e.getIdPublisher(), e.getName(), e.getAddress())).toList();
		//List<PublisherDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, PublisherDTO.class)).toList();

		List<PublisherDTO> list = service.findAll().stream().map(this::converToDto).toList();

		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PublisherDTO> findById(@PathVariable("id") Integer id) throws Exception{
		//Publisher obj =  service.findById(id);
		//PublisherDTO obj = modelMapper.map(service.findById(id), PublisherDTO.class);
		PublisherDTO obj = converToDto(service.findById(id));
		return ResponseEntity.ok(obj);
	}

	@PostMapping
	public ResponseEntity<Void> save(@RequestBody PublisherDTO dto) throws Exception{
		//Publisher obj =  service.save(publisher);
		//Publisher obj = service.save(modelMapper.map(dto, Publisher.class));
		Publisher obj = service.save(convertToEntity(dto));
		//return ResponseEntity.ok(obj);

		// location: http://localhost:9090/publishers/{id}
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getIdPublisher()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<PublisherDTO> update(@PathVariable("id") Integer id, @RequestBody PublisherDTO dto) throws Exception{
		//Publisher obj =  service.update(modelMapper.map(dto,Publisher.class), id);
		//PublisherDTO dto1 = modelMapper.map(obj, PublisherDTO.class);
		//return ResponseEntity.ok(dto1);

		Publisher obj = service.update(convertToEntity(dto),id);
		PublisherDTO dto1= converToDto(obj);
		return ResponseEntity.ok(dto1);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id)
			throws Exception{
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("hateoas/{id}")
	public EntityModel<PublisherDTO> findByIdHateoas(@PathVariable("id") Integer id) throws Exception{
		Publisher obj = service.findById(id);
		EntityModel<PublisherDTO> resource = EntityModel.of(converToDto(obj));

		// Generar links informativos
		// localhost:9090/publishers/5
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
		WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findAll());
		resource.add(link1.withRel("publisher-self-info"));
		resource.add(link2.withRel("publisher-all-info"));

		return resource;
	}

	private PublisherDTO converToDto(Publisher obj){
		return modelMapper.map(obj, PublisherDTO.class);
	}

	private Publisher convertToEntity(PublisherDTO dto){
		return modelMapper.map(dto, Publisher.class);
	}

	
}
