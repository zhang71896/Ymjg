package com.yrj520.pfapp.ymjg.UI.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JacksonMapper {
    private static ObjectMapper mapper;

    /**
     * @param createNew 是否创建一个新的Mapper
     * @return
     */
    public static synchronized ObjectMapper getMapperInstance(boolean createNew) {
        if (createNew) {
            return new ObjectMapper();
        } else if (mapper == null) {
            mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            //允许将JSON空字符串值（""）绑定到POJO为null的功能
            mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        }
        return mapper;
    }
}
