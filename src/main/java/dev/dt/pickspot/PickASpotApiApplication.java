package dev.dt.pickspot;

import dev.dt.pickspot.dto.PickRequest;
import dev.dt.pickspot.dto.PickResponse;
import dev.dt.pickspot.service.PickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1")
public class PickASpotApiApplication {

	@Autowired
	private PickerService service;
	public static void main(String[] args) {
		SpringApplication.run(PickASpotApiApplication.class, args);
	}

	@PostMapping("/pickSpot")
	public ResponseEntity<?> pick(@RequestBody PickRequest request) {
		return service.chooseBestSlot(request.container(), request.yardMap())
				.<ResponseEntity<?>>map(s -> ResponseEntity.ok(new PickResponse(request.container().id(), s.x(), s.y())))
				.orElseGet(() -> ResponseEntity.badRequest().body(Map.of("error", "no suitable slot")));
	}

}
