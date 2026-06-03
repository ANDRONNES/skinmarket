package com.skinmarket.project.mapper;

import java.util.List;

public interface DtoMapper <T,V>{
    public T toDto(V entity);

    default List<T> toDtoList(List<V> entities) {
        if (entities == null) return List.of();
        return entities.stream().map(entity -> this.toDto(entity)).toList();
    }

//    default T toDto(V entity, Object object){
//        return toDto(entity);
//    }
}
