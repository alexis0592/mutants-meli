package com.meli.challenge.mutantchallenge.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.challenge.mutantchallenge.MutantChallengeApplication;
import com.meli.challenge.mutantchallenge.model.DNA;
import com.meli.challenge.mutantchallenge.service.IDNAService;
import com.meli.challenge.mutantchallenge.service.model.dto.MutantStatDTO;
import com.meli.challenge.mutantchallenge.utils.exceptions.InvalidDnaSequenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MutantChallengeApplication.class)
@AutoConfigureMockMvc
class DNAControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDNAService dnaServiceMock;

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldReturn200WhenIsMutant() throws Exception {
        String uri = "/mutant";

        String[] dnaArray = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        DNA dna = DNA.builder().dna(dnaArray).build();
        String req = mapToJson(dna);

        when(dnaServiceMock.isMutant(any())).thenReturn(true);

        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(req))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return Forbidden When is not Mutant")
    public void shouldReturn403WhenIsHuman() throws Exception {
        String uri = "/mutant";

        String[] dnaArray = {"TTGCGA","CAGTCC","TTATGT","AGAAGG","CACCTA","TCACTG"};
        DNA dna = DNA.builder().dna(dnaArray).build();
        String req = mapToJson(dna);

        when(dnaServiceMock.isMutant(any())).thenReturn(false);

        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(req))
                .andExpect(status().isForbidden());
    }

    @Test
    public void shouldReturn500WhenDNAIsWrong() throws Exception {
        String uri = "/mutant";

        String[] dnaArray = {"TTGCGA","CARTCC","TTAFGT","AGAAGG","CACCTA","TCACTG"};
        DNA dna = DNA.builder().dna(dnaArray).build();
        String req = mapToJson(dna);

        when(dnaServiceMock.isMutant(any())).thenThrow(new InvalidDnaSequenceException());

        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(req))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldReturnStatsSuccesfully() throws Exception {
        String uri = "/stats";

        MutantStatDTO mutantStatDTO = MutantStatDTO.builder()
                .countHumanDna(100)
                .countMutantDna(40)
                .ratio(0.4)
                .build();
        when(dnaServiceMock.getStats()).thenReturn(mutantStatDTO);

        mockMvc.perform(get(uri))
                .andExpect(status().isOk());

    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

}