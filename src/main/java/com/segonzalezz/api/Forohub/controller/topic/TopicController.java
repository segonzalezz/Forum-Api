package com.segonzalezz.api.Forohub.controller.topic;

import com.segonzalezz.api.Forohub.domain.topic.Course;
import com.segonzalezz.api.Forohub.domain.topic.DTO.*;
import com.segonzalezz.api.Forohub.domain.topic.Topic;
import com.segonzalezz.api.Forohub.domain.topic.TopicRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    public ResponseEntity<TopicDataResponse> registerTopics(@RequestBody @Valid TopicRegistrationData topicRegistrationData, UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = topicRepository.save(new Topic(topicRegistrationData));
        TopicDataResponse topicDataResponse = new TopicDataResponse(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getAuthor(),
                topic.getCourse(),
                topic.getResponses()
        );
        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(url).body(topicDataResponse);
    }

    @GetMapping
    public ResponseEntity<Page<TopicDataList>> listTopics(@PageableDefault(size = 2) Pageable pagination) {
        Page<Topic> topicPage = topicRepository.findByActiveTrue(pagination);
        if (topicPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        Page<TopicDataList> topicDataList = topicPage.map(TopicDataList::new);
        return ResponseEntity.ok(topicDataList);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateTopics(@RequestBody @Valid TopicDataUpdate topicDataUpdate) {
        Topic topic = topicRepository.getReferenceById(topicDataUpdate.id());
        topic.updateData(topicDataUpdate);
        return ResponseEntity.ok(new TopicDataResponse(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreationDate(), topic.getAuthor(), topic.getCourse(), topic.getResponses()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopics(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        topic.turnOffActive();
        return ResponseEntity.noContent().build();
    }
}
