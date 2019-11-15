package boot.jpa.junit.service;

import boot.jpa.junit.domain.hero.HeroRepository;
import boot.jpa.junit.dto.HeroFindAllResponseDto;
import boot.jpa.junit.dto.HeroFindByIdResponseDto;
import boot.jpa.junit.dto.HeroSaveRequestDto;
import boot.jpa.junit.dto.HeroUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HeroServiceTest {

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private HeroService heroService;

    @After
    public void cleanUp() {
        heroRepository.deleteAll();
    }

    @Test
    public void HeroSaveRequestTest() {
        //given
        HeroSaveRequestDto input = HeroSaveRequestDto.builder()
                .name("github.com/hong-il")
                .age(26)
                .note("github.com/hong-il")
                .build();

        //when
        Long output = heroService.HeroSaveRequest(input);

        //then
        assertThat(output, is(1L));
    }

    @Test
    public void HeroFindAllResponseTest() {
        //given
        HeroSaveRequestDto input = HeroSaveRequestDto.builder()
                .name("github.com/hong-il")
                .age(26)
                .note("github.com/hong-il")
                .build();

        IntStream.rangeClosed(1, 10).forEach(i ->
                heroService.HeroSaveRequest(input));

        //when
        List<HeroFindAllResponseDto> output = heroService.HeroFindAllResponse();

        //then
        assertThat(output.size(), is(10));
    }

    @Test
    public void HeroFindByIdResponseTest() {
        //given
        HeroSaveRequestDto input = HeroSaveRequestDto.builder()
                .name("github.com/hong-il")
                .age(26)
                .note("github.com/hong-il")
                .build();

        heroService.HeroSaveRequest(input);

        //when
        HeroFindByIdResponseDto output = heroService.HeroFindByIdResponse(1L);

        //then
        assertThat(input.getName(), is(output.getName()));
        assertThat(input.getAge(), is(output.getAge()));
        assertThat(input.getNote(), is(output.getNote()));
    }

    @Test
    public void HeroUpdateRequestTest() {
        //given
        HeroSaveRequestDto input = HeroSaveRequestDto.builder()
                .name("temp")
                .age(0)
                .note("temp")
                .build();

        heroService.HeroSaveRequest(input);

        //when
        Long output = heroService.HeroUpdateRequest(HeroUpdateRequestDto.builder()
                .id(1L)
                .name("github.com/hong-il")
                .age(26)
                .note("github.com/hong-il")
                .build());

        //then
        assertThat(output, is(1L));
    }

    @Test
    public void HeroDeleteByIdRequestTest() {
        //given
        HeroSaveRequestDto input = HeroSaveRequestDto.builder()
                .name("github.com/hong-il")
                .age(26)
                .note("github.com/hong-il")
                .build();

        heroService.HeroSaveRequest(input);

        //when
        heroService.HeroDeleteByIdRequest(1L);

        //then
        assertThat(heroService.HeroFindAllResponse().size(), is(0));
    }
}
