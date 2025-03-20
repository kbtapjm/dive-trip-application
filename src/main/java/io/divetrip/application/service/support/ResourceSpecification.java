package io.divetrip.application.service.support;

import io.divetrip.application.dto.request.ResourceRequest;
import io.divetrip.library.domain.entity.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ResourceSpecification implements Specification<Resource> {
    private final ResourceRequest.SearchResource searchDto;

    @Override
    public Predicate toPredicate(Root<Resource> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.isNull(searchDto.getGroupId())) {
            predicates.add(criteriaBuilder.isNull(root.get("groupId")));
        } else {
            predicates.add(criteriaBuilder.equal(root.get("groupId"), searchDto.getGroupId()));
        }

        if (StringUtils.isNotEmpty(searchDto.getResourceName())) {
            predicates.add(criteriaBuilder.like(root.get("resourceName"), "%" + searchDto.getResourceName() + "%"));
        }
        if (!Objects.isNull(searchDto.getUsed())) {
            predicates.add(criteriaBuilder.equal(root.get("used"), searchDto.getUsed()));
        }

        final Predicate[] predicateArray = new Predicate[predicates.size()];

        return query
                .where(criteriaBuilder.and(predicates.toArray(predicateArray)))
                .getRestriction();
    }

}
