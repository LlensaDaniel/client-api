package com.client.api.controller;


import com.client.api.service.ClientService;
import com.client.api.dto.RequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class ClientController {

	@Autowired
	private ClientService service;

	@PostMapping(value = "/createcliente", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity createClient(@RequestBody RequestDto client) {
		 return service.createClient(client);
	}

	@GetMapping(value = "/kpideclientes", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity getKpiClients() {
		return service.getKpiClients();
	}

	@GetMapping(value = "/listclientes", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity getListClient() {
		return service.getListClient();
	}



}
