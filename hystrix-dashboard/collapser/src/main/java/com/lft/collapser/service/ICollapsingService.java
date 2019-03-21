package com.lft.collapser.service;

import com.lft.collapser.entity.Animal;

import java.util.concurrent.Future;

public interface ICollapsingService {
    Future<Animal> collapsing(Integer id);

    Animal collapsingSyn(Integer id);

    Future<Animal> collapsingGlobal(Integer id);
}
