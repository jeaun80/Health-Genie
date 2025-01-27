package com.example.healthgenie.controller;

import com.example.healthgenie.dto.TrainerProfileModifyRequestDto;
import com.example.healthgenie.dto.TrainerProfileRequestDto;
import com.example.healthgenie.entity.TrainerProfile;
import com.example.healthgenie.global.exception.GlobalExceptionHandler;
import com.example.healthgenie.service.TrainerProfileServie;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TrainerProfileControllerTest {

    @InjectMocks
    private TrainerProfileController target;

    private MockMvc mockMvc;

    private Gson gson;

    @Mock
    TrainerProfileServie servie;



    @BeforeEach
    public void init(){
        gson = new Gson();

        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();


    }

    @Test
    public void 약력작성실패_없음(){
        //예상 실패경로 없음
    }
    @Test
    public void 약력작성성공() throws Exception {

        //given
        Long userId = 1L;
        String url = "/profile/add";

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post(url)
                .content(gson.toJson(TrainerProfileRequestDto()))
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk());

    }
    @Test
    public void 약력수정성공() throws Exception {



        //given
        String url = "/profile/modify";
        Long userId = 1L;
        TrainerProfileModifyRequestDto dto = TrainerProfileModifyRequestDto.builder()
                .certification("testCERT")
                .description("testDES")
                .prize("testPRI")
                .build();
        //when
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post(url)
                .content(gson.toJson(dto))
                .contentType(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpect(status().isOk());


    }
    @Test
    public void 약력수정실패_(){

        //예상 실패경로 없음
    }

    public TrainerProfileRequestDto TrainerProfileRequestDto(){
        return TrainerProfileRequestDto.builder()
                .certification("test").build();
    }


}
