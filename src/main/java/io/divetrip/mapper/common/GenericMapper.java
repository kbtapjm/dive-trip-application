package io.divetrip.mapper.common;

public interface GenericMapper<D, E> {

    D toDto(E e);

    E toEntity(D d);

}
