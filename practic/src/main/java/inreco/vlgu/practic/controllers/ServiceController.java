package inreco.vlgu.practic.controllers;



import inreco.vlgu.practic.dto.MessageResponse;
import inreco.vlgu.practic.dto.request.ServiceListResponse;
import inreco.vlgu.practic.dto.service.ServiceCreateRequest;
import inreco.vlgu.practic.dto.service.ServiceRequestID;
import inreco.vlgu.practic.dto.service.ServiceTypeList;
import inreco.vlgu.practic.dto.service.ServiceUpdateRequest;
import inreco.vlgu.practic.service.ServiceListService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("Возвращает список всех услуг")
    public ResponseEntity<ServiceListResponse> services() {
        return ResponseEntity.ok(new ServiceListResponse(serviceListService.getAllServices()));
    }

    @GetMapping("/available")
    @ApiOperation("Возвращает список всех доступных услуг")
    public ResponseEntity<ServiceListResponse> availableServices() {
        return ResponseEntity.ok(new ServiceListResponse(serviceListService.getAvailableServices()));
    }

    @GetMapping("/type")
    @ApiOperation("Возвращает список типов услуг")
    public ResponseEntity<ServiceTypeList> getTypes() {
        return ResponseEntity.ok(new ServiceTypeList(serviceListService.getServicesType()));
    }

    @PostMapping("/create")
    @ApiOperation("Создает новую услугу")
    public ResponseEntity<MessageResponse> createService(@Valid @RequestBody ServiceCreateRequest serviceCreateRequest) {
        if(serviceListService.createService(serviceCreateRequest.getName(),serviceCreateRequest.getDescription(),serviceCreateRequest.getPrice(),serviceCreateRequest.getType_id()))
          return ResponseEntity.ok(new MessageResponse("Service created successfully!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Something went wrong("));
    }

    @PostMapping("/update")
    @ApiOperation("Изменяет цену услуги")
    public ResponseEntity<MessageResponse> upService(@Valid @RequestBody ServiceUpdateRequest serviceUpdateRequest) {
        if(serviceListService.updateService(serviceUpdateRequest.getId(), serviceUpdateRequest.getPrice()))
          return ResponseEntity.ok(new MessageResponse("Service updated successfully!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Something went wrong("));
    }

    @PostMapping("/delete")
    @ApiOperation("Помечает улсугу как удаленную/недоступную")
    public ResponseEntity<MessageResponse> deleteService(@Valid @RequestBody ServiceRequestID serviceRequestID) {
        if(serviceListService.deleteService(serviceRequestID.getId()))
            return ResponseEntity.ok(new MessageResponse("Service marked as deleted!"));
        else
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Something went wrong("));
    }
}
