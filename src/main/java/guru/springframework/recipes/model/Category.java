package guru.springframework.recipes.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    @ManyToMany(mappedBy="categories", fetch = FetchType.EAGER)
    private Set<Recipe> recipes;
}
