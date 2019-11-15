package boot.jpa.junit.dto;

import boot.jpa.junit.domain.hero.Hero;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeroUpdateRequestDto {

    private Long id;
    private String name;
    private int age;
    private String note;

    public Hero toEntity() {
        return Hero.builder()
                .id(id)
                .name(name)
                .age(age)
                .note(note)
                .build();
    }

    @Builder
    public HeroUpdateRequestDto(Long id, String name, int age, String note) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.note = note;
    }
}
