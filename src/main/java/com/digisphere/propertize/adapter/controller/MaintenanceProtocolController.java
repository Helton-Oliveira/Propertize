package com.digisphere.propertize.adapter.controller;

import com.digisphere.propertize.adapter.adapterPattern.maintenanceProtocolAdapter.IMaintenanceProtocolRequestAdapter;
import com.digisphere.propertize.adapter.dtos.maintenanceProtocol.*;
import com.digisphere.propertize.application.maintenance.useCase.interaces.IPickUpOpenMaintenanceProtocols;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/maintenanceProtocols")
public class MaintenanceProtocolController {

    private final IMaintenanceProtocolRequestAdapter adapter;
    private final IPickUpOpenMaintenanceProtocols getProtocols;

    public MaintenanceProtocolController(IMaintenanceProtocolRequestAdapter adapter, IPickUpOpenMaintenanceProtocols getProtocols) {
        this.adapter = adapter;
        this.getProtocols = getProtocols;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<OutputMaintenanceProtocol> openProtocol(@RequestBody @Valid CreateMaintenanceProtocolDto req, UriComponentsBuilder uriBuilder) {
        OutputMaintenanceProtocol protocol = adapter.adaptCreateMaintenanceProtocolRequest(req);
        var uri = uriBuilder.path("/maintenanceProtocols/{protocol}").buildAndExpand(protocol.protocol()).toUri();
        return ResponseEntity.created(uri).body(protocol);
    }

    @GetMapping
    public ResponseEntity<List<OutputMaintenanceProtocol>> getAllOpenProtocols() {
        List<OutputMaintenanceProtocol> protocols = getProtocols.execute().stream()
                .map(p -> new OutputMaintenanceProtocol(
                        p.getProtocol().toString(),
                        p.getRequestingTenantCpf(),
                        p.getPropertyIdForMaintenance().toString(),
                        p.getOpeningDate().toString(),
                        p.getMaintenanceDetails(),
                        p.getStatus().toString(),
                        p.getDateOfResolution() != null ? p.getDateOfResolution().toString() : null,
                        p.getReasonForCancellation()))
                .toList();

        return ResponseEntity.ok(protocols);
    }

    @GetMapping("/one")
    public ResponseEntity<OutputMaintenanceProtocol> getOneMaintenanceProtocol(@RequestBody @Valid MaintenanceProtocolIdReferenceDto req) {
        OutputMaintenanceProtocol protocol = adapter.adaptFindMaintenanceProtocolRequest(req);
        return ResponseEntity.ok(protocol);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> updateStatusProtocol(@RequestBody @Valid MaintenanceProtocolUpdateStatusDto req) {
        String response = adapter.adaptUpdateMaintenanceProtocolRequest(req);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<String> closeMaintenanceProtocol(@RequestBody @Valid ClosedMaintenanceProtocolDto req) {
        String response = adapter.adaptCloseMaintenanceProtocolRequest(req);
        return ResponseEntity.ok(response);
    }
}
