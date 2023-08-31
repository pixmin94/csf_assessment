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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vttp2023.batch3.csf.assessment.cnserver.Utils;
import vttp2023.batch3.csf.assessment.cnserver.models.News;
import vttp2023.batch3.csf.assessment.cnserver.models.TagCount;
import vttp2023.batch3.csf.assessment.cnserver.repositories.NewsRepository;
import vttp2023.batch3.csf.assessment.cnserver.services.NewsService;

@RestController
@RequestMapping(path="/api")
@CrossOrigin
public class NewsController {
	@Autowired
	private NewsService service;

	@PostMapping(path="/postnews")
	public ResponseEntity<String> postNews(@RequestBody String news) {
		News newsObj = Utils.toNewsObject(news);
		String newsId = service.postNews(newsObj);
		return ResponseEntity
				.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(newsId);
	}


	@GetMapping(path="/gettags")
	public ResponseEntity<List<TagCount>> getTags() { //@RequestParam Integer duration
		List<TagCount> tagsList = service.getTags();
		return ResponseEntity
			.status(HttpStatus.OK)
			.contentType(MediaType.APPLICATION_JSON)
			.body(tagsList);
	}


	@GetMapping(path="/getnews/{tag}")
	public ResponseEntity<List<News>> getNews(@PathVariable String tag) {
		List<News> news = service.getNewsByTag(tag);
		return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(news);
	}

}
