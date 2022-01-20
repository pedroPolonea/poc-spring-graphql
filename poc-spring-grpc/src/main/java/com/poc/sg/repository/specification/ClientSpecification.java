package com.poc.sg.repository.specification;

import com.pb.proto.message.ClientMessage;
import com.poc.sg.domain.entity.Client;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ClientSpecification {
    public static Specification<Client> getClientSpecification(final ClientMessage clientMessage) {
        return new Specification<Client>() {
            @Override
            public Predicate toPredicate(
                    final Root<Client> root, final CriteriaQuery<?> query,
                    final CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (!StringUtils.isEmpty(clientMessage.getName())) {
                    Predicate ctfPredicate = criteriaBuilder.like(
                            criteriaBuilder.upper(root.get("name")),
                            "%" + clientMessage.getName().toUpperCase() + "%"
                    );

                    predicates.add(ctfPredicate);
                }

                if (!StringUtils.isEmpty(clientMessage.getDocument())) {
                    Predicate ctfPredicate = criteriaBuilder.like(
                            root.get("document"),
                            "%" + clientMessage.getDocument() + "%"
                    );

                    predicates.add(ctfPredicate);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
            }
        };
    }
}
