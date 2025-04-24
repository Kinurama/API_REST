package com.example.api.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

public class FieldFilter {
    private static final ObjectMapper mapper = new ObjectMapper();
    public static Map<String,Object> filter(Object src, List<String> fields){
        Map<String,Object> m = mapper.convertValue(src, Map.class);
        if(fields!=null) m.keySet().retainAll(fields);
        return m;
    }
}