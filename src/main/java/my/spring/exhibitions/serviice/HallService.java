package my.spring.exhibitions.serviice;

import my.spring.exhibitions.entity.Hall;

import java.util.List;

public interface HallService {
    List<Hall> findAll();
}
