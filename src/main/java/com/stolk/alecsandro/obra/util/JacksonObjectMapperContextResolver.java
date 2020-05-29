//package com.stolk.alecsandro.obra.util;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.AnnotationIntrospector;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
//import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
//
//import javax.ws.rs.ext.ContextResolver;
//import javax.ws.rs.ext.Provider;
//
//@Provider
//public class JacksonObjectMapperContextResolver implements ContextResolver<ObjectMapper> {
//
//    private final ObjectMapper mapper;
//
//    public JacksonObjectMapperContextResolver() {
//        mapper = createMapper();
//    }
//
//    private final ObjectMapper createMapper() {
//
//        // cria um novo ObjectMapper para a aplicação
//        final ObjectMapper objectMapper = new ObjectMapper();
//
//        // inclui o suporte a anotações JAXB
//        AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
//        AnnotationIntrospector secondary = new JaxbAnnotationIntrospector(objectMapper.getTypeFactory());
//        AnnotationIntrospector pair = AnnotationIntrospector.pair(primary, secondary);
//
//        objectMapper.setConfig(objectMapper.getDeserializationConfig()
//                .with(pair));
//        objectMapper.setConfig(objectMapper.getSerializationConfig()
//                .with(pair).withSerializationInclusion(JsonInclude.Include.ALWAYS));
//
//        // Desabilita a escrita de dats como timestamp
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        // desabilita a falha ao se trabalhar com variaveis vazias
//        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
//
//        //Por padrão os maps devem serializar valores null
//        objectMapper.enable(SerializationFeature.WRITE_NULL_MAP_VALUES);
//
//        // Habilita a falha em caso de variavel primitiva nula
//        objectMapper.enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
//        // Habilita a falha em caso de uso de numeros para enums
//        objectMapper.enable(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS);
//        // Habilita a falha em caso de recebimento de uma propriedade desconhecida
//        objectMapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        // Habilita o uso de um valor simples para arrays de apenas uma posição
//        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
//
//        // Registra o módulo que da suporte as datas do projeto ThreeTen
//        objectMapper.registerModule(new JacksonJavaTimeModule());
//        // Registra o módulo que da suporte ao uso de EntityVersion
//        objectMapper.registerModule(new JacksonEntityModule());
//        // Registra o módulo que trata String vazias como null
//        objectMapper.registerModule(new JacksonStringModule());
//        // Registra o módulo que trata as Enums
//        objectMapper.registerModule(new JacksonEnumTypeModule());
//
//        return objectMapper;
//    }
//
//    @Override
//    public ObjectMapper getContext(Class<?> type) {
//        return mapper;
//    }
//}
