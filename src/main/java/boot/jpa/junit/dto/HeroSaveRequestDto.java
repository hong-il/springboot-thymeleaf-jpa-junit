package boot.jpa.junit.dto;

import boot.jpa.junit.domain.hero.Hero;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeroSaveRequestDto {

    private String name;
    private int age;
    private String note;

    public Hero toEntity() {
        return Hero.builder()
                .name(name)
                .age(age)
                .note(note)
                .build();
    }
}
