package com.example.setloggersb.entities;

import com.example.setloggersb.entities.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/* BufferedCompletedExercise is a CompletedExercise that has not been tied into a session.
 * When a
 */
@Entity
@Data
public class BufferedCompletedExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Exercise exercise;
    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn
    List<String> dataValues = new ArrayList<>();
    private Long timeStamp;
    @ManyToOne
    private User user;

    public static Comparator<BufferedCompletedExercise> getComparator() {
        return (o1, o2) -> (int) (o1.getTimeStamp() - o2.getTimeStamp());
    }
}