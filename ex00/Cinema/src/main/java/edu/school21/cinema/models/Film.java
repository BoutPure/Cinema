package edu.school21.cinema.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "films")
public class Film implements Serializable {

    private static final int FIRST_FILM = 1895;
    private static final int CURRENT_YEAR = 2022;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "age_restrictions")
    private int ageRestrictions;

    private String title;

    private int year;

    private String description;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "poster_id")
    private Poster poster;

    public static Optional<Film> validateFilm(String title, String description, String year, String  ageRestrictions) {
        Integer restrictions = null;
        Integer yearOfRelease = null;
        try {
            restrictions = Integer.parseInt(ageRestrictions);
            yearOfRelease = Integer.parseInt(year);
        } catch (NumberFormatException ignored) {}
        if (ageRestrictions != null && yearOfRelease != null
                && yearOfRelease >= FIRST_FILM && yearOfRelease <= CURRENT_YEAR) {
            Film film = new Film();
            film.setDescription(description);
            film.setTitle(title);
            film.setYear(yearOfRelease);
            film.setAgeRestrictions(restrictions);
            return Optional.of(film);
        }
        return Optional.empty();
    }
}
