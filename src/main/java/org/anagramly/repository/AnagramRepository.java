package org.anagramly.repository;

import org.anagramly.model.AnagramResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnagramRepository extends CrudRepository<AnagramResult, Long> {
}
