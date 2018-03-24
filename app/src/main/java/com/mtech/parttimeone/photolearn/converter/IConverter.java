package com.mtech.parttimeone.photolearn.converter;

/**
 * @author lechin
 * @date 3/24/18
 */

public interface IConverter<From, To> {

    From mapFrom(To to);

    To mapTo(From from);
}
