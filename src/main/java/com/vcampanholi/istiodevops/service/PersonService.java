package com.vcampanholi.istiodevops.service;import com.vcampanholi.istiodevops.mapper.PersonMapper;import com.vcampanholi.istiodevops.model.PersonRequest;import com.vcampanholi.istiodevops.model.PersonResponse;import com.vcampanholi.istiodevops.repository.PersonRepository;import org.springframework.stereotype.Service;import reactor.core.publisher.Flux;import reactor.core.publisher.Mono;@Servicepublic class PersonService {    private PersonRepository personRepository;    public Flux<PersonResponse> findAllPerson() {        return personRepository.findAll().map(PersonMapper::mapToResponse);    }    public Mono<PersonResponse> findPersonById(String id) {        return personRepository.findById(id)                .map(PersonMapper::mapToResponse);    }    public Mono<PersonResponse> createPerson(PersonRequest personRequest) {        return personRepository.save(PersonMapper.mapToEntity(personRequest))                .map(PersonMapper::mapToResponse);    }    public Mono<PersonResponse> updatePerson(PersonRequest personRequest, String id) {        personRequest.setId(id);        return personRepository.save(PersonMapper.mapToEntity(personRequest))                .map(PersonMapper::mapToResponse);    }    public Mono<Void> deletePerson(String id) {        return personRepository.deleteById(id);    }}