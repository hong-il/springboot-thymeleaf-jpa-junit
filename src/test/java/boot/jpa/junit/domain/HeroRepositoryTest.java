package boot.jpa.junit.domain;

import boot.jpa.junit.domain.hero.Hero;
import boot.jpa.junit.domain.hero.HeroRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HeroRepositoryTest {

    @Autowired
    private HeroRepository heroRepository;

    @After
    public void cleanUp() {
        heroRepository.deleteAll();
    }

    @Test
    public void JpaAuditingTest() {
        //given
        LocalDateTime now = LocalDateTime.parse("2019-11-08T00:00:00");

        Hero input = Hero.builder()
                .name("github.com/hong-il")
                .age(26)
                .note("github.com/hong-il")
                .build();

        //when
        Hero output = heroRepository.save(input);

        //then
        assertTrue(output.getCreatedDate().isAfter(now));
        assertTrue(output.getModifiedDate().isAfter(now));
    }

    @Test
    public void HeroSaveRequestTest() {
        //given
        Hero input = Hero.builder()
                .name("github.com/hong-il")
                .age(26)
                .note("github.com/hong-il")
                .build();

        //when
        Hero output = heroRepository.save(input);

        //then
        assertThat(input, is(output));
    }

    @Test
    public void HeroFindAllResponseTest() {
        //given
        IntStream.rangeClosed(1, 10).forEach(i ->
                heroRepository.save(Hero.builder()
                        .name("github.com/hong-il")
                        .age(26)
                        .note("github.com/hong-il")
                        .build()));

        //when
        List<Hero> output = heroRepository.findAll();

        //then
        assertThat(output.size(), is(10));
    }

    @Test
    public void HeroFindByIdResponseTest() {
        //given
        Hero input = Hero.builder()
                .name("github.com/hong-il")
                .age(26)
                .note("github.com/hong-il")
                .build();

        heroRepository.save(input);

        //when
        Hero output = heroRepository.findById(1L).orElse(null);

        //then
        assertThat(input.getName(), is(output.getName()));
        assertThat(input.getAge(), is(output.getAge()));
        assertThat(input.getNote(), is(output.getNote()));
    }

    @Test
    public void HeroUpdateRequestTest() {
        //given
        Hero input = Hero.builder()
                .name("temp")
                .age(0)
                .note("temp")
                .build();

        heroRepository.save(input);

        //when
        heroRepository.save(Hero.builder()
                .id(1L)
                .name("github.com/hong-il")
                .age(26)
                .note("github.com/hong-il")
                .build());

        //then
        Hero output = heroRepository.findById(1L).orElse(null);
        assertThat(input.getId(), is(output.getId()));
        assertThat(input.getCreatedDate(), is(output.getCreatedDate()));
        assertTrue(output.getModifiedDate()
                .isAfter(input.getModifiedDate()));
    }

    @Test
    public void HeroDeleteRequest() {
        //given
        heroRepository.save(Hero.builder()
                .name("github.com/hong-il")
                .age(26)
                .note("github.com/hong-il")
                .build());

        //when
        heroRepository.deleteById(1L);

        //then
        assertNull(heroRepository.findById(1L).orElse(null));
    }
}
