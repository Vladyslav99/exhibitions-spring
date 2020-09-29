package my.spring.exhibitions.serviice;

import my.spring.exhibitions.entity.Hall;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HallServiceImpl implements HallService{
    @Override
    public List<Hall> findAll() {
        return new ArrayList<>();
    }
}
