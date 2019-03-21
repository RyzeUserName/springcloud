package com.lft.collapser.service;

import com.lft.collapser.entity.Animal;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 描述
 * @author Ryze
 * @date 2019/3/21 21:50
 */
@Service
public class CollapsingServiceImpl implements ICollapsingService {
    @HystrixCollapser(batchMethod = "collapsingList", collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "1000")})
    @Override
    public Future<Animal> collapsing(Integer id) {
        return null;
    }

    @HystrixCollapser(batchMethod = "collapsingList", collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "1000")})
    @Override
    public Animal collapsingSyn(Integer id) {
        return null;
    }

    @HystrixCollapser(batchMethod = "collapsingListGlobal", scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL, collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "1000")})
    @Override
    public Future<Animal> collapsingGlobal(Integer id) {
        return null;
    }

    @HystrixCommand
    public List<Animal> collapsingList(List<Integer> animalParam) {
        System.out.println("collapsingList当前线程" + Thread.currentThread().getName());
        System.out.println("当前请求参数个数:" + animalParam.size());
        List<Animal> animalList = new ArrayList<Animal>();
        for (Integer animalNumber : animalParam) {
            Animal animal = new Animal();
            animal.setName("Cat - " + animalNumber);
            animal.setSex("male");
            animal.setAge(animalNumber);
            animalList.add(animal);
        }
        return animalList;
    }

    @HystrixCommand
    public List<Animal> collapsingListGlobal(List<Integer> animalParam) {
        System.out.println("collapsingListGlobal当前线程" + Thread.currentThread().getName());
        System.out.println("当前请求参数个数:" + animalParam.size());
        List<Animal> animalList = new ArrayList<Animal>();
        for (Integer animalNumber : animalParam) {
            Animal animal = new Animal();
            animal.setName("Dog - " + animalNumber);
            animal.setSex("male");
            animal.setAge(animalNumber);
            animalList.add(animal);
        }
        return animalList;
    }
}
