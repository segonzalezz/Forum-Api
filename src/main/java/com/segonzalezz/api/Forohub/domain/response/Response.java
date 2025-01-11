package com.segonzalezz.api.Forohub.domain.response;

import com.segonzalezz.api.Forohub.domain.response.DTO.ResponseDataUpdate;
import com.segonzalezz.api.Forohub.domain.response.DTO.ResponseRegistrationData;
import com.segonzalezz.api.Forohub.domain.topic.DTO.CourseDataUpdate;
import com.segonzalezz.api.Forohub.domain.topic.DTO.CourseRegistrationData;
import com.segonzalezz.api.Forohub.domain.topic.Topic;
import com.segonzalezz.api.Forohub.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "responses")
@Entity(name =  "Response")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Column(name = "creationDate", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;
    private String solution;

    public Response(@Valid ResponseRegistrationData responseRegistrationData) {
        this.active = true;
        this.message =  responseRegistrationData.message();
        this.topic = responseRegistrationData.topic();
        this.creationDate = responseRegistrationData.creationDate();
        this.author = responseRegistrationData.author();
        this.solution = responseRegistrationData.solution();
    }

    public void updateData(ResponseDataUpdate responseDataUpdate) {
        if (responseDataUpdate.message() != null) {
            this.message = responseDataUpdate.message();
        }
        if (responseDataUpdate.topic() != null) {
            this.topic = responseDataUpdate.topic();
        }
        if(responseDataUpdate.creationDate() != null){
            this.creationDate = responseDataUpdate.creationDate();
        }
        if(responseDataUpdate.author() != null){
            this.author = responseDataUpdate.author();
        }
        if(responseDataUpdate.solution() != null){
            this.solution  =  responseDataUpdate.solution();
        }
    }

    public void turnOffActive() {
        this.active = false;
    }
}
