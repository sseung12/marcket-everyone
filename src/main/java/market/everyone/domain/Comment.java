package market.everyone.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Comment {


    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String context;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;



}
