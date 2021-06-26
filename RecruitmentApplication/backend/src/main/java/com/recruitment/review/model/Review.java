package com.recruitment.review.model;

import com.recruitment.item.model.Item;
import com.recruitment.review.Rating;
import com.recruitment.user.model.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue
    private Long id;
    private String text;
    private Rating rating;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

}
