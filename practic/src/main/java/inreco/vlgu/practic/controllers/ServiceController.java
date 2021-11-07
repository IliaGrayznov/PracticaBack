package inreco.vlgu.practic.controllers;



import inreco.vlgu.practic.dto.MessageResponse;
import inreco.vlgu.practic.dto.request.ServiceListResponse;
import inreco.vlgu.practic.dto.service.ServiceCreateRequest;
import inreco.vlgu.practic.dto.service.ServiceRequestID;
import inreco.vlgu.practic.dto.service.ServiceUpdateRequest;
import inreco.vlgu.practic.service.ServiceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/service")
public class ServiceController {
    @Autowired
    ServiceListService serviceListService;

    @GetMapping("/all")
    public ResponseEntity<?> services() {
        return ResponseEntity.ok(new ServiceListResponse(serviceListService.getAllServices()));
    }

    @GetMapping("/available")
    public ResponseEntity<?> availableServices() {
        return ResponseEntity.ok(new ServiceListResponse(serviceListService.getAvailableServices()));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createService(@Valid @RequestBody ServiceCreateRequest serviceCreateRequest) {
        if(serviceListService.createService(serviceCreateRequest.getName(),serviceCreateRequest.getDescription(),serviceCreateRequest.getPrice(),serviceCreateRequest.getType_id()))
          return ResponseEntity.ok(new MessageResponse("Service created successfully!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Something went wrong("));
    }

    @PostMapping("/update")
    public ResponseEntity<?> upService(@Valid @RequestBody ServiceUpdateRequest serviceUpdateRequest) {
        if(serviceListService.updateService(serviceUpdateRequest.getId(), serviceUpdateRequest.getPrice()))
          return ResponseEntity.ok(new MessageResponse("Service updated successfully!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Something went wrong("));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteService(@Valid @RequestBody ServiceRequestID serviceRequestID) {
        if(serviceListService.deleteService(serviceRequestID.getId()))
            return ResponseEntity.ok(new MessageResponse("Service marked as deleted!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Something went wrong("));
    }
}
