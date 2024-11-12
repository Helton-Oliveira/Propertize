package com.digisphere.propertize.adapter.controller;

import com.digisphere.propertize.adapter.adapterPattern.PropertyAdapter.IPropertyAdapter;
import com.digisphere.propertize.adapter.dtos.property.CreatePropertyDto;
import com.digisphere.propertize.adapter.dtos.property.OutputProperty;
import com.digisphere.propertize.adapter.dtos.property.PropertyReferenceDto;
import com.digisphere.propertize.application.property.useCase.interfaces.IGetAllProperties;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {
    private final IPropertyAdapter propertyAdapter;
    private final IGetAllProperties getAllProperties;

    public PropertyController(IPropertyAdapter propertyAdapter, IGetAllProperties getAllProperties) {
        this.propertyAdapter = propertyAdapter;
        this.getAllProperties = getAllProperties;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<OutputProperty> createProperty(@RequestBody @Valid CreatePropertyDto dto, UriComponentsBuilder uriBuilder) {
       OutputProperty outputProperty = propertyAdapter.adaptCreatePropertyRequest(dto);
       var uri = uriBuilder.path("/properties/{id}").buildAndExpand(outputProperty.id()).toUri();

       return ResponseEntity.created(uri).body(outputProperty);
    }

    @GetMapping
    public ResponseEntity<List<OutputProperty>> getAllProperties() {
        List<OutputProperty> outputProperties = getAllProperties.execute().stream()
                .map(OutputProperty::new)
                .toList();

        return ResponseEntity.ok(outputProperties);
    }

    @GetMapping("/one")
    public ResponseEntity<OutputProperty> getOneProperty(@RequestBody @Valid PropertyReferenceDto dto) {
        OutputProperty outputProperty = propertyAdapter.adaptFindPropertyRequest(dto);

        return ResponseEntity.ok(outputProperty);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> updateFieldProperty(@RequestBody @Valid PropertyReferenceDto dto) {
        var response = propertyAdapter.adaptUpdatePropertyRequest(dto);

        return ResponseEntity.ok(response);
    }

}
