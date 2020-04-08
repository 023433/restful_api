package dev.j.api.restful.blog.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = {"userPw", "roles"})
@Table(name = "b_user")
public class User {

    @Id
    @Column(name = "b_user_id", nullable = false)
    @ApiModelProperty(notes = "userId", example = "userId")
    @JsonProperty("userId")
    private String userId;

    @Column(name = "b_user_pw", nullable = false)
    @ApiModelProperty(notes = "userPw", example = "userPw")
    @JsonProperty("userPw")
    private String userPw;

    @Column(name = "b_user_name", length = 20, nullable = false)
    @ApiModelProperty(notes = "userName", example = "userName")
    @JsonProperty("userName")
    private String userName;
    
    @Column(
        name = "b_create_date", 
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", 
        updatable = false
    )
    @JsonProperty("createDate")
    private LocalDateTime createDate;

    @JoinTable(
        name = "b_user_role",
        joinColumns = @JoinColumn(name = "b_user_id", nullable = false)
    )
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name="b_role", nullable=false)
    private List<String> roles = new ArrayList<>();
    
}