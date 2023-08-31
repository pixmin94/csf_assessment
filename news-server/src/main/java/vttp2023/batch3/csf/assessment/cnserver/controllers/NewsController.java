package vttp2023.batch3.csf.assessment.cnserver.controllers;

import java.util.List;

// import org.bson.json.JsonObject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vttp2023.batch3.csf.assessment.cnserver.Utils;
import vttp2023.batch3.csf.assessment.cnserver.models.News;
import vttp2023.batch3.csf.assessment.cnserver.models.TagCount;
import vttp2023.batch3.csf.assessment.cnserver.repositories.NewsRepository;

@RestController
@RequestMapping(path="/api")
@CrossOrigin
public class NewsController {
	@Autowired
	private NewsRepository newsRepo;

	@PostMapping(path="/postnews")
	public ResponseEntity<ObjectId> postNews(@RequestBody String news) {
		News newsObj = Utils.toNewsObject(news);
		ObjectId newsId = newsRepo.postNews(newsObj);
		return ResponseEntity
				.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(newsId);
	}


	@GetMapping(path="/getnews")
	public ResponseEntity<List<TagCount>> getNews() { //@RequestParam Integer duration
		List<TagCount> tagsList = newsRepo.getTags();
		return ResponseEntity
			.status(HttpStatus.OK)
			.contentType(MediaType.APPLICATION_JSON)
			.body(tagsList);
	}


	// TODO: Task 3

}
