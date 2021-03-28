package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.model.network.response.UserApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseService<Req, Res, Entity> implements CrudInterface<Req, Res> {

    @Autowired(required=false)
    protected JpaRepository<Entity, Long> baseRepository;

    public abstract Res response(Entity entity);

    @Override
    public Header<List<Res>> search(Pageable pageable) {
            Page<Entity> entities = baseRepository.findAll(pageable);
            List<Res> resList = entities.stream()
                    .map(entity->response(entity))
                    .collect(Collectors.toList());

            Pagination pagination = Pagination.builder()
                    .totalPages(entities.getTotalPages())
                    .totalElements(entities.getTotalElements())
                    .currentPage(entities.getNumber())
                    .currentElements(entities.getNumberOfElements())
                    .build();
            return Header.OK(resList,pagination);
    }
}
