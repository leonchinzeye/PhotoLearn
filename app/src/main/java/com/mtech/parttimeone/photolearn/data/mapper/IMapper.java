package com.mtech.parttimeone.photolearn.data.mapper;

public interface IMapper<From, To> {
    To map(From from);
}
