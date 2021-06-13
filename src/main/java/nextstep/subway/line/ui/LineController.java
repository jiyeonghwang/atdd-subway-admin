package nextstep.subway.line.ui;

import nextstep.subway.line.application.LineService;
import nextstep.subway.line.dto.LineRequest;
import nextstep.subway.line.dto.LineResponse;
import nextstep.subway.section.dto.SectionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lines")
public class LineController {
	private final LineService lineService;

	public LineController(final LineService lineService) {
		this.lineService = lineService;
	}

	@PostMapping
	public ResponseEntity<LineResponse> createLine(@RequestBody LineRequest lineRequest) {
		LineResponse line = lineService.saveLine(lineRequest);
		return ResponseEntity.created(URI.create("/lines/" + line.getId())).body(line);
	}

	@GetMapping
	public ResponseEntity<List<LineResponse>> getLines() {
		return ResponseEntity.ok(lineService.getLines());
	}

	@GetMapping("/{lineId}")
	public ResponseEntity<LineResponse> getLineById(@PathVariable Long lineId) {
		return ResponseEntity.ok(LineResponse.of(lineService.findLineById(lineId)));
	}

	@PutMapping("/{lineId}")
	public ResponseEntity<Void> modifyLine(@PathVariable Long lineId, @RequestBody LineRequest lineRequest) {
		lineService.modifyLine(lineId, lineRequest);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{lineId}")
	public ResponseEntity<Void> deleteLine(@PathVariable Long lineId) {
		lineService.deleteLine(lineId);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{lineId}/sections")
	public ResponseEntity addSection(@PathVariable String lineId, @RequestBody SectionRequest sectionRequest) {
		return ResponseEntity.ok("");
	}
}
